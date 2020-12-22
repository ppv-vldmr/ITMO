#!/bin/bash

K=30

for ((i=0; i<$K; i++)); do
	bash newmem.bash 3000000 &
	sleep 1
done
