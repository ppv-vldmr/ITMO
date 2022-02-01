module HW1.T5 where


import Data.List.NonEmpty(NonEmpty(..))


split :: Eq a => a -> [a] -> [[a]]
split _ [] = [[]]
split c s = l : if null r
                then []
                else split c (case r of
                              (_:xs) -> xs
                              []     -> [])
            where l = takeWhile (/= c) s
                  r = dropWhile (/= c) s

splitOn :: Eq a => a -> [a] -> NonEmpty [a]
splitOn c s = x :| xs
  where (x:xs) = split c s

join :: a -> [[a]] -> [a]
join _ [] = []
join d xs = foldr (\s1 -> ((d:s1) ++)) [] xs

joinWith :: a -> NonEmpty [a] -> [a]
joinWith _ (a:|[]) = a
joinWith x (a:|xa) = a ++ foldr (\element -> ((x:element) ++)) [] xa