{-# LANGUAGE DerivingVia #-}

module HW3.Action
  ( HIO (..)
  , HiPermission (..)
  , PermissionException (..)
  ) where

import Control.Exception (Exception)
import Data.Set (Set)
import GHC.IO.Exception (IOErrorType (PermissionDenied))
import HW3.Base (HiMonad (..))
import Control.Monad.Reader

data HiPermission =
    AllowRead
  | AllowWrite
  deriving (Show, Eq, Ord)

data PermissionException =
  PermissionRequired HiPermission
  deriving Show

instance Exception PermissionException

newtype HIO a = HIO {runHIO :: Set HiPermission -> IO a}
  deriving (Functor, Applicative, Monad) via (ReaderT (Set HiPermission) IO)

instance HiMonad HIO where
  runAction = undefined