#!/bin/bash
buscarNome(){
    read -p "Nome do diretorio:" diretorio
    read -p "Nome do arquivo:" arq
    find ~/$diretorio -name $arq
}
buscarTexto(){
    read -p "Nome do arquivo:" arq
    read -p "Texto a procurar:" texto
    grep -R "$texto" $arq
}

echo '1 - Buscar nome'
echo '2 - Buscar texto'
echo 'Option: '
read var

case $var in
    1)buscarNome;;
    2)buscarTexto;;
    *)echo "INVALIDO"
esac

