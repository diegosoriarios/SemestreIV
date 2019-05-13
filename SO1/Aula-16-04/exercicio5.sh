#!/bin/bash

memoria_total=$(vmstat -s -S M | grep ' total memory' | awk '{print $1;}')
memoria_usada=$(vmstat -s -S M | grep ' used memory' | awk '{print $1;}')
memoria_swap_usada=$(vmstat -s -S M | grep ' total swap' | awk '{print $1;}')
memoria_cache_usada=$(vmstat -s -S M | grep ' buffer swap' | awk '{print $1;}')

texto="Memoria Total: $memoria_total\nMemoria utilizada: $memoria_usada\nMemoria Swap: $memoria_swap_usada\nMemoria Cache: $memoria_cache_usada"

nome=$(zenity --title="Identificação" --text "$texto" --info)
