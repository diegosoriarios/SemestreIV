#!/bin/bash
P=$1

adicionarUser(){
	read nome    	
	adduser $nome
}
removerUser(){
	read nome 
   	userdel $nome
}
install(){
	read nome
	apt-get install $nome
    
}
uninstall(){
	read nome
	apt-get remove $nome
}

echo '1 - Add User'
echo '2 - Remove'
echo '3 - Install'
echo '4 - Uninstall'

read VARIAVEL
case $VARIAVEL in
    1) adicionarUser;;
    2) removerUser;;
    3) install;;
    4) uninstall;;
    *)echo "opcao invalida";;
esac


