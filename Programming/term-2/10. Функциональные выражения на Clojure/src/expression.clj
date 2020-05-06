(defn op [operator] (fn [& operands] (fn [args] (apply operator (map #(% args) operands)))))
(defn reduce-fold [f] (fn [& args] (reduce #(f %1 %2) args)))

(defn constant [val] (constantly val))
(defn variable [name] #(get % name))

(def add (op +))
(def subtract (op -))
(def multiply (op *))
(def divide (op #(/ %1 ^double %2)))
(def negate subtract)

(def square (op #(* % %)))
(def sqrt (op #(Math/sqrt (Math/abs ^double %))))

(def min (op (reduce-fold min)))
(def max (op (reduce-fold max)))

(def operator {'+ add, '- subtract, '* multiply, '/ divide,
               'negate negate, 'square square, 'sqrt sqrt,
               'min min, 'max max})

(defn parseSeq [expr] (cond
                        (seq? expr) (apply (operator (first expr)) (map parseSeq (rest expr)))
                        (symbol? expr) (variable (str expr))
                        (number? expr) (constant expr)))

(defn parseFunction [expr] (parseSeq (read-string expr)))