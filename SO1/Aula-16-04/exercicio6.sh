#!/bin/bash

memoria_total=$(free -m | grep -i mem | awk '{ print $2 }')
memoria_swap=$(free -m | grep -i swap | awk '{ print $2 }')
l1=$(lscpu | grep cache | awk '{print $4;}' | awk 'FNR == 1')
l2=$(lscpu | grep cache | awk '{print $4;}' | awk 'FNR == 3')
l3=$(lscpu | grep cache | awk '{print $4;}' | awk 'FNR == 4')

texto="Memoria Total: $memoria_total Mb\nMemoria swap: $memoria_swap Mb\n L1: $l1\n L2: $l2\n L3: $l3"

nome=$(zenity --title="Identificação" --text "$texto" --info)
