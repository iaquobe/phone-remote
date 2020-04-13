import socket_poler
import event_handler
import getopt
import sys
from threading import Thread

port = 10000
verbose = False

def main():
    global port,verbose
    optlist = dict(getopt.getopt(sys.argv[1:],'vp:')[0])

    if "-p" in optlist:
        port = int(optlist["-p"])
    if "-v" in optlist:
        verbose = True

    poler = socket_poler.socket_poler(port=port, buffer_size=1)
    handler = event_handler.event_handler(poler)

    print(poler.connection())

    poler_thread = Thread(target=poler.pol)
    handler_thread = Thread(target=handler.handle)

    poler_thread.start()
    handler_thread.start()

    poler_thread.join()
    handler_thread.join()


main()
