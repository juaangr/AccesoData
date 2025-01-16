# Module Imports
#import mariadb
import pymysql
import sys

print("Empezamos")

# Connect to MariaDB Platform
try:
    conn = pymysql.connect(
        user="root",
        password="my-secret-pw",
        host="localhost",
        port=3308,
        #db="prueba1",

    )
except pymysql.Error as e:
    print(f"Error connecting to MariaDB Platform: {e}")
    sys.exit(1)

# Get Cursor
cur = conn.cursor()
#cur.execute("use prueba1") seria redundante si ya lo pongo en la conexion

cur.execute("drop database if exists prueba1")
cur.execute("create database if not exists prueba1")
cur.execute("use prueba1")


cur.execute("drop table if exists Persona")
cur.execute("""CREATE TABLE Persona (
    PersonID integer NOT NULL AUTO_INCREMENT,
    apellidos varchar(50) NOT NULL,
    nombre varchar(25) not null,
    telefono varchar(15) unique,
    direccion varchar(100),
    ciudad varchar(50) DEFAULT 'Fuenla',
    edad int,
    primary key (personid),
    CHECK (edad>=1)
    )""")

cur.execute("drop table if exists Orders")
cur.execute("""
CREATE TABLE Orders (
    OrderID int NOT NULL,
    OrderNumber int NOT NULL,
    PersonID int,
    PRIMARY KEY (OrderID),
    FOREIGN KEY (PersonID) REFERENCES Persona(PersonID)
); """)

cur.close()
conn.close()


print("Fin")
