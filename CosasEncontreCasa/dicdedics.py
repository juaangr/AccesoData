def alta(agenda):
    print("ALTA")
    nom=input("Introduce tu nombre:")
    ape=input("Introduce tus apellidos:")
    dire=input("Introduce tu direccion:")
    tel=input("Introduce tu telefono")
    edad=input("Introduce tu edad:")
    contacto = {nom:nom.strip(), ape:ape.strip(), dire:dire.strip(), tel:tel.strip(), edad:edad.strip()}
    if (ape in agenda.keys()): #si ya existe un contacto con ese apellido....
        if (type(agenda[ape]) is list):#si hay más de un contacto 
         listaCon=agenda[ape]
         repetido=False
         cont=0
         while(cont<len(listaCon and not repetido)):
            contac=listaCon[cont]
            print("XXX", contac, tel)
            if (contac [tel]==tel):
              repetido=True
            cont += 1 
         if (not repetido): 
            agenda[ape].append(contacto)
         else:
            print("Contacto repetido, no puede haber 2 contactos con el mismo telefono")    
        else: #si solo existe un contacto
         if (tel==agenda[ape]["tel"]):
            print("Contacto repetido, no puede haber 2 contactos con el mismo telefono")
         else:
            listaCon=[agenda[ape], contacto]
            agenda[ape]=listaCon
    else:
        agenda[ape]=contacto
        
def baja(agenda):
    print("baja")