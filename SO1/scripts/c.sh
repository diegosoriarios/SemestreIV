#!/bin/bash
P=1

adicionarUser(){
	echo "Digite o nome do usuário para adicionar"
	read nome    	
	adduser $nome
	if [ ! -d $nome ]
	then
		echo "Usuário adicionado"
	else
		echo "Erro ao adicionar usuário"
	fi
	awk '{print $1}' /etc/passwd | awk 'END {print $1}'
	menu
}
removerUser(){
	echo "Digite o nome do usuário a ser removido"
	read nome 
   	userdel $nome
	if [ ! -d $nome ]
	then
		echo "Usuário removido"
	else
		echo "Erro ao remover usuário"
	fi
	menu
}

menu(){
	echo '1 - Add User'
	echo '2 - Remove'
	echo '3 - Sair'
	read VARIAVEL
	case $VARIAVEL in
	    1) adicionarUser;;
	    2) removerUser;;
	    3) exit 1;;
	    *)echo "opcao invalida";;
	esac
}

menu


