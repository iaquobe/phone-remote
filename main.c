#include <stdio.h>
#include <unistd.h>
#include <stdio.h>

#include <stdlib.h>
#include <X11/Xlib.h>

#include <sys/socket.h>
#include <sys/types.h>
#include <ifaddrs.h>
#include <netinet/in.h>


#define PORT 9000
#define BUFFER_LEN 1024

int open_connection(){
	int res;
	int server_fd;
	struct sockaddr_in address;
	int addrlen = sizeof(address);

	//open socket
	if((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0){
		perror("socket failed");
		exit(EXIT_FAILURE);
	}


	//bind port
	address.sin_family = AF_INET;
	address.sin_addr.s_addr = INADDR_ANY;
	address.sin_port = htons (PORT);

	if(bind(server_fd, (struct sockaddr *)&address, addrlen)<0){
		perror("bind failed");
		exit(EXIT_FAILURE);
	}


	//listen for connection	
	if(listen(server_fd, 1)){
		perror("listen failed");
		exit(EXIT_FAILURE);
	}

	//accept connection
	if((res = accept(server_fd, NULL, NULL))<0){
		perror("accept");
		exit(EXIT_FAILURE);
	}
	return res;
}



int move_mouse(int x, int y){

	Display *display = XOpenDisplay(0);

	XWarpPointer(display, None, None, 0, 0, 0, 0, x, y);
	XFlush(display);

	XCloseDisplay(display);

	return 0;
}



void mainloop(int client){
	char buffer[BUFFER_LEN] = {0};

	while(read(client, buffer, BUFFER_LEN)){
		if(buffer[0] == 'm'){
			int x, y;
			sscanf(buffer, "m %d %d\n", &x, &y);
			printf("m %d %d\n", x, y);
			move_mouse(x, y);

		}
	}

}


int main(int argc, char **argv){

	int client = open_connection();
	
	mainloop(client);

	return 0;
}
