module HW2.T2
       ( module HW2.T1,
       distOption,
       wrapOption,
       distPair,
       wrapPair,
       distQuad,
       wrapQuad,
       distAnnotated,
       wrapAnnotated,
       distExcept,
       wrapExcept,
       distPrioritised,
       wrapPrioritised,
       distStream,
       wrapStream,
       distList,
       concatList,
       wrapList,
       distFun,
       wrapFun
       ) where

import HW2.T1




distOption :: (Option a, Option b) -> Option (a, b)
distOption (None, None)     = None
distOption (None, Some _)   = None
distOption (Some _, None)   = None
distOption (Some a, Some b) = Some (a, b)

wrapOption :: a -> Option a
wrapOption = Some



distPair :: (Pair a, Pair b) -> Pair (a, b)
distPair (P a1 a2, P b1 b2) = P (a1, b1) (a2, b2)

wrapPair :: a -> Pair a
wrapPair a = P a a



distQuad :: (Quad a, Quad b) -> Quad (a, b)
distQuad (Q a1 a2 a3 a4, Q b1 b2 b3 b4) = Q (a1, b1) (a2, b2) (a3, b3) (a4, b4)

wrapQuad :: a -> Quad a
wrapQuad a = Q a a a a



distAnnotated :: Semigroup e => (Annotated e a, Annotated e b) -> Annotated e (a, b)
distAnnotated (a :# e1, b :# e2) = (a, b) :# (e1 <> e2)

wrapAnnotated :: Monoid e => a -> Annotated e a
wrapAnnotated a = a :# mempty



distExcept :: (Except e a, Except e b) -> Except e (a, b)
distExcept (Error e1, _)          = Error e1
distExcept (_, Error e2)          = Error e2
distExcept (Success a, Success b) = Success (a, b)

wrapExcept :: a -> Except e a
wrapExcept = Success



distPrioritised :: (Prioritised a, Prioritised b) -> Prioritised (a, b)
distPrioritised (Low a, Low b)       = Low (a, b)
distPrioritised (Low a, Medium b)    = Medium (a, b)
distPrioritised (Low a, High b)      = High (a, b)
distPrioritised (Medium a, Low b)    = Medium (a, b)
distPrioritised (Medium a, Medium b) = Medium (a, b)
distPrioritised (Medium a, High b)   = High (a, b)
distPrioritised (High a, Low b)      = High (a, b)
distPrioritised (High a, Medium b)   = High (a, b)
distPrioritised (High a, High b)     = High (a, b)

wrapPrioritised :: a -> Prioritised a
wrapPrioritised = Low



distStream :: (Stream a, Stream b) -> Stream (a, b)
distStream (a :> sa, b :> sb) = (a, b) :> distStream (sa, sb)

wrapStream :: a -> Stream a
wrapStream a = a :> wrapStream a



distOneToList :: a -> List b -> List (a, b)
distOneToList _  Nil      = Nil
distOneToList a (b :. xb) = (a, b) :. distOneToList a xb

concatList :: List a -> List a -> List a
concatList Nil b       = b
concatList (a :. xa) b = a :. concatList xa b

distList :: (List a, List b) -> List (a, b)
distList (Nil, _)     = Nil
distList (a :. xa, b) = concatList (distOneToList a b) (distList (xa, b))

wrapList :: a -> List a
wrapList a = a :. Nil



distFun :: (Fun i a, Fun i b) -> Fun i (a, b)
distFun (F f, F g) = F (\arg -> (f arg, g arg))

wrapFun :: a -> Fun i a
wrapFun a = F (const a)