net stop Dnscache
net start > servicesWithLate.txt
fc /A /L servicesWithLate.txt services.txt > servicesDiff.txt
net start Dnscache