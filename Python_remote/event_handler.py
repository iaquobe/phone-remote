import socket_poler
import pyautogui

class event_handler:
    def __init__(self, poler):
        self.poler = poler

    def handle(self):
        down = False
        x_last = 0
        y_last = 0

        while True:
            orders = self.poler.orders()
            if orders != []:
                print(orders)
            for order in orders:
                print("handling {}".format(order))
                words = order.split(" ")
                if words[0] == "a":
                    if words[1] == "d":
                        down = True
                elif words[0] == "m":
                    if down:
                        x_last = int(words[1])
                        y_last = int(words[2])
                        down = False
                    else:
                        x = int(words[1])
                        y = int(words[2])
                        
                        pyautogui.move(x - x_last, y - y_last, duration=0.2)

                        x_last = x
                        y_last = y




