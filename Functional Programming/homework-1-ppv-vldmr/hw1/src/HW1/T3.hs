module HW1.T3
       ( Tree(..),
         tsize,
         tdepth,
         tmember,
         tinsert,
         tFromList
       ) where


data Tree a = Leaf | Branch Int (Tree a) a (Tree a) 
    deriving (Show, Eq, Ord, Read)


mkBranch :: Tree a -> a -> Tree a -> Tree a
mkBranch Leaf a Leaf = Branch 1 Leaf a Leaf
mkBranch Leaf a (Branch size l v r) = Branch (size + 1) Leaf a (Branch size l v r)
mkBranch (Branch size l v r) a Leaf = Branch (size + 1) (Branch size l v r) a Leaf
mkBranch (Branch size1 l1 v1 r1) a (Branch size2 l2 v2 r2) = Branch (size1 + size2 + 1) (Branch size1 l1 v1 r1) a (Branch size2 l2 v2 r2)
                  
                  
-- | Size of the tree, O(1).
tsize :: Tree a -> Int
tsize Leaf = 0
tsize (Branch s _ _ _) = s
    
tleft :: Tree a -> Tree a
tleft Leaf = Leaf
tleft (Branch _ l _ _) = l

tright :: Tree a -> Tree a
tright Leaf = Leaf
tright (Branch _ _ _ r) = r

tvalue :: Tree a -> a
tvalue Leaf = undefined
tvalue (Branch _ _ v _) = v  


-- | Depth of the tree.
tdepth :: Tree a -> Int
tdepth Leaf = 0
tdepth (Branch _ l _ r) = max (tdepth l) (tdepth r) + 1


-- | Check if the element is in the tree, O(log n)
tmember :: Ord a => a -> Tree a -> Bool
tmember _ Leaf = False
tmember x (Branch _ left value right) | x == value = True
                                      | x < value = tmember x left
                                      | otherwise = tmember x right


-- | Insert an element into the tree, O(log n)
tinsert :: Ord a => a -> Tree a -> Tree a
tinsert a Leaf = mkBranch Leaf a Leaf
tinsert x (Branch size left value right) | value < x = rotate (mkBranch left value (tinsert x right))
                                         | value > x = rotate (mkBranch (tinsert x left) value right)
                                         | otherwise = Branch size left value right

singleRotationLeft :: Tree a -> Tree a
singleRotationLeft Leaf = Leaf
singleRotationLeft (Branch _ l n r) = mkBranch (mkBranch l n (tleft r)) (tvalue r) (tright r)

singleRotationRight :: Tree a -> Tree a
singleRotationRight Leaf = Leaf
singleRotationRight (Branch _ l n r) = mkBranch (tleft l) (tvalue l) (mkBranch (tright l) n r)

doubleRotationLeft :: Tree a -> Tree a
doubleRotationLeft Leaf = Leaf
doubleRotationLeft (Branch _ l n r) = mkBranch (mkBranch l n (tleft (tleft r))) (tvalue (tleft r)) (mkBranch (tright (tleft r)) (tvalue r) (tright r))

doubleRotationRight :: Tree a -> Tree a
doubleRotationRight Leaf = Leaf
doubleRotationRight (Branch _ l n r) = mkBranch (mkBranch (tleft l) (tvalue l) (tleft (tright l))) (tvalue (tright l)) (mkBranch (tright (tright l)) n r)

rotate :: Ord a => Tree a -> Tree a
rotate Leaf = Leaf
rotate (Branch _ l n r) | not (balanced l) = mkBranch (rotate l) n r
                        | not (balanced r) = mkBranch l n (rotate r)
                        | tdepth l + 1 < tdepth r && tdepth (tleft r) < tdepth (tright r) = singleRotationLeft (mkBranch l n r)
                        | tdepth r + 1 < tdepth l && tdepth (tright l) < tdepth (tleft l) = singleRotationRight (mkBranch l n r)
                        | tdepth l + 1 < tdepth r && tdepth (tleft r) > tdepth (tright r) = doubleRotationLeft (mkBranch l n r)
                        | tdepth r + 1 < tdepth l && tdepth (tright l)  > tdepth (tleft l) = doubleRotationRight (mkBranch l n r)
                        | otherwise = mkBranch l n r

balanced :: Tree a -> Bool
balanced Leaf = True
balanced (Branch _ l _ r) | not (balanced l) || not (balanced r) = False
                          | abs (tdepth l - tdepth r) > 1 = False
                          | otherwise = True


-- | Build a tree from a list, O(n log n)
tFromList :: Ord a => [a] -> Tree a     
tFromList [] = Leaf
tFromList (x:xs) = foldr tinsert Leaf (x:xs)