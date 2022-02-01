module HW2.T4
       ( module HW2.T1,
       Control.Monad.ap,
       mapState,
       wrapState,
       joinState,
       modifyState,
       State (..),
       Prim (..),
       Expr (..),
       eval
       ) where

import HW2.T1
import Control.Monad (ap)

data State s a = S { runS :: s -> Annotated s a }

mapState :: (a -> b) -> State s a -> State s b
mapState f s = S $ (\i -> let (a :# s1) = runS s i in (f a) :# s1)

wrapState :: a -> State s a
wrapState a = S $ (\i -> a :# i)

joinState :: State s (State s a) -> State s a
joinState s = S $ (\i -> let (S a :# s1) = runS s i in a s1)

modifyState :: (s -> s) -> State s ()
modifyState f = S $ (\s -> () :# (f s))

instance Functor (State s) where
  fmap = mapState

instance Applicative (State s) where
  pure = wrapState
  p <*> q = Control.Monad.ap p q

instance Monad (State s) where
  m >>= f = joinState (fmap f m)
  
  
 
data Prim a =
    Add a a      -- (+)
  | Sub a a      -- (-)
  | Mul a a      -- (*)
  | Div a a      -- (/)
  | Abs a        -- abs
  | Sgn a        -- signum

data Expr = Val Double | Op (Prim Expr)

instance Num Expr where
  x + y    = Op (Add x y)
  x * y    = Op (Mul x y)
  x - y    = Op (Sub x y)
  abs x    = Op (Abs x)
  signum x = Op (Sgn x)
  fromInteger x = Val (fromInteger x)

instance Fractional Expr where  
  x / y = Op (Div x y)
  fromRational x = Val (fromRational x)
  
  
eval :: Expr -> State [Prim Double] Double  
eval (Val v) = return v

eval (Op (Add x y)) = do
  i1 <- eval x
  i2 <- eval y
  modifyState(Add i1 i2 :)
  return (i1 + i2)
  
eval (Op (Sub x y)) = do
  i1 <- eval x
  i2 <- eval y
  modifyState(Sub i1 i2 :)
  return (i1 - i2) 

eval (Op (Mul x y)) = do
  i1 <- eval x
  i2 <- eval y
  modifyState(Mul i1 i2 :)
  return (i1 * i2)  
  
eval (Op (Div x y)) = do
  i1 <- eval x
  i2 <- eval y
  modifyState(Div i1 i2 :)
  return (i1 / i2)   
  
eval (Op (Abs x)) = do
  i <- eval x
  modifyState(Abs i :)
  return (abs i)   
  
eval (Op (Sgn x)) = do
  i <- eval x
  modifyState(Sgn i :)
  return $ (signum i) 