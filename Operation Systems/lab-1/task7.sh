#!/bin/bash
email="[[:alnum:]._]+@[[:lower:]]+\.[[:lower:]]+"
grep -E -h -r -s -o $email /etc | sort | uniq | awk ‘printf("%s, ", $1)}’ > emails.lst
