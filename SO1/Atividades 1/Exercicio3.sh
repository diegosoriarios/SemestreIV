#!/bin/bash
echo "Digite seu nome"

read nome

if [-z $nome]
then
echo "Digite um nome valido."
else
echo "Seu nome é $nome"
fi
adduser $nome

awk '{print $1}' /etc/passwd | awk 'END {print $1}'