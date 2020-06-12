;Interface
(definterface Operation
  (evaluate [])
  (toString [])
  (toStringSuffix [])
  (diff []))

;Operation
(defn evaluate [expr vars] ((.evaluate expr) vars))
(defn toString [expr] (.toString expr))
(defn toStringSuffix [expr] (.toStringSuffix expr))
(defn diff [expr var] ((.diff expr) var))

(deftype CommonPrototype [operation symbol diffRule])

(deftype CommonOperation [prototype args]
  Operation
  (evaluate [this] #(apply (.operation prototype) (map (fn [x] (evaluate x %)) args)))
  (toString [this] (str "(" (.symbol prototype) " " (clojure.string/join " " (map toString args)) ")"))
  (toStringSuffix [this] (str "(" (clojure.string/join " " (map toStringSuffix args)) " " (.symbol prototype) ")"))
  (diff [this] #(apply (.diffRule prototype) (concat args (map (fn [x] (diff x %)) args)))))

;Constant
(declare Constant)

(deftype ConstantPrototype [value]
  Operation
  (evaluate [this] (fn [vars] value))
  (toString [this] (format "%.1f" (double value)))
  (toStringSuffix [this] (toString this))
  (diff [this] (fn [var] (Constant 0))))

(defn Constant [value] (ConstantPrototype. value))

;Variable
(declare Variable)

(defn getVar [name] (str (first (clojure.string/lower-case name))))

(deftype VariablePrototype [name]
  Operation
  (evaluate [this] (fn [vars] (get vars (getVar name))))
  (toString [this] name)
  (toStringSuffix [this] name)
  (diff [this] (fn [var] (if (= var (getVar name)) (Constant 1) (Constant 0)))))

(defn Variable [name] (VariablePrototype. name))

(defn Add [& args] (CommonOperation. (CommonPrototype.
                                       +
                                       "+"
                                       (fn [a b da db] (Add da db))) args))

(defn Subtract [& args] (CommonOperation. (CommonPrototype.
                                            -
                                            "-"
                                            (fn [a b da db] (Subtract da db))) args))

(defn Multiply [& args] (CommonOperation. (CommonPrototype.
                                            *
                                            "*"
                                            (fn [a b da db] (Add (Multiply da b) (Multiply db a)))) args))

(defn Divide [& args] (CommonOperation. (CommonPrototype.
                                          #(/ (double %1) (double %2))
                                          "/"
                                          (fn [a b da db] (Divide (Subtract (Multiply da b) (Multiply db a)) (Multiply b b)))) args))

(defn Negate [& args] (CommonOperation. (CommonPrototype.
                                          -
                                          "negate"
                                          (fn [a da] (Negate da))) args))

(defn Exp [& args] (CommonOperation. (CommonPrototype.
                                       #(Math/exp ^double %)
                                       "exp"
                                       (fn [a da] (Multiply da (Exp a)))) args))

(defn Ln [& args] (CommonOperation. (CommonPrototype.
                                      #(Math/log (Math/abs ^double %))
                                      "ln"
                                      (fn [a da] (Divide da a))) args))

;Parsers

(def OPERATIONS {
                 '+ Add,
                 '- Subtract,
                 '* Multiply,
                 '/ Divide,
                 'negate Negate,
                 'exp Exp,
                 'ln Ln})

(defn parseObject [expression]
  (cond
    (number? expression) (Constant expression)
    (symbol? expression) (Variable (str expression))
    (seq? expression) (apply (get OPERATIONS (first expression)) (map parseObject (rest expression)))
    (string? expression) (parseObject (read-string expression))))

;Combinators
(defn -return [value tail] {:value value :tail tail})
(def -valid? boolean)
(def -value :value)
(def -tail :tail)
(defn _show [result]
  (if (-valid? result) (str "-> " (pr-str (-value result)) " | " (pr-str (apply str (-tail result))))
                       "!"))
(defn tabulate [parser inputs]
  (run! (fn [input] (printf "    %-10s %s\n" input (_show (parser input)))) inputs))
(defn _empty [value] (partial -return value))
(defn _char [p]
  (fn [[c & cs]]
    (if (and c (p c)) (-return c cs))))
(defn _map [f]
  (fn [result]
    (if (-valid? result)
      (-return (f (-value result)) (-tail result)))))
(defn _combine [f a b]
  (fn [str]
    (let [ar ((force a) str)]
      (if (-valid? ar)
        ((_map (partial f (-value ar)))
         ((force b) (-tail ar)))))))
(defn _either [a b]
  (fn [str]
    (let [ar ((force a) str)]
      (if (-valid? ar) ar ((force b) str)))))
(defn _parser [p]
  (fn [input]
    (-value ((_combine (fn [v _] v) p (_char #{\u0000})) (str input \u0000)))))

(defn +char [chars] (_char (set chars)))
(defn +char-not [chars] (_char (comp not (set chars))))
(defn +map [f parser] (comp (_map f) parser))
(def +parser _parser)
(def +ignore (partial +map (constantly 'ignore)))
(defn iconj [coll value]
  (if (= value 'ignore) coll (conj coll value)))
(defn +seq [& ps]
  (reduce (partial _combine iconj) (_empty []) ps))
(defn +seqf [f & ps] (+map (partial apply f) (apply +seq ps)))
(defn +seqn [n & ps] (apply +seqf (fn [& vs] (nth vs n)) ps))
(defn +or [p & ps]
  (reduce (partial _either) p ps))
(defn +opt [p]
  (+or p (_empty nil)))
(defn +star [p]
  (letfn [(rec [] (+or (+seqf cons p (delay (rec))) (_empty ())))] (rec)))
(defn +plus [p] (+seqf cons p (+star p)))
(defn +str [p] (+map (partial apply str) p))

(def *all-chars (mapv char (range 0 128)))
(def *space (+char (apply str (filter #(Character/isWhitespace (char %)) *all-chars))))
(def *letter (+char (apply str (filter #(Character/isLetter (char %)) *all-chars))))
(def *digit (+char (apply str (filter #(Character/isDigit (char %)) *all-chars))))

(def *ws (+ignore (+star *space)))

(def *constant (+map (comp Constant read-string)
                     (+str (+seq (+opt (+char "-+")) (+str (+plus *digit)) (+char ".") (+str (+plus *digit))
                                 (+opt (+seq (+char "e") (+opt (+char "-+")) (+str (+plus *digit))))))))

(def *operations (+char "+-*/"))
(def *identifier (+str (+plus (+or *letter *operations))))
(def *function-or-variable (+map (comp #(OPERATIONS % (Variable (str %))) symbol) *identifier))

(declare *value)

(defn *seq [begin p end] (+seqn 1 (+char begin) (+plus (+seqn 0 *ws p)) *ws (+char end)))
(def *list (+map (fn [list] (apply (last list) (butlast list))) (*seq "(" (delay *value) ")")))
(def *value (+or *constant *function-or-variable *list))

(def parseObjectSuffix (+parser (+seqn 0 *ws *value *ws)))