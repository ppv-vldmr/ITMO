#!/bin/bash
man bash | grep -i -o "[[:alpha:]]\{4,\}" | sort | uniq -c -i | sort -r -n | head -3
