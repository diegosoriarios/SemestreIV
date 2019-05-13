#!/bin/sh

echo Insira o PID do processo:
read PID

ps -mo THREAD -p $PID