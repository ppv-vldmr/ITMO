#!/bin/bash

N=$1
declare -a array

while true; do
	array+=( 1 2 3 4 5 6 7 8 9 10 )
	if [[ ${#array[@]} -ge $N ]]; then
		exit 0
	fi
done
