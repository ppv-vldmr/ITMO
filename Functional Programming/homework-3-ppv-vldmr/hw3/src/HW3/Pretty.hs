module HW3.Pretty 
  ( prettyValue
  ) where

import HW3.Base (HiValue (..))
import Prettyprinter (Doc, pretty, slash, prettyList, list, (<+>), encloseSep)
import Prettyprinter.Render.Terminal (AnsiStyle)
import Data.Scientific (fromRationalRepetendUnlimited, floatingOrInteger, formatScientific, FPFormat (Fixed), scientific, fromFloatDigits)
import Data.Ratio
import Data.Text (unpack)
import Data.Sequence.Internal (Seq, empty)
import Data.Foldable (toList)

prettyValue :: HiValue -> Doc AnsiStyle
prettyValue val = case val of
                    (HiValueFunction f) -> pretty $ show f
                    (HiValueNull) -> pretty "null"
                    (HiValueString s) -> pretty ("\"" ++ (unpack s) ++ "\"")
                    (HiValueNumber n) ->
                       case mbR of
                         Nothing -> prettyFiniteNumber $ floatingOrInteger s
                         Just _  -> prettyInfiniteNumber n
                       where (s, mbR) = fromRationalRepetendUnlimited n
                    (HiValueBool v) -> case v of
                                         True -> pretty "true"
                                         False -> pretty "false"
                    (HiValueList a) -> case (toList a) of
                                         [] -> prettyList "[ ]"
                                         x:xs -> encloseSep (pretty "[ ") (pretty " ]") (pretty ", ") (map (prettyValue) (x:xs))

    
prettyFiniteNumber :: Either Double Integer -> Doc AnsiStyle
prettyFiniteNumber v = case v of
                         (Left f) -> pretty $ formatScientific Fixed Nothing (fromFloatDigits f)
                         (Right i) -> pretty i

prettyInfiniteNumber :: Rational -> Doc AnsiStyle
prettyInfiniteNumber s
  | z == 0    = 
    ( if signed 
      then pretty "-" 
      else pretty "") <> prettyR
  | otherwise =
    prettyZ <> 
    (if signed 
      then pretty " - " 
      else pretty " + ") <> prettyR
  where
    (signed, positive) = if s < 0 then (True, -s) else (False, s)
    num = numerator positive
    den = denominator positive
    (z, r) = quotRem num den

    prettyZ = (if signed then pretty "-" else pretty "") <> pretty z
    prettyR = pretty r <> slash <> pretty den
