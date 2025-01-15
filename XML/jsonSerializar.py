import json

class Coche:

    def __init__(self, matricula="",marca="ninguna"):
        self.matricula = matricula
        self.marca = marca
        
    def __str__(self):
        return self.matricula+" "+self.matricula
    

coche1 = Coche()
coche1.matricula="1"
coche2 = Coche()
coche2.matricula="2"
coche3 = Coche()
coche3.matricula="3"
lista=[coche1,coche2,coche3]
dic={"1":"coche1"}

fichero=open('ejemplo.json', mode='w', encoding='utf-8')
#objeto=json.dumps(dic, fichero)
objeto=json.dumps(coche1.__dict__)
fichero.write(objeto)
objeto=json.dumps(coche2.__dict__)
fichero.write(objeto)
objeto=json.dumps(coche3.__dict__)
fichero.write(objeto)
objeto=json.dumps(dic)
fichero.write(objeto)
fichero.close()


with open('ejemplo.json') as json_data:
    d = json.load(json_data)
    print(d)

    
print("Fin")
