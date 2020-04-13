import socket_poler
import event_handler
import options

import getopt
import sys
from threading import Thread

verbose=False
port=10000
host=""

def get_options():
    global verbose, port, host

    optdict = dict(getopt.getopt(sys.argv[1:],'vp:')[0])

    if "-p" in optdict:
        port = int(optdict["-p"])

    verbose = ("-v" in optdict)

    return options.Options(verbose=verbose, port=port, host=host)

    
def main():
    options = get_options()

    poler = socket_poler.socket_poler(options, buffer_size=1)
    handler = event_handler.event_handler(options, poler)

    print(poler.connection())

    poler_thread = Thread(target=poler.pol)
    handler_thread = Thread(target=handler.handle)

    poler_thread.start()
    handler_thread.start()

    poler_thread.join()
    handler_thread.join()


main()
