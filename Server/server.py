import socket
import pyautogui
import qrcode
from parse import *
from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes


HOST = ""
PORT = 9000

def get_eas():
    key = get_random_bytes(16)
    cipher = AES.new(key, AES.MODE_CBC)

    return cipher, key


def show_connection(key):
    name = "{}/{}/".format(socket.gethostbyname(socket.gethostname()), PORT)
    img = qrcode.make(name)
    img.show()


def read(conn, cipher):
    while True:
        data = conn.recv(1024).decode("utf-8")
        if not data:
            break
        
        if data.startswith("m"):
            x, y = parse("m {:d} {:d}\n", data)
            pyautogui.move(x, y)
        if data.startswith("b"):
            button = parse("b {}\n", data)
            pyautogui.click(button=button)
        if data.startswith("k")
            text = parse("k {}\n", data)
            pyautogui.press(text)



def connect():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        cipher, key = get_eas()
        
        show_connection(key)

        s.bind((HOST, PORT))
        s.listen()
        conn, addr = s.accept()
        with conn:
            print("Connected by", addr)
            read(conn, cipher)
        

connect()
