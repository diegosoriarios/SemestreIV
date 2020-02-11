#include<stdio.h>

#include<fcntl.h>

#include<errno.h>

extern int errno;

int main()

{

 int fd = open("teste.txt", O_RDONLY | O_CREAT);

 printf("fd = %d/n", fd);

 if (fd ==-1)

 {

 printf("Erro Numero % d\n", errno);

 perror("Erro");

 }

 return 0;

}
