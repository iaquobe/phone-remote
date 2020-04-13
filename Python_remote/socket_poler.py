import socket
import queue

class socket_poler:
    def __init__(self, buffer_size=None, host="", port=10000):
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.socket.bind((host, port))
        self.socket.listen()

        self.buffer = queue.Queue()

        self.port = port


    def pol(self):
        while True:
            conn, addr = self.socket.accept()
            print("connected with {}".format(addr))
            while True:
                data = conn.recv(1024).decode("utf-8")
                if not data:
                    break
                orders = data.split(",")[:-1]
                
                for order in orders:
                    self.buffer.put(order)

    def orders(self):
        return self.buffer

    def connection(self):
        return "{}/{}/".format(socket.gethostbyname(socket.gethostname()), self.port)


