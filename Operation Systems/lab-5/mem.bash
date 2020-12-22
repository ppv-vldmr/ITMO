#!/bin/bash

[ ! -f report.log ] && touch report.log || echo "" > report.log
[ ! -f pids ] && touch pids || echo $$ > pids

bash writer $$ $1 report.log &

declare -a array
step=0

while true; do
	array+=( 1 2 3 4 5 6 7 8 9 10 )
	step=$(( step + 1 ))
	if [[ $step -ge 100000 ]]; then
		mod=$(( step % 100000 ))
		if [[ $mod -eq 0 ]]; then
			echo ${#array[@]} >> report.log
		fi
	fi
done
