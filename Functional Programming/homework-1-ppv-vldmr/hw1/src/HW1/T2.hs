module HW1.T2
       ( N(..),
         nplus,
         nmult,
         nsub,
         ncmp,
         nFromNatural,
         nToNum,
         nEven,
         nOdd,
         ndiv,
         nmod
       ) where

import Numeric.Natural (Natural)


  
data N = Z | S N
    deriving Show

nplus :: N -> N -> N        -- addition
nplus Z Z = Z
nplus Z (S a) = S a
nplus (S a) Z = S a
nplus (S a) (S b) = S (S (nplus a b))

nmult :: N -> N -> N        -- multiplication
nmult Z Z = Z
nmult Z _ = Z
nmult _ Z = Z
nmult (S a) (S b) = nplus (S b) (nmult a (S b))

nsub :: N -> N -> Maybe N   -- subtraction     (Nothing if result is negative)
nsub Z Z = Just Z
nsub Z (S _) = Nothing
nsub (S a) Z = Just (S a)
nsub (S a) (S b) = nsub a b

ncmp :: N -> N -> Ordering  -- comparison      (Do not derive Ord)
ncmp Z Z = EQ
ncmp (S _) Z = GT
ncmp Z (S _) = LT
ncmp (S a) (S b) = ncmp a b

buildN :: Natural -> N -> N
buildN 0 a = a
buildN x a = buildN (x - 1) (S a)

nFromNatural :: Natural -> N
nFromNatural x = buildN x Z

nToNum :: Num a => N -> a
nToNum Z = 0
nToNum (S a) = 1 + nToNum a

-- Advanced

nEven, nOdd :: N -> Bool    -- parity checking
nEven Z = True
nEven (S Z) = False
nEven (S (S a)) = nEven a

nOdd a = not (nEven a)

getDiv :: (N, N) -> N
getDiv (a, _) = a
  
getMod :: (N, N) -> N
getMod (_, b) = b 

divide :: N -> N -> N -> (N, N)
divide _ Z _ = undefined
divide Z _ c = (c, Z)
divide a b c = case subRes of
               Just x -> divide x b (S c)
               Nothing -> (c, a)
               where subRes = nsub a b
               
ndiv :: N -> N -> N         -- integer division
ndiv a b = getDiv (divide a b Z)

nmod :: N -> N -> N         -- modulo operation
nmod a b = getMod (divide a b Z)