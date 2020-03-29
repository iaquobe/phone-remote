#include <sys/socket.h>
#include <stdio.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <string.h>

#define PORT 9000
#define ADDRESS "192.168.178.62"


int main(int argc, char **argv){
	int sock;
	struct sockaddr_in serv_addr;

	if((sock = socket(AF_INET, SOCK_STREAM, 0))<0)
		return -1;

	serv_addr.sin_family = AF_INET;
	serv_addr.sin_port = htons(PORT);

	if(inet_pton(AF_INET, ADDRESS, &serv_addr.sin_addr)<=0)
		return -1;

	if(connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0)
		return -1;

	char buffer[1024] = {0};
	int x, y;
	while(scanf("%d %d", &x, &y)){
		snprintf(buffer, 1024, "m %d %d\n", x, y);

		send(sock, buffer, strlen(buffer), 0);

	}

	return 0;
}
