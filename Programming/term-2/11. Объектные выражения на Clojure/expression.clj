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

(declare parseObject)

(defn parseObject [expression]
  (cond
    (number? expression) (Constant expression)
    (symbol? expression) (Variable (str expression))
    (seq? expression) (apply (get OPERATIONS (first expression)) (map parseObject (rest expression)))
    (string? expression) (parseObject (read-string expression))))