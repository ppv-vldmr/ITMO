#!/bin/bash
string=‘’
while [[ $string != ‘q’ ]]
do
		buf=$buf$string
		read string
done
echo $buf
