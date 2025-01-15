import xml.etree.ElementTree as ET
from xml.dom import minidom

# Quiero hacer un CRUD de XML

# va a convertir el ELEMento introducido en un string
# luego vamos a parsearlo con minidom parseString
# y a imprimirlo cn toprettyxml
def prettyPrint(elem):
    roughString = ET.tostring(elem)
    parsed = minidom.parseString(roughString)
    return parsed.toprettyxml(indent= "  ")

# crear xml 
def createXml(root):
    root = ET.Element(input("Introduce el elemento raiz"))
    return root

# vamos a guardar la salida del archivo
# 
def saveXml(tree, file):
    salida = prettyPrint(tree)
    file = open(file,"w")
    file.write(salida)
    file.close()