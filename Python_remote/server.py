import socket
import pyautogui
import qrcode
from parse import *


HOST = ""
PORT = 10000 



def show_connection():
    name = "{}/{}/".format(socket.gethostbyname(socket.gethostname()), PORT)
    print(name)
    img = qrcode.make(name)
    #img.show()


def last_mouse(orders):
    for order in orders[::-1]:
        if order[0] == 'm':
            return order
    return order


def clean_input(orders):
    res = []
    action = False
    for order in orders:
        if order[0] == 'a':
            res.append(order)
            action = True
        elif order[0] == 'm' and action:
            res.append(order)
            action = False
    res.append(last_mouse(orders))
    return res


def read(conn):
    down = False
    x_last = 0
    y_last = 0
    data = ""
    while True:
        data = conn.recv(1024).decode("utf-8")
        if not data:
            break
        orders = data.split(",")[:-1]
        print(orders)

        orders = clean_input(orders)

        print(orders)

        for order in orders:
            print(order)
            if order.startswith("m"):
                if down:
                    x_last, y_last = parse("m {:d} {:d}", order)
                    down = False
                else:
                    x, y = parse("m {:d} {:d}", order)
                    pyautogui.move(x - x_last, y - y_last, duration=0.1, tween=pyautogui.easeInOutQuad)
                    x_last = x
                    y_last = y
            if order.startswith("b"):
                button = parse("b {}", order)
                pyautogui.click(button=button)
            if order.startswith("k"):
                text = parse("k {}", order)
                pyautogui.press(text)
            if order.startswith("a"):
                if order[2] == "d":
                    down = True



def connect():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:

        s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        
        show_connection()

        s.bind((HOST, PORT))
        s.listen()
        conn, addr = s.accept()
        with conn:
            print("Connected by", addr)
            read(conn)
        

connect()
