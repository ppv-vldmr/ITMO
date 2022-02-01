{-# LANGUAGE DerivingStrategies #-}
{-# LANGUAGE GeneralizedNewtypeDeriving #-}

module HW2.T6
       (ParseError (..),
       Parser (..),
       module HW2.T5,
       module HW2.T4,
       runP,
       pChar,
       pEof,
       pAbbr,
       parseExpr
       ) where

import GHC.Natural (Natural)
import HW2.T5 (ExceptState (..), Except (..), runES, Annotated (..), Expr (..))
import HW2.T4 (Prim (..))
import Control.Applicative
import Control.Monad
import Data.Char
import Data.Scientific
import Data.Maybe (fromMaybe)
  
  
data ParseError = ErrorAtPos Natural

newtype Parser a = P (ExceptState ParseError (Natural, String) a)
  deriving newtype (Functor, Applicative, Monad)
  
  
  
runP :: Parser a -> String -> Except ParseError a  
runP (P p) st = case runES p (0, st) of
                Error e -> Error e
                Success (a :# _) -> Success a
                
              
              
-- This is a function that parses a single character. 
-- If the input is empty, an error will be thrown, 
-- otherwise the parser will receive a symbol with its position in the annotation.
pChar :: Parser Char
pChar = P $ ES $ \(pos, s) ->
  case s of
    []     -> Error (ErrorAtPos pos)
    (c:cs) -> Success (c :# (pos + 1, cs))   
    
    
    
parseError :: Parser a
parseError = P $ ES $ \(pos, _) -> Error (ErrorAtPos pos)   

instance Alternative Parser where
  empty = parseError
  (P p) <|> (P q) = P $ ES $ \st -> case runES p st of
                              Success (a :# an) -> Success (a :# an)
                              Error _ -> runES q st
                            
instance MonadPlus Parser



pEof :: Parser ()
pEof = P $ ES $ \(pos, s) ->
  case s of
      []     -> Success (() :# (pos, []))
      (_:_) -> Error (ErrorAtPos pos)
      
      
      
pAbbr :: Parser String
pAbbr = do
  abbr <- some (mfilter Data.Char.isUpper pChar)
  pEof
  pure abbr
  
        

parseExpr :: String -> Except ParseError Expr
parseExpr st = runP parseSyntax st
  
parseSyntax :: Parser Expr
parseSyntax = do
  abbr <- parsePlusMinus
  pEof
  return abbr
  
skipSpaces :: Parser ()
skipSpaces = void (many (mfilter isSpace pChar))

num :: Parser [Char]
num = some (mfilter isDigit pChar)

arrToInteger :: [Char] -> Integer -> Integer
arrToInteger [] balance = balance
arrToInteger (x:xs) balance = arrToInteger xs (balance * 10 + toInteger (digitToInt x))   

parseDouble :: Parser Expr
parseDouble = do
  skipSpaces
  i1 <- num
  c <- pChar
  if (c == '.') then do
    i2 <- num
    return $ Val $ toRealFloat $ scientific (arrToInteger (i1 ++ i2) 0) (- length i2) 
  else
    return $ Val $ toRealFloat $ scientific (arrToInteger i1 0) 0

parseInfinitePlusMinus :: Expr -> Parser Expr
parseInfinitePlusMinus a = do
  s <- optional $ do 
    skipSpaces
    c <- mfilter (== '+') pChar <|> mfilter (== '-') pChar
    i <- parseMulDiv
    if c == '+'
    then return $ Op (Add a i)
    else return $ Op (Sub a i)  
  case s of 
    Nothing -> return a 
    Just b -> (parseInfinitePlusMinus b)

parsePlusMinus :: Parser Expr
parsePlusMinus = do
  skipSpaces
  i1 <- parseMulDiv
  parseInfinitePlusMinus i1 
  
parseInfiniteMulDiv :: Expr -> Parser Expr
parseInfiniteMulDiv a = do
  s <- optional $ do 
    skipSpaces
    c <- mfilter (== '*') pChar <|> mfilter (== '/') pChar
    i <- parseVal
    if c == '*'
    then return $ Op (Mul a i)
    else return $ Op (Div a i)  
  case s of 
    Nothing -> return a 
    Just b -> (parseInfiniteMulDiv b)  
  
parseMulDiv :: Parser Expr
parseMulDiv = do
  skipSpaces
  i1 <- parseVal
  parseInfiniteMulDiv i1
  
parseVal :: Parser Expr
parseVal = do
  skipSpaces
  a <- parseDouble <|> parseBrackets
  skipSpaces
  return a
  
parseBrackets :: Parser Expr
parseBrackets = do
  _ <- mfilter (== '(') pChar
  v <- parsePlusMinus
  _ <- mfilter (== ')') pChar
  return v