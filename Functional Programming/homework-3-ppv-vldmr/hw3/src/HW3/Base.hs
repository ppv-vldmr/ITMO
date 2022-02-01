module HW3.Base 
  ( HiFun (..)
  , HiValue (..)
  , HiExpr (..)
  , HiError (..)
  , HiMonad (..)
  ) where

import Data.Text (Text)
import Data.ByteString.Char8 (ByteString)
import Data.Sequence.Internal (Seq)


 -- function names (e.g. div, sort, length, ...)
data HiFun =
    HiFunDiv
  | HiFunMul
  | HiFunAdd
  | HiFunSub
  | HiFunNot
  | HiFunAnd
  | HiFunOr
  | HiFunLessThan
  | HiFunGreaterThan
  | HiFunEquals
  | HiFunNotLessThan
  | HiFunNotGreaterThan
  | HiFunNotEquals
  | HiFunIf
  | HiFunLength
  | HiFunToUpper
  | HiFunToLower
  | HiFunReverse
  | HiFunTrim
  | HiFunList
  | HiFunRange
  | HiFunFold
  deriving (Eq, Ord)

instance Show HiFun where
  show HiFunDiv            = "div"
  show HiFunMul            = "mul"
  show HiFunAdd            = "add"
  show HiFunSub            = "sub"
  show HiFunNot            = "not"
  show HiFunAnd            = "and"
  show HiFunOr             = "or"
  show HiFunLessThan       = "less-than"
  show HiFunGreaterThan    = "greater-than"
  show HiFunEquals         = "equals"
  show HiFunNotLessThan    = "not-less-than"
  show HiFunNotGreaterThan = "not-greater-than"
  show HiFunNotEquals      = "not-equals"
  show HiFunIf             = "if"
  show HiFunLength         = "length"
  show HiFunToUpper        = "to-upper"
  show HiFunToLower        = "to-lower"
  show HiFunReverse        = "reverse"
  show HiFunTrim           = "trim"
  show HiFunList           = "list"
  show HiFunRange          = "range"
  show HiFunFold           = "fold"

 -- values (numbers, booleans, strings, ...)
data HiValue =
    HiValueBool Bool
  | HiValueNumber Rational
  | HiValueFunction HiFun
  | HiValueNull
  | HiValueString Text
  | HiValueList (Seq HiValue)
  deriving (Show, Eq, Ord)

 -- expressions (literals, function calls, ...)
data HiExpr =
    HiExprValue HiValue
  | HiExprApply HiExpr [HiExpr]
  deriving (Show, Eq, Ord)

 -- evaluation errors (invalid arguments, ...)
data HiError =
    HiErrorInvalidArgument
  | HiErrorInvalidFunction
  | HiErrorArityMismatch
  | HiErrorDivideByZero
  deriving (Show, Eq, Ord)

data HiAction =
    HiActionRead  FilePath
  | HiActionWrite FilePath ByteString
  | HiActionMkDir FilePath
  | HiActionChDir FilePath
  | HiActionCwd

class Monad m => HiMonad m where
  runAction :: HiAction -> m HiValue