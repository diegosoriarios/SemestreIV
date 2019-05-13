#!/bin/bash
nome=$(zenity --title="Processo" --text "Informe o nome do processo:" --entry)
processo=$(ps -C $nome --no-headers -o pmem)
zenity --info --title="Ola" --text="$nome está utilizando $processo de memória"
