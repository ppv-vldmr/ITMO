module HW1.T7 where

  
data ListPlus a = a :+ ListPlus a | Last a
  deriving Show
infixr 5 :+

instance Semigroup (ListPlus a) where
  Last a <> b = a :+ b
  (a :+ b) <> c = a :+ (b <> c)
  
  
data Inclusive a b = This a | That b | Both a b
  deriving Show

instance (Semigroup a, Semigroup b) => Semigroup (Inclusive a b) where
  This i   <> This j     = This (i <> j)
  That i   <> That j     = That (i <> j)
  This i   <> That j     = Both i j
  That i   <> This j     = Both j i 
  This i   <> Both j k   = Both (i <> j) k
  That i   <> Both j k   = Both j (i <> k)
  Both j k <> This i     = Both (j <> i) k
  Both j k <> That i     = Both j (k <> i)
  Both i j <> Both l m   = Both (i <> l) (j <> m)
  
  
newtype DotString = DS String
  deriving Show

instance Semigroup DotString where
  DS [] <> DS [] = DS []
  DS a <> DS [] = DS a
  DS [] <> DS a = DS a
  DS a <> DS b = DS (a ++ "." ++ b)
  
instance Monoid DotString where
    mempty = DS ""
    
    
newtype Fun a = F (a -> a)
      
instance Semigroup (Fun a) where
  (<>) (F a) (F b) = F (a . b)
  
instance Monoid (Fun a) where
  mempty = F id
   