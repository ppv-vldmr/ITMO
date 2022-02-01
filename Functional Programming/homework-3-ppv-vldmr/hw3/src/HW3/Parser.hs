{-# LANGUAGE AllowAmbiguousTypes #-}

module HW3.Parser
  ( parse
  , errorBundlePretty
  ) where

import HW3.Base (HiFun (..), HiValue (..), HiExpr (..))
import Data.Void (Void)
import Text.Megaparsec (ParseErrorBundle, Parsec, runParser, eof, errorBundlePretty, choice, notFollowedBy, MonadParsec, Tokens, manyTill, between, try)
import Text.Megaparsec.Char (char, space1, space, string)
import Text.Megaparsec.Char.Lexer (scientific, signed, charLiteral, skipBlockComment, skipLineComment)
import Control.Applicative ((<|>), optional, many)
import Data.Maybe (fromMaybe)
import Control.Monad.Combinators.Expr (makeExprParser, Operator (InfixL, InfixR, InfixN))
import Data.Text (pack, Text)
import Data.Char
import qualified Text.Megaparsec.Char.Lexer as L
import Data.Sequence.Internal (Seq, singleton)
import Data.Sequence (fromList, empty)

type Parser = Parsec Void String

parse :: String -> Either (ParseErrorBundle String Void) HiExpr
parse = runParser (parseTerm <* eof) "logErr.txt" . dropWhile isSpace

sc :: Parser ()
sc = L.space
  space1
  (skipLineComment "//")
  (skipBlockComment "/*" "*/")

lexeme :: Parser a -> Parser a
lexeme = L.lexeme sc

symbol :: String -> Parser String
symbol = L.symbol sc

parens :: Parser a -> Parser a
parens = between (symbol "(") (symbol ")")

parensList :: Parser a -> Parser a
parensList = between (symbol "[") (symbol "]")

parseTerm :: Parser HiExpr
parseTerm = lexeme $ makeExprParser parseExpression operators

operators :: [[Operator Parser HiExpr]]
operators =
  [ [myBinaryL "*" HiFunMul,-- L
     myBinaryLNotFollowedBy "/" "=" HiFunDiv], -- L
    [myBinaryL "+" HiFunAdd,-- L
     myBinaryL "-" HiFunSub],-- L
    [myBinaryNNotFollowedBy ">" "=" HiFunGreaterThan,-- N
     myBinaryNNotFollowedBy "<" "=" HiFunLessThan,-- N
     myBinaryN ">=" HiFunNotLessThan,-- N
     myBinaryN "<=" HiFunNotGreaterThan,-- N
     myBinaryN "==" HiFunEquals,-- N
     myBinaryN "/=" HiFunNotEquals],-- N
    [myBinaryR "&&" HiFunAnd],-- R
     [myBinaryR "||" HiFunOr]-- R
  ]

myBinaryL :: [Char] -> HiFun -> Operator Parser HiExpr
myBinaryL name f = InfixL $ do
  _ <- lexeme $ string name
  return $ (\a b -> HiExprApply (HiExprValue $ HiValueFunction f) [a, b])

myBinaryN :: [Char] -> HiFun -> Operator Parser HiExpr
myBinaryN name f = InfixN $ do
  _ <- lexeme $ string name
  return $ (\a b -> HiExprApply (HiExprValue $ HiValueFunction f) [a, b])

myBinaryR :: [Char] -> HiFun -> Operator Parser HiExpr
myBinaryR name f = InfixR $ do
  _ <- lexeme $ string name
  return $ (\a b -> HiExprApply (HiExprValue $ HiValueFunction f) [a, b])

myBinaryLNotFollowedBy :: [Char] -> [Char] -> HiFun -> Operator Parser HiExpr
myBinaryLNotFollowedBy name after f = InfixL $ do
  _ <- try . lexeme $ string name <* notFollowedBy (string after)
  return $ (\a b -> HiExprApply (HiExprValue $ HiValueFunction f) [a, b])

myBinaryNNotFollowedBy :: [Char] -> [Char] -> HiFun -> Operator Parser HiExpr
myBinaryNNotFollowedBy name after f = InfixN $ do
  _ <- try . lexeme $ string name <* notFollowedBy (string after)
  return $ (\a b -> HiExprApply (HiExprValue $ HiValueFunction f) [a, b])

parseExpression :: Parser HiExpr
parseExpression = lexeme $ do
  c <- parseHiValue <|> parensList parseList
  parseApply c

parseApply :: HiExpr -> Parser HiExpr
parseApply a = do
  brackets <- optional $ parens parseArguments
  case brackets of
    Just b -> do
      let t = HiExprApply a b
      parseApply t
    Nothing  -> return a
  
parseArguments :: Parser [HiExpr]
parseArguments = do
  x <- parseTerm
  xs <- many $ (lexeme $ char ',') *> parseTerm
  return (x : xs)

parseHiValue :: Parser HiExpr
parseHiValue = lexeme $ (parseValue <|> parens parseTerm)

parseList :: Parser HiExpr
parseList = do
   first <- optional parseTerm
   case first of
     Nothing -> return (HiExprApply (HiExprValue (HiValueFunction HiFunList)) [])
     Just el -> do
       other <- many $ (lexeme $ char ',') *> parseTerm
       return (HiExprApply (HiExprValue (HiValueFunction HiFunList)) (el:other))

parseValue :: Parser HiExpr
parseValue = lexeme $ HiExprValue <$> (parseNull 
  <|> parseBool 
  <|> parseNumber 
  <|> parseString 
  <|> parseFunction)

parseNull :: Parser HiValue
parseNull = do
  _ <- string "null"
  return HiValueNull

parseBool :: Parser HiValue
parseBool = lexeme $ do
   res <- (string "true") <|> (string "false")
   case res of
     "true"  -> return $ (HiValueBool True)
     "false" -> return $ (HiValueBool False)

parseNumber :: Parser HiValue
parseNumber = lexeme $ do
   res <- toRational <$> signed sc scientific
   return (HiValueNumber res)

parseString :: Parser HiValue
parseString = lexeme $ do
   res <- char '\"' *> manyTill charLiteral (char '\"')
   return (HiValueString (pack res))

parseFunction :: Parser HiValue
parseFunction = lexeme $ HiValueFunction <$> choice
  [ pStr "mul" HiFunMul
  , pStr "div" HiFunDiv
  , pStr "add" HiFunAdd
  , pStr "sub" HiFunSub
  , pStr "and" HiFunAnd
  , pStr "or" HiFunOr
  , pStr "less-than" HiFunLessThan
  , pStr "greater-than" HiFunGreaterThan
  , pStr "equals" HiFunEquals
  , pStr "not-less-than" HiFunNotLessThan
  , pStr "not-greater-than" HiFunNotGreaterThan
  , pStr "not-equals" HiFunNotEquals
  , pStr "if" HiFunIf
  , pStr "length" HiFunLength
  , pStr "to-upper" HiFunToUpper
  , pStr "to-lower" HiFunToLower
  , pStr "reverse" HiFunReverse
  , pStr "trim" HiFunTrim
  , pStr "not" HiFunNot
  , pStr "list" HiFunList
  , pStr "range" HiFunRange
  , pStr "fold" HiFunFold]
  where pStr s r = string s >> return r