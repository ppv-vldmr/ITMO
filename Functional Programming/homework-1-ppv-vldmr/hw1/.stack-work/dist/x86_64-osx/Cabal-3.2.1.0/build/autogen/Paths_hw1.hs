{-# LANGUAGE CPP #-}
{-# LANGUAGE NoRebindableSyntax #-}
{-# OPTIONS_GHC -fno-warn-missing-import-lists #-}
module Paths_hw1 (
    version,
    getBinDir, getLibDir, getDynLibDir, getDataDir, getLibexecDir,
    getDataFileName, getSysconfDir
  ) where

import qualified Control.Exception as Exception
import Data.Version (Version(..))
import System.Environment (getEnv)
import Prelude

#if defined(VERSION_base)

#if MIN_VERSION_base(4,0,0)
catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
#else
catchIO :: IO a -> (Exception.Exception -> IO a) -> IO a
#endif

#else
catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
#endif
catchIO = Exception.catch

version :: Version
version = Version [0,1,0,0] []
bindir, libdir, dynlibdir, datadir, libexecdir, sysconfdir :: FilePath

bindir     = "/Users/ppv-vldmr/Desktop/fp-homework/.stack-work/install/x86_64-osx/712f36a7ab065b38cd14f5d684d62a502028679cfd1000cde25237b4a78c780e/8.10.7/bin"
libdir     = "/Users/ppv-vldmr/Desktop/fp-homework/.stack-work/install/x86_64-osx/712f36a7ab065b38cd14f5d684d62a502028679cfd1000cde25237b4a78c780e/8.10.7/lib/x86_64-osx-ghc-8.10.7/hw1-0.1.0.0-H2x76hz06zRArVgl02Pmde"
dynlibdir  = "/Users/ppv-vldmr/Desktop/fp-homework/.stack-work/install/x86_64-osx/712f36a7ab065b38cd14f5d684d62a502028679cfd1000cde25237b4a78c780e/8.10.7/lib/x86_64-osx-ghc-8.10.7"
datadir    = "/Users/ppv-vldmr/Desktop/fp-homework/.stack-work/install/x86_64-osx/712f36a7ab065b38cd14f5d684d62a502028679cfd1000cde25237b4a78c780e/8.10.7/share/x86_64-osx-ghc-8.10.7/hw1-0.1.0.0"
libexecdir = "/Users/ppv-vldmr/Desktop/fp-homework/.stack-work/install/x86_64-osx/712f36a7ab065b38cd14f5d684d62a502028679cfd1000cde25237b4a78c780e/8.10.7/libexec/x86_64-osx-ghc-8.10.7/hw1-0.1.0.0"
sysconfdir = "/Users/ppv-vldmr/Desktop/fp-homework/.stack-work/install/x86_64-osx/712f36a7ab065b38cd14f5d684d62a502028679cfd1000cde25237b4a78c780e/8.10.7/etc"

getBinDir, getLibDir, getDynLibDir, getDataDir, getLibexecDir, getSysconfDir :: IO FilePath
getBinDir = catchIO (getEnv "hw1_bindir") (\_ -> return bindir)
getLibDir = catchIO (getEnv "hw1_libdir") (\_ -> return libdir)
getDynLibDir = catchIO (getEnv "hw1_dynlibdir") (\_ -> return dynlibdir)
getDataDir = catchIO (getEnv "hw1_datadir") (\_ -> return datadir)
getLibexecDir = catchIO (getEnv "hw1_libexecdir") (\_ -> return libexecdir)
getSysconfDir = catchIO (getEnv "hw1_sysconfdir") (\_ -> return sysconfdir)

getDataFileName :: FilePath -> IO FilePath
getDataFileName name = do
  dir <- getDataDir
  return (dir ++ "/" ++ name)
