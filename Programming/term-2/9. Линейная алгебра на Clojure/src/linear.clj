(defn helper-apply [f] (fn [& arg] (apply mapv f arg)))

(def v+ (helper-apply +))
(def v- (helper-apply -))
(def v* (helper-apply *))
(def vd (helper-apply /))

(defn scalar [a, b] (apply + (v* a b)))
(defn vect [a, b] (let [[a1 a2 a3] a [b1 b2 b3] b]
                    [(- (* a2 b3) (* a3 b2)),
                     (- (* a3 b1) (* a1 b3)),
                     (- (* a1 b2) (* a2 b1))]))

(defn v*s [a, b] (mapv (fn [n] (* b n)) a))
(def m+ (helper-apply v+))
(def m- (helper-apply v-))
(def m* (helper-apply v*))
(def md (helper-apply vd))
(defn m*s [a, b] (mapv (fn [n] (v*s n b)) a))
(defn m*v [a, b] (mapv (fn [n] (scalar n b)) a))
(defn transpose [a] (apply mapv vector a))
(defn m*m [a, b] (mapv (fn [n] (mapv (fn [m] (scalar n m)) (transpose b))) a))

(def c+ (helper-apply m+))
(def c- (helper-apply m-))
(def c* (helper-apply m*))
(def cd (helper-apply md))