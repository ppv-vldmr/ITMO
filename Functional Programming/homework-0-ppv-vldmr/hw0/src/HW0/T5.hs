module HW0.T5 where

import           Data.Function   (fix)
import           Numeric.Natural (Natural)

type Nat a = (a -> a) -> a -> a

nz :: Nat a
nz _ a = a

ns :: Nat a -> Nat a
ns n func arg = n func (func arg)

nplus :: Nat a -> Nat a -> Nat a
nplus k1 k2 func s = k1 func (k2 func s)

nmult :: Nat a -> Nat a -> Nat a
nmult a b = a . b

nFromNatural :: Natural -> Nat a
nFromNatural cnt func arg = if cnt > 0
                 then nFromNatural (cnt - 1) func (func arg)
                 else arg

nToNum :: Num a => Nat a -> a
nToNum cnt = cnt (+ 1) 0