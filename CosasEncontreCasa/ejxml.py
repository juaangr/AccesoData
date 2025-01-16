'''
Coche
    Matricula
    Marca
    Modelo
    Color

Ejer1.
Crear tantos coches como el usuario quiera. Después, se muestra el XML y se guarda en fichero

Ejer2.
A partir del fichero creado en el apartado anterior, permitir borrar tantos coches como se quiera
'''

import xml.etree.ElementTree as ET

def guardarXml(arbol,fichero):
    salida = prettify(arbol)
    file = open(fichero,"w")
    file.write(salida)
    file.close()
    
def ejer1():
    #arbol = ET.parse("agenda.xml")
    documento = ET.Element('concesionario')
    
    terminar = False
    while(not terminar):
        coche = ET.SubElement(documento, 'coche')
        mat = input("Introduce matricula:")
        mar = input("Introduce marca:")
        mod = input("Introduce modelo:")
        col = input("Introduce color:")
    
        matXml = ET.SubElement(coche, 'matricula')
        matXml.text = mat
        marXml = ET.SubElement(coche, 'marca')
        marXml.text = mar
        modXml = ET.SubElement(coche, 'modelo')
        modXml.text = mod
        colXml = ET.SubElement(coche, 'color')
        colXml.text = col
        opcion = input("Quieres introducir otro coche?(no/otro)")
        if(opcion.upper()=="NO"):
            terminar = True
    
    print(prettify(documento))
    guardarXml(documento,"ejer1.xml")
    
def ejer1_2():
    #arbol = ET.parse("agenda.xml")
    documento = ET.Element('concesionario')
    
    terminar = False
    while(not terminar):
        coche = ET.Element('coche')
        mat = input("Introduce matricula:")
        mar = input("Introduce marca:")
        mod = input("Introduce modelo:")
        col = input("Introduce color:")
    
        matXml = ET.SubElement(coche, 'matricula')
        matXml.text = mat
        marXml = ET.SubElement(coche, 'marca')
        marXml.text = mar
        modXml = ET.SubElement(coche, 'modelo')
        modXml.text = mod
        colXml = ET.SubElement(coche, 'color')
        colXml.text = col
        opcion = input("Estás seguro de querer añadir el coche?(si/no)")
        if(opcion.lower()=="si" or opcion.lower()=="s"):
            documento.append(coche)
        
        opcion = input("Quieres introducir otro coche?(no/otro)")
        if(opcion.upper()=="NO"):
            terminar = True
    
    print(prettify(documento))
    guardarXml(documento,"ejer1.xml")
    return documento
    
def prettify(elem):
    from xml.etree import ElementTree
    from xml.dom import minidom
    """Return a pretty-printed XML string for the Element.
    """
    rough_string = ElementTree.tostring(elem, 'utf-8')
    reparsed = minidom.parseString(rough_string)
    return reparsed.toprettyxml(indent="  ")

#Metodo que devuelve el elemento raiz de un archivo XML
def leerXml(fichero):
	arbol = ET.parse(fichero)
	root = arbol.getroot()
	return root

#Mostrar el arbol manualmente
def mostrar(root):
	cont = 1
	for coche in root:
		print("Coche:",cont)
		cont+=1
		for elemento in coche:
			print("\t",elemento.tag,":",elemento.text)
	
	#print(root[1][0])
	#print(root[1][0].tag,root[1][0].text)
	
def buscar(conce,mat):
	pos = 0
	for coche in conce:
		if(coche[0].text==mat):
			return pos
		pos+=1
	return -1
	
def ejer2():
	conce = leerXml("ejer1.xml")	
	print(prettify(conce))
	mostrar(conce)
	
	#print("--------------")
	#del(conce[1])
	#conce.pop(1)
	
	mat = input("Introduce la matricula del coche a borrar:")
	pos = buscar(conce,mat)
	if(pos!=-1):
		coche = conce[pos][0].txt
		del(conce[pos])
		print(coche,"borrado")
	else:
		print("Coche no encontrado")
	opcion = input("Quieres borrar otro coche?(si/otro)")
	while(opcion.upper()=="SI"):
		mat = input("Introduce la matricula del coche a borrar:")
		pos = buscar(conce,mat)
		if(pos!=-1):
			coche = conce[pos][0].txt
			del(conce[pos])
			print(coche,"borrado")
		else:
			print("Coche no encontrado")
		opcion = input("Quieres borrar otro coche?(si/otro)")
		
	
	mostrar(conce)
	guardarXml(conce,"ejer1.xml")
	
	
	
def devolver2cosas():
	return 5,7


print("Empezamos")

root = ejer1_2()
n1 = ET.Element("dueno")
n1.text = "Juan"
root.append(n1)
guardarXml(root,"ejer1.xml")

conce = leerXml("ejer1.xml")	
print(prettify(conce))
mostrar(conce)


#ejer2()

#a,b = devolver2cosas()
#print(a,b)

print("Fin")