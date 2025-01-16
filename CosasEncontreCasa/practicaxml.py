import xml.etree.ElementTree as ET

#devuelve el elemento raiz del archivo xml 
def leerXML(fichero):
    arbol = ET.parse(fichero)
    root = arbol.getroot()
    return root    


#esta def va a crear un coche dentro del tag root concesionario 
#especificando la marca, matricula...
def ejer_1():
    documento = ET.Element('concesionario')
    
    NoMasCars = False
    while (not NoMasCars):
        coche = ET.SubElement(documento, 'coche')
        matri = input("Introduce la matricula")
        marc = input("Introduce la marca")
        color = input("Introduce el color")
        model = input("Introduce el modelo")
        
        matriXML = ET.SubElement(coche, 'matricula')
        matriXML = matri
        marcXML = ET.SubElement(coche, 'marca')
        marcXML = marc
        colorXML = ET.SubElement(coche, 'color')
        colorXML = color
        modelXML = ET.SubElement(coche, 'modelo')
        modelXML = model
        finalCreo = input('Quieres introducir otro coche?(si/no)')
        if (finalCreo.upper()=='NO'):
            NoMasCars=True
    
    print((documento))
    guardarXML(documento, "comprueba.xml")
    return(documento)

#esta def lo guarda "bonito" 
def guardarXML(arbol, fichero):
    salida = embellecedor(arbol)
    file = open(fichero, "w")
    file.write(salida)
    file.close()


def embellecedor(elem):
    from xml.etree import ElementTree
    from xml.dom import minidom
    # basicamente devuelve el elemento en "bonito", un formato mas legible
    rough_string = ElementTree.tostring(elem, 'utf-8')
    reparsed = minidom.parseString(rough_string)
    return reparsed.toprettyxml(indent="  ")

#fin ejercicio1

#en este segundo ejercicio vamos a tener que buscar los coches
#para poder borrar especificamente el que deseemos
#ademas de borrar todos los que deseamos

#invocamos una funcionalidad que nos permita buscar dependiendo de la matricula del vehículo
#ya que la matricula no se repite en ningun coche (es el atr que mas les identifica)
def buscar_car(concesi, matri): 
    posicion =  0
    for coche in concesi:
        if (coche[0].text==matri):
            return posicion
        pos +=1
    return -1    

#este metodo sera el que borre los coches deseados usando buscar_car
def ejer_2():
    concesi = leerXML("comprueba.xml")
    print(embellecedor(concesi))
    