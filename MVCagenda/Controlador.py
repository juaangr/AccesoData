from MVCagenda.EjemploProfe import menu
import Vista

def alta(lista):
	print("ALTA")
	nom=input("Introduce nombre:")
	ape=input("Introduce apellidos:")
	direc=input("Introduce direccion:")
	tel=input("Introduce telefono:")
	edad=input("Introduce edad:")
	contacto = [nom,ape,direc,tel,edad]
	lista.append(contacto)
	
	
def baja(lista):
	print("BAJA")
	encontrado = False
	ape=input("Introduce apellidos del contacto a borrar:")
	pos = -1
	posBorrar = -1
	for i in lista:
		pos = pos + 1
		if(i[1]==ape):
			print(i)
			encontrado = True
			opcion = input("Estas seguro de borrar? s/n ")
			if(opcion.lower()=='s' or opcion.lower()=='si'):
				posBorrar = pos
				
	if(not encontrado):
		print("Contacto no encontrado")
	else:
		if(posBorrar!=-1):
			del(lista[posBorrar])
			print("Contacto borrado")
	
def modificacion(lista):
	print("MODIFICAR")
	encontrado = False
	ape=input("Introduce apellidos del contacto a modificar:")
	pos = -1
	posBorrar = -1
	for i in lista:
		pos = pos + 1
		if(i[1]==ape):
			print(i)
			encontrado = True
			newtel = input("Introduce el nuevo telefono:")
			opcion = input("Estas seguro de modificar? s/n ")
			if(opcion.lower()=='s' or opcion.lower()=='si'):
				i[3]=newtel
				print("Contacto modificado")
				
	if(not encontrado):
		print("Contacto no encontrado")

	
def buscar(lista):
	print("BUSCAR")
	encontrado = False
	ape=input("Introduce apellidos del contacto:")
	for i in lista:
		if(i[1]==ape):
			print(i)
			encontrado = True
	if(not encontrado):
		print("Contacto no encontrado")
	
def mostrar(lista):
	print("\n\n------------")
	print("-- AGENDA --")
	print("------------\n")
	for i in lista:
		print(i)
 
 
print("Empezamos agenda listas")  
agenda=[]
salir=False
while(not salir):
	opcion= menu()
	if(opcion=='1'):
		alta(agenda)
	elif(opcion=='2'):
		baja(agenda)
	elif(opcion=='3'):
		modificacion(agenda)
	elif(opcion=='4'):
		buscar(agenda)
	elif(opcion=='5'):
		mostrar(agenda)
	elif(opcion=='0'):
		print("Terminamos")
		salir=True
	else:
		print("Opcion no valida")
print("Fin agenda listas")

if __name__ == '__main__':
    Vista.menuVista()