#!/bin/bash
CAMINHO_DIR=/home/aluno/scripts
CAMINHO_BACKUP=/home/aluno/backup
if [ ! -d $CAMINHO_DIR ]
then
break
fi
if [ ! -d $CAMINHO_BACKUP ]
then
break
fi

i=0

for f in $CAMINHO_DIR/*
	do
 		(( i=$(( $i + 1 )) ));
	done

zip -r -j backup.zip $CAMINHO_DIR
mkdir $CAMINHO_BACKUP
mv backup.zip /$CAMINHO_BACKUP

cd $CAMINHO_BACKUP
	mkdir verifica
	cp backup.zip ./verifica
	cd verifica
	unzip backup.zip
	rm backup.zip

j=0
for f in $CAMINHO_BACKUP/verifica/*
	do
 		(( j=$(( $j + 1 )) ));
	done

cd ..
rm -rf verifica

if [ $i -eq $j ]
then
	echo "Backup realizado com sucesso" > mensagem.txt
else
 	echo "Houve um problema no Backup" > mensagem.txt
fi
