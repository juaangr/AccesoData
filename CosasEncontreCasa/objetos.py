#clase con todos los objetos 
class Empleado():
    emplcont = 0
    
    def __init__(self, nombre="", salario=0):
        self.nombre = nombre
        self.salario = salario
        Empleado.emplcont += 1

    def contador(self):
        print(f"Los empleados totales son: {Empleado.emplcont}")
        
    def empleados(self):
        print("Nombre: " , self.nombre, " - Y su salario es de: ", self.salario)
        
    def __del__(self):
        class_name = self.__class__.__name__
        print(class_name, "Destruido")
        
    def __str__(self):
        return  str(self.nombre) + "-" +str(self.salario)

klk = Empleado("Juan", 12000)

print("Empezamos")
print(klk)
print("Final")