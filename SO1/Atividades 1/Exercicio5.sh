#!/bin/bash

cd /home/diego/Documentos
variavel=0
for f in *
do
    variavel=$[$variavel+1]
done

echo $variavel

