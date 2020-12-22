#!/bin/bash

untrashFile=$1
<<<<<<< HEAD
trashDir=$HOME/.trash
logFile=$HOME/.trash.log

if [[ ! -d $trashDir ]]; then
=======
trashDir=/home/.trash
logFile=/home/.trash.log

if ! [ -d $trashDir ]; then
>>>>>>> cfdd6d912b3ec65658c2b36be46aa9c7b64cc0f4
  echo "There is no trash bin on your computer"
  return -1
fi

<<<<<<< HEAD
if [[ ! -f $logFile ]]; then
=======
if ! [ -f $logFile ]; then
>>>>>>> cfdd6d912b3ec65658c2b36be46aa9c7b64cc0f4
  echo "There is no trash.log on your computer"
  return -1
fi

<<<<<<< HEAD
logData=$(grep "/$untrashFile|" $logFile | tail -n1)
=======
logData=$(grep "/$untrashFile|" $logFile)
>>>>>>> cfdd6d912b3ec65658c2b36be46aa9c7b64cc0f4

for line in $logData; do
  nameInTrash=$(echo $line | cut -d "|" -f2)
  filePath=$(echo $line | cut -d "|" -f1)

  command=null
  while ! [[ "$command" == Y || "$command" == N ]]; do
    echo "Do you want to untrash $filePath? [Y/N]"
    read command
  done

  if [[ "$command" == Y ]]; then

    dir="$(dirname $filePath)"
    if ! [ -e $dir ]; then
      echo "$dir doesn't exist, trying to untrash your file into /home"
      dir=/home
    fi

    fileName=$untrashFile
    while [ -f "$dir/$fileName" ]; do
      echo "$fileName already exits, type another name:"
      read fileName
      echo $fileName
    done

    ln "$trashDir/$nameInTrash" "$dir/$fileName"
    rm "$trashDir/$nameInTrash"
    break
  fi
done
