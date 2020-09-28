#!/bin/bash
inf="\\(II\\)"
war="\\(WW\\)"
grep -E $war /var/log/syslog | sed -E "s/$war/Warning: /" > full.log
grep -E $inf /var/log/syslog | sed -E "/$inf/Information: /" >> full.log
