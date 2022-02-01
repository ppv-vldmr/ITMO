module HW0.T4 where

import Data.Function (fix)
import GHC.Natural (Natural)

repeat' :: a -> [a]
repeat' x = fix (x:)

map' :: (a -> b) -> [a] -> [b]
map' = fix (\g f list -> case list of
                           a:as -> f a : g f as
                           _ -> [])

fib :: Natural -> Natural
fib = fix (\f prev prevprev n -> if n > 0
                       then f prevprev (prev + prevprev) (n - 1)
                       else prev) 0 1 

fac :: Natural -> Natural
fac = fix (\f prev pow -> if pow > 0
                       then f (prev * pow) (pow - 1)
                       else prev) 1