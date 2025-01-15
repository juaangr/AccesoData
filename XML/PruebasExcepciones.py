import sys

def fun1():
    print(7/0)
a=7
b=0
lista=[1,2]

try:
    fun1()
except:
    print("Error capturado")
finally:
    print("Estamos en el finally")

#print(7/0)
#print(lista[5])
#int("a")
try:
    print(lista[5])
    print(a/b)
    #int("a")

except IndexError as ex:
    print("Error de indice")
    print(ex)
except ZeroDivisionError as ex:
    print("Error de division")
except:
    print("Error indeterminado")
    print("Error inesperado:", sys.exc_info())
else:
    print("Pasamos por el else. Cuando no hay error")
finally:
    print("Por aqui pasa siempre")
    

print("1.Accion 1")
print("2.Accion 2")
print("3.Salir")
incorrecto = True
while incorrecto:
    try:
        opcion=int(input("Introduce opcion:"))
        if(opcion>=1 and opcion<=3):
            incorrecto = False
    except:
        print("Opcion incorrecta vuelva a intentarlo")
        
if(opcion==1):
    print("Accion 1")
elif(opcion==2):
    print("Accion 2")
elif(opcion==3):
    print("Salimos")
else:
    print("opcion no incluida")
    


print("Fin")