#!/bin/bash
echo ‘Menu:’
echo ‘1) Open nano’
echo ‘2) Open vim’
echo ‘3) Open links’
echo ‘4) Exit’
echo ‘’
echo «Please enter number of menu’s section»
read command
case $command in
		1 )
			usr/bin/nano
		;;
		2 )
			usr/bin/vim
		;;
		3 )
			usr/bin/links
		;;
		4 )
			echo «See you»
			exit 0
		;;
esac