#!/bin/bash
awk -F: ‘{printf "%s %s\n", $3, $1}’ /etc/passwd | sort -n -k 1
