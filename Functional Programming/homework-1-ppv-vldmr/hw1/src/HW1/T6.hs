module HW1.T6 where

import Data.Maybe
import Data.Foldable
import Data.Either (lefts, rights)
  
mcat :: Monoid a => [Maybe a] -> a
mcat a = foldMap fromJust (filter isJust a)                                                                      

epart :: (Monoid a, Monoid b) => [Either a b] -> (a, b)
epart x = (fold (lefts x), fold (rights x))