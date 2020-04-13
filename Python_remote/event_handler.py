import socket_poler
import pyautogui
from time import sleep

class event_handler:
    def __init__(self, options, poler):
        self.poler = poler
        self.options = options

        self.x_o = 0
        self.y_o = 0
        pyautogui.PAUSE = 0
        

    def handle(self):
        while True:
            order = self.poler.orders().get()

            if self.options.verbose:
                print("handling {}".format(order))

            words = order.split(" ")
            if words[0] == "a":
                if words[1] == "d":
                    x_o, y_o = pyautogui.position()
                elif words[1] == "c":
                    pyautogui.click()

            elif words[0] == "m":
                x = int(words[1])
                y = int(words[2])
                
                pyautogui.moveTo(x + x_o, y + y_o)




