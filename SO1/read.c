#include<stdio.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
int main()
{
 int fd, sz;
 char *c = (char *) calloc(100, sizeof(char));
 fd = open("teste.txt", O_RDONLY);
 if (fd < 0) { perror("Erro 1"); exit(1); }
 sz = read(fd, c, 10);
 printf("chamada read(% d, c, 10). retornou que"
 " %d bytes foram lidos.\n", fd, sz);
 c[sz] = '\0';
 printf("Os seguintes bytes foram lidos: % s\n", c);
}
