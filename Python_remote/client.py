import socket

HOST = '127.0.0.1'
PORT = 10000

def test():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((HOST, PORT))
        while True:
            text = "{}\n".format(input())
            bt = bytearray(text, "utf-8")
            s.sendall(bt)


test()
