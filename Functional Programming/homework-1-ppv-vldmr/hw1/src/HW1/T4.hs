module HW1.T4 
       ( module HW1.T3,
         tfoldr,
         treeToList,
         Foldable
       ) where
  
import HW1.T3

tfoldr :: (a -> b -> b) -> b -> Tree a -> b
tfoldr _ z Leaf = z
tfoldr f z (Branch _ left v right) = tfoldr f (f v (tfoldr f z right)) left

instance Foldable Tree where
  foldr = tfoldr

treeToList :: Tree a -> [a]    -- output list is sorted
treeToList = tfoldr (:) []