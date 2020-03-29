import socket
import pyautogui
from parse import *


HOST = "127.0.0.1"
PORT = 9000

def read(conn):
    while True:
        data = conn.recv(1024).decode("utf-8")
        if not data:
            break
        
        if data.startswith("m"):
            x, y = parse("m {:d} {:d}\n", data)
            pyautogui.move(x, y)


def connect():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind((HOST, PORT))
        s.listen()
        conn, addr = s.accept()
        with conn:
            print("Connected by", addr)
            read(conn)
        

connect()
