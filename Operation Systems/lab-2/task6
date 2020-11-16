#!/bin/bash

grep --include=status --exclude-dir=task -ir vmrss /proc | sort -nrt " " -k2 | head -1 |
tr / " " | tr -s " " " " | cut -d" " -f5,6,3 | sed "s/^/PID:/"
