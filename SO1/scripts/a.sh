#!/bin/bash
DIRETORIO=/home/aluno/scripts

for f in $DIRETORIO/*.sh
do
	chmod +x $f
done
