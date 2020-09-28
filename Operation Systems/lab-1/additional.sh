#!/bin/bash
echo $* | awk '{for (i = 1; i < NF; i++) {n = substr($1,i,1) + 2; print $n}}'