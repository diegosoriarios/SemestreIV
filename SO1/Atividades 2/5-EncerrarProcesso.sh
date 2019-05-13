#!/bin/sh

echo Qual PID do processo?
read TASK
echo $TASK
kill $TASK