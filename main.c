#include <stdlib.h>
#include <X11/Xlib.h>

int main(int argc, char **argv){
	int x = atoi(argv[1]);
	int y = atoi(argv[2]);

	Display *display = XOpenDisplay(0);

	XWarpPointer(display, None, None, 0, 0, 0, 0, x, y);
	XFlush(display);

	XCloseDisplay(display);

	return 0;
}
