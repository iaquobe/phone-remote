import socket_poler
import pyautogui
from time import sleep

class event_handler:
    def __init__(self, poler):
        self.poler = poler
        pyautogui.PAUSE = 0

    def handle(self):
        x_o = 0
        y_o = 0

        while True:
            sleep(0.02)
            orders = self.poler.orders()
            if orders != []:
                print(orders)
            for order in orders:
                print("handling {}".format(order))
                words = order.split(" ")
                if words[0] == "a":
                    if words[1] == "d":
                        x_o, y_o = pyautogui.position()

                elif words[0] == "m":
                    x = int(words[1])
                    y = int(words[2])
                    
                    pyautogui.moveTo(x + x_o, y + y_o)




