import xml.etree.ElementTree as ET

# Quiero hacer un CRUD de XML


#para el elemento hace un pretty print de un string XML 
def prettify(elem):
    from xml.etree import ElementTree
    from xml.dom import minidom
    
# este metodo devuelve el elemento raíz de un archivo XML    
def leerXml(archivo): 
    tree = ET.parse(archivo)
    root = tree.getroot()
    return root


def guardarXml(tree, archivo):
    out = prettify(tree)
    file = open(archivo, "w")
    file.write(out)
    file.close()