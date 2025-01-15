import pickle

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

with open('ficheroObjetos.dat', 'wb') as f:
    pickle.dump(lista, f)
    pickle.dump(coche1, f)

lista=[]
with open('ficheroObjetos.dat', 'rb') as f:
    lista=pickle.load(f)
    coche5=pickle.load(f)
    
for coche in lista:
    print(coche)

print(coche5)

lista=[]
try:    
    with open('ficheroObjetos', 'rb') as f:
        while True:
            lista.append(pickle.load(f))
except:
    pass
for i in lista:
    print(type(i),i)
