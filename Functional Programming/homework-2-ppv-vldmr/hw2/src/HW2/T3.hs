module HW2.T3 
    ( module HW2.T1,
    module HW2.T2,
    joinOption,
    joinExcept,
    joinAnnotated,
    joinList,
    joinFun
    )where
  
import HW2.T1
import HW2.T2 (concatList)



joinOption :: Option (Option a) -> Option a
joinOption None            = None
joinOption (Some None)     = None
joinOption (Some (Some a)) = Some a



joinExcept :: Except e (Except e a) -> Except e a
joinExcept (Success (Success a)) = Success a
joinExcept (Success (Error e))   = Error e
joinExcept (Error x)             = Error x



joinAnnotated :: Semigroup e => Annotated e (Annotated e a) -> Annotated e a
joinAnnotated ((a :# e1) :# e2) = a :# (e2 <> e1)



joinList :: List (List a) -> List a
joinList Nil = Nil
joinList (a :. b) = concatList a (joinList b)



joinFun :: Fun i (Fun i a) -> Fun i a
joinFun (F f) = F (\i -> let (F tempF) = f i in tempF i)
