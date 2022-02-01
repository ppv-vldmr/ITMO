module HW0.T6 where

import           Data.Char (isSpace)
import           HW0.T1    (distrib)

--I think it's not critical if I add a type description to the declared variables.
-- Despite the IDE's advice, 
-- I did not change their names in order to meet the task condition :)

a :: (Either [Char] b, Either [Char] c)
a = distrib (Left ("AB" ++ "CD" ++ "EF"))

b :: [Bool]
b = map isSpace "Hello, World"

c :: [Char]
c = if 1 > 0 || error "X" then "Y" else "Z"

a_whnf :: (Either [Char] b1, Either [Char] b2)
b_whnf :: [Bool]
c_whnf :: [Char]

a_whnf = (Left ("AB" ++ "CD" ++ "EF"),  Left ("AB" ++ "CD" ++ "EF"))
b_whnf = False : map isSpace "ello, World"
c_whnf = "Y"
