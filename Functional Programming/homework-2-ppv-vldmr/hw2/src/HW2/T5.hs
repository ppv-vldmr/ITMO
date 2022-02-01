module HW2.T5
       ( module HW2.T1,
       module HW2.T4,
       ExceptState (..),
       Control.Monad.ap,
       mapExceptState,
       wrapExceptState,
       joinExceptState,
       modifyExceptState,
       throwExceptState,
       EvaluationError (..),
       eval
       ) where

import HW2.T1 (Annotated (..), Except (..))
import HW2.T4 (Expr (..), Prim (..))
import Control.Monad (ap)


  
data ExceptState e s a = ES { runES :: s -> Except e (Annotated s a) }  



mapExceptState :: (a -> b) -> ExceptState e s a -> ExceptState e s b
mapExceptState f s = ES $ (\i -> case (runES s i) of
                                 (Error e) -> Error e
                                 (Success (v :# an)) -> Success ((f v) :# an))                              



wrapExceptState :: a -> ExceptState e s a
wrapExceptState a = ES $ (\i -> Success (a :# i))



joinExceptState :: ExceptState e s (ExceptState e s a) -> ExceptState e s a
joinExceptState s = ES $ (\i -> case (runES s i) of
                          (Error e) -> Error e
                          (Success (ES v :# an)) -> v an)



modifyExceptState :: (s -> s) -> ExceptState e s ()
modifyExceptState f = ES $ (\s -> Success (() :# (f s)))



throwExceptState :: e -> ExceptState e s a
throwExceptState e = ES $ (\_ -> Error e)



instance Functor (ExceptState e s) where
  fmap = mapExceptState
  
instance Applicative (ExceptState e s) where
  pure = wrapExceptState
  p <*> q = Control.Monad.ap p q
  
instance Monad (ExceptState e s) where
  m >>= f = joinExceptState (fmap f m)
  
  
  
data EvaluationError = DivideByZero

eval :: Expr -> ExceptState EvaluationError [Prim Double] Double 
eval (Val v) = return v

eval (Op (Add x y)) = do
  i1 <- eval x
  i2 <- eval y
  modifyExceptState(Add i1 i2 :)
  return (i1 + i2)
  
eval (Op (Sub x y)) = do
  i1 <- eval x
  i2 <- eval y
  modifyExceptState(Sub i1 i2 :)
  return (i1 - i2)   
  
eval (Op (Mul x y)) = do
  i1 <- eval x
  i2 <- eval y
  modifyExceptState(Mul i1 i2 :)
  return (i1 * i2)   
  
eval (Op (Div x y)) = do
  i1 <- eval x
  i2 <- eval y
  if (i2 /= 0) then modifyExceptState(Div i1 i2 :) else throwExceptState (DivideByZero)
  if (i2 /= 0) then return (i1 / i2) else throwExceptState DivideByZero
  
eval (Op (Abs x)) = do
  i <- eval x
  modifyExceptState(Abs i :)
  return (abs i)
  
eval (Op (Sgn x)) = do
  i <- eval x
  modifyExceptState(Sgn i :)
  return $ (signum i)    