{-# LANGUAGE BlockArguments #-}

module Main where

import System.Console.Haskeline
import HW3.Parser (parse)
import HW3.Evaluator
import HW3.Pretty
import HW3.Action
import qualified Data.Set as Set
import Control.Monad.Cont (liftIO)

main :: IO ()
main = runInputT defaultSettings loop
   where 
       loop :: InputT IO ()
       loop = do
           minput <- getInputLine "hi> "
           case minput of
               Nothing -> return ()
               Just "quit" -> return ()
               Just input -> case parse input of
                               Left e -> outputStrLn $ show e
                               Right v -> do
                                 res <- liftIO $ runHIO (eval v) (Set.fromList [])
                                 case res of
                                   Left e1 -> outputStrLn $ show e1
                                   Right v1 -> outputStrLn $ show $ prettyValue v1
           loop