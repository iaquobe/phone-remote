import socket_poler
import event_handler
import socket
from threading import Thread

PORT = 10000

def main():
    poler = socket_poler.socket_poler(port=PORT, mouse_buffer_size=1)
    handler = event_handler.event_handler(poler)

    print(poler.connection())

    poler_thread = Thread(target=poler.pol)
    handler_thread = Thread(target=handler.handle)

    poler_thread.start()
    handler_thread.start()

    poler_thread.join()
    handler_thread.join()


main()
