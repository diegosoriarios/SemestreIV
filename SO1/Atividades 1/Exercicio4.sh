#!/bin/bash
f= /home/diego/Documentos/aula/

for file in "$f"/*
do 
    if [ $f -nt ../teste2/$f -o -z ../teste2/$f ] 
    then
        cp -v $f ../teste2/
        echo "Arquivo copiado com sucesso"
    fi
    echo $file
done