import socket_poler
import socket

PORT = 10000

def main():
    poler = socket_poler.socket_poler(port=PORT)
    print(poler.connection())
    poler.pol()

main()
