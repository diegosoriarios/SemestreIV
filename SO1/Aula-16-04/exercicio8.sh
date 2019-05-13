#!/bin/bash
nome=$(zenity --title="Identificação" --text "Informe o nome do processo" --entry)
processo=$(ps -C $nome --no-headers | awk '{ print $1 }')

if zenity --question --text="Você deseja matar esse processo"; then
 kill $processo
 zenity --info --text="Você matou o processo $processo!"
else
 zenity --error --text="Você não matou o processo\!"
fi
