module HW2.T1 where
  


data Option a = None | Some a
  deriving Show

mapOption :: (a -> b) -> (Option a -> Option b)
mapOption _ None     = None
mapOption f (Some a) = Some (f a)



data Pair a = P a a
  deriving Show

mapPair :: (a -> b) -> (Pair a -> Pair b)
mapPair f (P v1 v2) = P (f v1) (f v2)



data Quad a = Q a a a a
  deriving Show

mapQuad :: (a -> b) -> (Quad a -> Quad b)
mapQuad f (Q q1 q2 q3 q4) = Q (f q1) (f q2) (f q3) (f q4)



data Annotated e a = a :# e
  deriving Show
infix 0 :#

mapAnnotated :: (a -> b) -> (Annotated e a -> Annotated e b)
mapAnnotated f (a :# e) = f a :# e



data Except e a = Error e | Success a
  deriving Show

mapExcept :: (a -> b) -> (Except e a -> Except e b)
mapExcept _ (Error e)   = Error e
mapExcept f (Success a) = Success (f a)



data Prioritised a = Low a | Medium a | High a
  deriving Show

mapPrioritised :: (a -> b) -> (Prioritised a -> Prioritised b)
mapPrioritised f (Low v)    = Low (f v)
mapPrioritised f (Medium v) = Medium (f v)
mapPrioritised f (High v)   = High (f v)



data Stream a = a :> Stream a
  deriving Show
infixr 5 :>

mapStream :: (a -> b) -> (Stream a -> Stream b)
mapStream f (a :> sa) = f a :> mapStream f sa



data List a = Nil | a :. List a
  deriving Show
infixr 5 :.

mapList :: (a -> b) -> (List a -> List b)
mapList _ Nil       = Nil
mapList f (a :. xa) = f a :. mapList f xa



data Fun i a = F (i -> a)

mapFun :: (a -> b) -> (Fun i a -> Fun i b)
mapFun f (F g) = F (f . g)



data Tree a = Leaf | Branch (Tree a) a (Tree a)  
  deriving Show

mapTree :: (a -> b) -> (Tree a -> Tree b)
mapTree _ Leaf = Leaf
mapTree f (Branch l v r) = Branch (mapTree f l) (f v) (mapTree f r)