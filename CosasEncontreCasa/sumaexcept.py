def suma():
    print("Vamos a sumar!")
    a= int(input("Introduce el primer numero"))
    b=int(input("introduce el segundo número"))
    suma= a + b
    print(suma, "Esta es la suma de lo que has introducido")

try:
    suma()
except:
    print("No es posible, vuelve a introducir tus NUMEROS")    
