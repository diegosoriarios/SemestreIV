#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main(void){
	pid_t pid; int retval;
	int fd[2]; int n;
	retval = pipe(fd);
	if (retval < 0) {
		printf("Não foi possível criar o pipe\n");
		exit(0); }
	
	pid = fork();
 	if (pid == 0) {
		close(fd[0]);
		n = write (fd[1],"Ola do processo filho",21);
		system("echo 'Ola do processo filho' > log.log");
		exit(0);
	}
	else if (pid > 0) {
		FILE *f = fopen("info.log", "a");		
		char buffer[64]; close(fd[1]);
		n = read(fd[0],buffer,64); buffer[n]='\0';
		printf("Mensagem recebida: %s\n",buffer);
		if(f) 
		{
			fprintf(f, "Mensagem recebida: %s\n",buffer);
			fclose(f);
		}
	 }
	 return 0;
}
