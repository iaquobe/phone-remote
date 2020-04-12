import socket

HOST = '192.168.178.62'
PORT = 10000

def test():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((HOST, PORT))
        while True:
            bt = bytearray(input(), "utf-8")
            s.sendall(bt)


test()
