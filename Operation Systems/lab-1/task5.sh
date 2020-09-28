#!/bin/bash
cat /var/log/syslog | awk '$6 == "<info>"' > info.log
