#!bin/bash

p1=$(ps axco pid,command --sort=-pmem | head -n 6  | awk 'FNR == 2')
p2=$(ps axco pid,command --sort=-pmem | head -n 6  | awk 'FNR == 3')
p3=$(ps axco pid,command --sort=-pmem | head -n 6  | awk 'FNR == 4')
p4=$(ps axco pid,command --sort=-pmem | head -n 6  | awk 'FNR == 5')
p5=$(ps axco pid,command --sort=-pmem | head -n 6  | awk 'FNR == 6')
p6=$(ps axco pid,command --sort=-pmem | head -n 6  | awk 'FNR == 7')


texto="$p1\n$p2\n$p3\n$p4\n$p5\n$p6"

nome=$(zenity --title="Identificação" --text "$texto" --info)
