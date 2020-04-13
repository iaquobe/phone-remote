# Phone Remote
control your PC with your phone as remote.


# Installation

## Server

### Windows
in Powershell run:\
`install.ps`

### Linux
run:\
`install.sh`\
on Linux pyautogui needs tkinter to function. You'll have to find the package you need. For the following managers use:

pacman:
`pacman -S python-pmw`

apt
`sudo apt-get install python-tk`

## App
Download the apk and install the app.\
You'll need to enable installs outside the Play Store. A prompt will show you how to do this.

# Dependencies
python modules:
`parse`,`pyautogui`,`qrcode`

# Usage
Start the Server\
`python ./Server/server.py`\
your ip and a port will be printed to the terminal\


Now open the app.
Go into options and write enter the Ip you got from the server.\
Now press the connect button until the server prints out `connected to (...)`




