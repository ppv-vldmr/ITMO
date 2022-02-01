module HW3.Evaluator 
  ( eval
  ) where

import HW3.Base (HiFun (..), HiExpr (..), HiError (..), HiValue (..), HiMonad (..))
import Control.Monad.Trans.Except
import Data.Text (unpack, pack, toUpper, toLower, strip, intercalate, index, Text)
import qualified Data.Text (take, reverse)
import Data.Semigroup (stimes, sconcat)
import Data.Ratio (numerator)
import Data.Scientific (fromRationalRepetendUnlimited, isInteger, toDecimalDigits)
import Data.Sequence (fromList, Seq (..))
import Data.Foldable (toList)
import Control.Monad.Cont (foldM)


eval :: HiMonad m => HiExpr -> m (Either HiError HiValue)
eval = runExceptT . evalHiExpr

evalHiExpr :: HiMonad m => HiExpr -> ExceptT HiError m HiValue
evalHiExpr expr = do
  case expr of
    (HiExprValue a) -> return a
    (HiExprApply a b) -> do

      func <- evalHiExpr a
      case func of
        (HiValueString s) -> do
          case b of
            [HiExprValue (HiValueNumber arg)] -> do
              let idx = (fromInteger $ numerator arg)
              if idx >= 0 && idx < (length (unpack s))
              then return $ HiValueString (pack [(index s idx)])
              else return $ HiValueNull

            [HiExprApply f args] -> do
              pF <- evalHiExpr f
              pArgs <- mapM evalHiExpr args
              res <- evalHiExpr (HiExprApply (HiExprValue pF) (map (HiExprValue) pArgs))
              evalHiExpr (HiExprApply (HiExprValue (HiValueString s)) [(HiExprValue res)])

            [arg1, arg2] -> do
              from <- evalHiExpr arg1
              to <- evalHiExpr arg2
              case (from, to) of
                ((HiValueNumber idxL), (HiValueNumber idxR)) -> do
                  let intIdxL = (fromInteger $ numerator idxL)
                  let intIdxR = (fromInteger $ numerator idxR)
                  if (intIdxL >= 0)
                  then
                    if (intIdxR >= 0)
                    then
                      return $ HiValueString (slice intIdxL intIdxR s)
                    else
                      return $ HiValueString (slice intIdxL (length (unpack s) - (abs intIdxR)) s)
                  else
                    if (intIdxR >= 0)
                    then
                      return $ HiValueString (slice (length (unpack s) - (abs intIdxL)) intIdxR s)
                    else
                      return $ HiValueString (slice (length (unpack s) - (abs intIdxL)) (length (unpack s) - (abs intIdxR)) s)
                (HiValueNull, (HiValueNumber idxR)) -> do
                  let intIdxR = (fromInteger $ numerator idxR)
                  if (intIdxR >= 0)
                  then return $ HiValueString (slice 0 intIdxR s)
                  else return $ HiValueString (slice 0 (length (unpack s) - (abs intIdxR)) s)
                ((HiValueNumber idxL), HiValueNull) -> do
                  let intIdxL = (fromInteger $ numerator idxL)
                  let intIdxR = (length (unpack s))
                  if (intIdxL >= 0)
                  then return $ HiValueString (slice intIdxL intIdxR s)
                  else return $ HiValueString (slice (abs intIdxL) intIdxR s)
                (HiValueNull, HiValueNull) -> return $ HiValueString s
                _ -> throwE HiErrorInvalidArgument

            _ -> throwE HiErrorInvalidArgument

        (HiValueList s) -> do
          case b of
            [HiExprValue (HiValueNumber arg)] -> do
              let idx = (fromInteger $ numerator arg)
              if idx >= 0 && idx < (length s)
              then return $ ((toList s) !! idx)
              else return $ HiValueNull

            [HiExprApply f args] -> do
              pF <- evalHiExpr f
              pArgs <- mapM evalHiExpr args
              res <- evalHiExpr (HiExprApply (HiExprValue pF) (map (HiExprValue) pArgs))
              evalHiExpr (HiExprApply (HiExprValue (HiValueList s)) [(HiExprValue res)])

            [arg1, arg2] -> do
              from <- evalHiExpr arg1
              to <- evalHiExpr arg2
              case (from, to) of
                ((HiValueNumber idxL), (HiValueNumber idxR)) -> do
                  let intIdxL = (fromInteger $ numerator idxL)
                  let intIdxR = (fromInteger $ numerator idxR)
                  if (intIdxL >= 0)
                  then
                    if (intIdxR >= 0)
                    then
                      return $ HiValueList (fromList (sliceList intIdxL intIdxR (toList s)))
                    else
                      return $ HiValueList (fromList (sliceList intIdxL ((length (toList s)) - (abs intIdxR)) (toList s)))
                  else
                    if (intIdxR >= 0)
                    then
                      return $ HiValueList (fromList (sliceList (length (toList s) - (abs intIdxL)) intIdxR (toList s)))
                    else
                      return $ HiValueList (fromList (sliceList (length (toList s) - (abs intIdxL)) (length (toList s) - (abs intIdxR)) (toList s)))
                (HiValueNull, (HiValueNumber idxR)) -> do
                  let intIdxR = (fromInteger $ numerator idxR)
                  if (intIdxR >= 0)
                  then return $ HiValueList $ fromList $ (sliceList 0 intIdxR (toList s))
                  else return $ HiValueList $ fromList $ (sliceList 0 (length (toList s) - (abs intIdxR)) (toList s))
                ((HiValueNumber idxL), HiValueNull) -> do
                  let intIdxL = (fromInteger $ numerator idxL)
                  let intIdxR = (length (toList s))
                  if (intIdxL >= 0)
                  then return $ HiValueList $ fromList $ (sliceList intIdxL intIdxR (toList s))
                  else return $ HiValueList $ fromList $ (sliceList (abs intIdxL) intIdxR (toList s))
                (HiValueNull, HiValueNull) -> return $ HiValueList s
                _ -> throwE HiErrorInvalidArgument

            _ -> throwE HiErrorInvalidArgument

        (HiValueFunction HiFunIf) -> do
          evalFuncLazy HiFunIf b

        (HiValueFunction HiFunAnd) -> do
          evalFuncLazy HiFunAnd b

        (HiValueFunction HiFunOr) -> do
          evalFuncLazy HiFunOr b

        (HiValueFunction f) -> do
          args <- mapM evalHiExpr b
          evalFunc f args

        _ -> throwE HiErrorInvalidFunction

evalFunc :: HiMonad m => HiFun -> [HiValue] -> ExceptT HiError m HiValue

evalFunc HiFunAdd [HiValueNumber n1, HiValueNumber n2] = return $ HiValueNumber (n1 + n2)
evalFunc HiFunAdd [HiValueString n1, HiValueString n2] = return $ HiValueString (pack ((unpack n1) ++ (unpack n2)))
evalFunc HiFunAdd [HiValueList n1, HiValueList n2] = return $ HiValueList (fromList ((toList n1) ++ (toList n2)))
evalFunc HiFunAdd [_, _] = throwE HiErrorInvalidArgument
evalFunc HiFunAdd _ = throwE HiErrorArityMismatch

evalFunc HiFunSub [HiValueNumber n1, HiValueNumber n2] = return $ HiValueNumber (n1 - n2)
evalFunc HiFunSub [_, _] = throwE HiErrorInvalidArgument
evalFunc HiFunSub _ = throwE HiErrorArityMismatch

evalFunc HiFunMul [HiValueNumber n1, HiValueNumber n2] = return $ HiValueNumber (n1 * n2)
evalFunc HiFunMul [HiValueString n1, HiValueNumber n2] = do
  let (s, mbR) = fromRationalRepetendUnlimited n2
  case mbR of
    Nothing -> do
      case isInteger s of
        True -> do
          let res = fromInteger $ numerator n2
          if (res > 0)
          then return $ HiValueString (stimes res n1)
          else throwE HiErrorInvalidArgument
        False -> throwE HiErrorInvalidArgument
    Just _ -> throwE HiErrorInvalidArgument
evalFunc HiFunMul [HiValueList n1, HiValueNumber n2] = do
  let (s, mbR) = fromRationalRepetendUnlimited n2
  case mbR of
    Nothing -> do
      case isInteger s of
        True -> do
          let res = fromInteger $ numerator n2
          if (res > 0)
          then return $ HiValueList (stimes res n1)
          else throwE HiErrorInvalidArgument
        False -> throwE HiErrorInvalidArgument
    Just _ -> throwE HiErrorInvalidArgument
evalFunc HiFunMul [_, _] = throwE HiErrorInvalidArgument
evalFunc HiFunMul _ = throwE HiErrorArityMismatch

evalFunc HiFunDiv [HiValueNumber n1, HiValueNumber n2] = if (n2 == 0)
                                                         then throwE HiErrorDivideByZero
                                                         else return $ HiValueNumber (n1 / n2)
evalFunc HiFunDiv [HiValueString n1, HiValueString n2] = return $ HiValueString (pack ((unpack n1) ++ "/" ++ (unpack n2)))
evalFunc HiFunDiv [_, _] = throwE HiErrorInvalidArgument
evalFunc HiFunDiv _ = throwE HiErrorArityMismatch

evalFunc HiFunAnd [HiValueBool v1, HiValueBool v2] = return $ HiValueBool (v1 && v2)
evalFunc HiFunAnd [_] = throwE HiErrorInvalidArgument
evalFunc HiFunAnd _ = throwE HiErrorArityMismatch

evalFunc HiFunOr [HiValueBool v1, HiValueBool v2] = return $ HiValueBool (v1 || v2)
evalFunc HiFunOr [_] = throwE HiErrorInvalidArgument
evalFunc HiFunOr _ = throwE HiErrorArityMismatch

evalFunc HiFunNot [HiValueBool v] = return $ HiValueBool (not v)
evalFunc HiFunNot [_] = throwE HiErrorInvalidArgument
evalFunc HiFunNot _ = throwE HiErrorArityMismatch

evalFunc HiFunLessThan [n1, n2] = return $ HiValueBool (n1 < n2)
evalFunc HiFunLessThan _ = throwE HiErrorArityMismatch

evalFunc HiFunGreaterThan [n1, n2] = return $ HiValueBool (n1 > n2)
evalFunc HiFunGreaterThan _ = throwE HiErrorArityMismatch

evalFunc HiFunEquals [n1, n2] = return $ HiValueBool (n1 == n2)
evalFunc HiFunEquals _ = throwE HiErrorArityMismatch

evalFunc HiFunNotLessThan [n1, n2] = return $ HiValueBool (n1 >= n2)
evalFunc HiFunNotLessThan _ = throwE HiErrorArityMismatch

evalFunc HiFunNotGreaterThan [n1, n2] = return $ HiValueBool (n1 <= n2)
evalFunc HiFunNotGreaterThan _ = throwE HiErrorArityMismatch

evalFunc HiFunNotEquals [n1, n2] = return $ HiValueBool (n1 /= n2)
evalFunc HiFunNotEquals _ = throwE HiErrorArityMismatch

evalFunc HiFunLength [HiValueString n] = return $ HiValueNumber (toRational $ length $ unpack n)
evalFunc HiFunLength [HiValueList n] = return $ HiValueNumber (toRational $ length n)
evalFunc HiFunLength [_] = throwE HiErrorInvalidArgument
evalFunc HiFunLength _ = throwE HiErrorArityMismatch

evalFunc HiFunToUpper [HiValueString n] = return $ HiValueString (toUpper n)
evalFunc HiFunToUpper [_] = throwE HiErrorInvalidArgument
evalFunc HiFunToUpper _ = throwE HiErrorArityMismatch

evalFunc HiFunToLower [HiValueString n] = return $ HiValueString (toLower n)
evalFunc HiFunToLower [_] = throwE HiErrorInvalidArgument
evalFunc HiFunToLower _ = throwE HiErrorArityMismatch

evalFunc HiFunReverse [HiValueString n] = return $ HiValueString (Data.Text.reverse n)
evalFunc HiFunReverse [HiValueList n] = return $ HiValueList (fromList (reverse (toList n)))
evalFunc HiFunReverse [_] = throwE HiErrorInvalidArgument
evalFunc HiFunReverse _ = throwE HiErrorArityMismatch

evalFunc HiFunTrim [HiValueString n] = return $ HiValueString (strip n)
evalFunc HiFunTrim [_] = throwE HiErrorInvalidArgument
evalFunc HiFunTrim _ = throwE HiErrorArityMismatch

evalFunc HiFunList args = return $ HiValueList (fromList args)

evalFunc HiFunRange [HiValueNumber n1, HiValueNumber n2] = return $ HiValueList (fromList $ map (HiValueNumber) [n1..n2])
evalFunc HiFunRange [_, _] = throwE HiErrorInvalidArgument
evalFunc HiFunRange _ = throwE HiErrorArityMismatch

evalFunc HiFunFold [HiValueFunction f, HiValueList (x :<| xs)] = case f of
  HiFunAdd -> (foldM (evalFold f) x xs)
  HiFunSub -> (foldM (evalFold f) x xs)
  HiFunMul -> (foldM (evalFold f) x xs)
  HiFunDiv -> (foldM (evalFold f) x xs)
  HiFunAnd -> (foldM (evalFold f) x xs)
  HiFunOr -> (foldM (evalFold f) x xs)
  HiFunLessThan       -> (foldM (evalFold f) x xs)
  HiFunGreaterThan    -> (foldM (evalFold f) x xs)
  HiFunEquals         -> (foldM (evalFold f) x xs)
  HiFunNotLessThan    -> (foldM (evalFold f) x xs)
  HiFunNotGreaterThan -> (foldM (evalFold f) x xs)
  HiFunNotEquals      -> (foldM (evalFold f) x xs)
  _ -> throwE HiErrorInvalidArgument
evalFunc HiFunFold [HiValueFunction _, HiValueList (Empty)] = return $ HiValueNull
evalFunc HiFunFold [_, _] = throwE HiErrorInvalidArgument
evalFunc HiFunFold _ = throwE HiErrorArityMismatch


evalFold :: HiMonad m => HiFun -> HiValue -> HiValue -> ExceptT HiError m HiValue
evalFold f v1 v2 = evalFunc f [v1, v2]

evalFuncLazy :: HiMonad m => HiFun -> [HiExpr] -> ExceptT HiError m HiValue
evalFuncLazy HiFunIf [cond, v1, v2] = do
   res <- evalHiExpr cond
   case res of
     HiValueBool True -> evalHiExpr v1
     HiValueBool False -> evalHiExpr v2
     _ -> throwE HiErrorInvalidArgument

evalFuncLazy HiFunOr [v1, v2] = do
  res <- evalHiExpr v1
  case res of
    HiValueBool True -> return res
    HiValueBool False -> do
      res2 <- evalHiExpr v2
      case res2 of
        HiValueBool True -> return res2
        HiValueBool False -> return res2
        _ -> throwE HiErrorInvalidArgument
    _ -> throwE HiErrorInvalidArgument

evalFuncLazy HiFunAnd [v1, v2] = do
  res <- evalHiExpr v1
  case res of
    HiValueBool False -> return res
    HiValueBool True -> do
      res2 <- evalHiExpr v2
      case res2 of
        HiValueBool False -> return res2
        HiValueBool True -> return res2
        _ -> throwE HiErrorInvalidArgument
    _ -> throwE HiErrorInvalidArgument

evalFuncLazy _ _ = throwE HiErrorDivideByZero

--thanks stackoverflow
slice :: Int -> Int -> Text -> Text
slice start end text = pack (take (end - start) (drop start (unpack text)))

sliceList :: Int -> Int -> [HiValue] -> [HiValue]
sliceList start end l = take (end - start) (drop start l)