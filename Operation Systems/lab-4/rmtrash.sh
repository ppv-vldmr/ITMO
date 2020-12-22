#!/bin/bash

<<<<<<< HEAD
trashDir=/home/vladimir/.trash
numFile=/home/vladimir/.numbers
logFile=/home/vladimir/.trash.log

if ! [[ -f $1 ]]; then
  echo "File $1 doesn't exist"
  exit 0
=======
trashDir=/home/.trash
numFile=/home/.numbers
logFile=/home/.trash.log

if ! [ -f $1 ]; then
  echo "File $1 doesn't exist"
  return -1
>>>>>>> cfdd6d912b3ec65658c2b36be46aa9c7b64cc0f4
fi

removeFile=$PWD"/"$1

<<<<<<< HEAD
if ! [[ -d $trashDir ]]
then
  mkdir $trashDir
fi

if ! [[ -f $numFile ]]; then
=======
if ! [ -d $trashDir ]; then
  mkdir $trashDir
fi

if ! [ -f $numFile ]; then
>>>>>>> cfdd6d912b3ec65658c2b36be46aa9c7b64cc0f4
  touch $numFile
  echo 1 > $numFile
fi

<<<<<<< HEAD
if ! [[ -f $logFile ]]; then
=======
if ! [ -f $logFile ]; then
>>>>>>> cfdd6d912b3ec65658c2b36be46aa9c7b64cc0f4
  touch $logFile
fi

read nextNumber < $numFile

nextNumber=$((nextNumber + 1))

ln $removeFile "$trashDir/$nextNumber"
rm $removeFile

echo $nextNumber > $numFile
echo "$removeFile|$nextNumber" >> $logFile
