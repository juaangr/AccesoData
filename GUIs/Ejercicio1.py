# un programa que controle el ordenador para automatizar el ratón o lo escrito por teclado 
# este programa va a descargar algo, vamos a descargar una imagen de la pelicula Pulp Fiction
# este programa es de prueba...
import pyautogui
import time

# barra de escribir en google: 375 132
# web a descargar 251 287
# guardar como 294 403

def mvGoogleBar():
    pyautogui.move(375, 132)
    
def mvWeb():
    pyautogui.move(251, 132)
    
def mvSaveAs():
    pyautogui.move(294, 403)

def mousePosition():
    time.sleep(5)
    currentMouseX, currentMouseY = pyautogui.position()
    print("Esta es la posición de tu ratón: ",currentMouseX, currentMouseY)

def resolution():
    screenWidth, screenHeight = pyautogui.size()
    print("Esta es la resolución de la pantalla: ", screenWidth, screenHeight)

def rightClic():
    pyautogui.rightClick()
    print("Click derecho realizado")

def doubleClic():
    doubleC = pyautogui.doubleClick
    if (doubleC):
        print("He hecho doble click")

def useKeyboard():
    pyautogui.write("hola mundo en python")
    pyautogui.press('enter')
    print("escrito realizado")

    

if __name__ == "__main__":
    mvGoogleBar()
    pyautogui.click()
    useKeyboard()
    mvWeb()
    rightClic()
    mvSaveAs()
    pyautogui.click()
    pyautogui.press('tab')
    pyautogui.press('tab')
    pyautogui.press('tab')
    pyautogui.press('enter')
    