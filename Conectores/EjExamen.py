from errno import errorcode
import pymysql

# Crear la base de datos y las tablas necesarias

def crear_base_de_datos_y_tablas():
    try:
        # Conexión al servidor MySQL
        conexion = pymysql.connect(
            host="localhost",
            user="tu_usuario",
            password="tu_contraseña"
        )
        cursor = conexion.cursor()

        # Crear la base de datos si no existe
        cursor.execute("CREATE DATABASE IF NOT EXISTS tienda_discos")
        cursor.execute("USE tienda_discos")

        # Crear la tabla 'discos'
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS discos (
                id INT AUTO_INCREMENT PRIMARY KEY,  # ID único del disco
                titulo VARCHAR(100) NOT NULL,       # Título del disco
                autor VARCHAR(100) NOT NULL,        # Autor del disco
                formato ENUM('CD', 'DVD', 'Vinilo', 'Casete') NOT NULL,  # Formato del disco
                anio INT NOT NULL,                  # Año de publicación
                tipo_musica ENUM('Pop', 'Rock', 'Otros') NOT NULL,       # Tipo de música
                precio DECIMAL(10, 2) NOT NULL      # Precio del disco
            )
        """)

        # Crear la tabla 'ventas'
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS ventas (
                id INT AUTO_INCREMENT PRIMARY KEY,  # ID único de la venta
                disco_id INT NOT NULL,             # Referencia al disco vendido
                cantidad INT NOT NULL,             # Cantidad vendida
                dni_comprador VARCHAR(9) NOT NULL, # DNI del comprador
                precio_total DECIMAL(10, 2) NOT NULL,  # Precio total de la venta
                FOREIGN KEY (disco_id) REFERENCES discos(id)  # Relación con la tabla discos
            )
        """)

        # Confirmar cambios
        conexion.commit()
        print("Base de datos y tablas creadas correctamente.")

        # Insertar discos iniciales
        insertar_discos_iniciales(cursor)
        conexion.commit()

    except pymysql.Error as err:
        # Manejo de errores de conexión
        if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
            print("Error de acceso: verifica tu usuario y contraseña.")
        elif err.errno == errorcode.ER_BAD_DB_ERROR:
            print("La base de datos no existe.")
        else:
            print(err)
    finally:
        # Cerrar el cursor y la conexión
        cursor.close()
        conexion.close()

# Insertar discos iniciales en la tabla

def insertar_discos_iniciales(cursor):
    discos = [
        ("Thriller", "Michael Jackson", "Vinilo", 1982, "Pop", 25.99),
        ("Back in Black", "AC/DC", "CD", 1980, "Rock", 20.50),
        ("Dark Side of the Moon", "Pink Floyd", "Vinilo", 1973, "Rock", 30.00),
        ("Rumours", "Fleetwood Mac", "Casete", 1977, "Rock", 15.99),
        ("The Bodyguard", "Whitney Houston", "CD", 1992, "Pop", 19.99),
        ("Saturday Night Fever", "Bee Gees", "DVD", 1977, "Pop", 22.99),
        ("Abbey Road", "The Beatles", "Vinilo", 1969, "Rock", 28.50),
        ("Hotel California", "Eagles", "CD", 1976, "Rock", 21.00),
        ("Born to Run", "Bruce Springsteen", "Vinilo", 1975, "Rock", 24.50),
        ("Purple Rain", "Prince", "Casete", 1984, "Pop", 18.75)
    ]

    # Insertar registros de discos en la tabla 'discos'
    cursor.executemany("""
        INSERT INTO discos (titulo, autor, formato, anio, tipo_musica, precio)
        VALUES (%s, %s, %s, %s, %s, %s)
    """, discos)
    print("Discos iniciales insertados correctamente.")

# Modificar discos existentes en la tabla

def modificar_discos():
    try:
        # Conexión a la base de datos
        conexion = pymysql.connect(
            host="localhost",
            user="tu_usuario",
            password="tu_contraseña",
            database="tienda_discos"
        )
        cursor = conexion.cursor()

        while True:
            # Pedir ID del disco a modificar
            id_disco = input("Introduce el ID del disco a modificar (o 'salir' para terminar): ")
            if id_disco.lower() == 'salir':
                break

            # Comprobar si el disco existe
            cursor.execute("SELECT * FROM discos WHERE id = %s", (id_disco,))
            disco = cursor.fetchone()
            if not disco:
                print("El disco no existe.")
                continue

            # Elegir campo a modificar
            campo = input("Qué campo quieres modificar? (titulo/formato/precio): ").lower()
            if campo not in ['titulo', 'formato', 'precio']:
                print("Campo no válido.")
                continue

            # Pedir nuevo valor
            nuevo_valor = input(f"Introduce el nuevo valor para {campo}: ")

            # Confirmar modificación
            confirmar = input(f"Estás seguro de modificar {campo} a '{nuevo_valor}'? (s/n): ").lower()
            if confirmar != 's':
                print("Modificación cancelada.")
                continue

            # Actualizar el disco en la base de datos
            cursor.execute(f"UPDATE discos SET {campo} = %s WHERE id = %s", (nuevo_valor, id_disco))
            conexion.commit()
            print("Disco modificado correctamente.")

    except pymysql.Error as err:
        print("Error: ", err)

    finally:
        # Cerrar la conexión
        cursor.close()
        conexion.close()

# Realizar ventas de discos

def realizar_venta():
    try:
        # Conexión a la base de datos
        conexion = pymysql.connect(
            host="localhost",
            user="tu_usuario",
            password="tu_contraseña",
            database="tienda_discos"
        )
        cursor = conexion.cursor()

        # Pedir ID del disco
        for intentos in range(5):
            disco_id = input("Introduce el ID del disco a vender: ")
            cursor.execute("SELECT precio FROM discos WHERE id = %s", (disco_id,))
            disco = cursor.fetchone()
            if disco:
                precio_unitario = disco[0]
                break
            else:
                print("ID no válido. Intenta de nuevo.")
        else:
            print("Demasiados intentos fallidos.")
            return

        # Pedir cantidad
        for intentos in range(5):
            try:
                cantidad = int(input("Introduce la cantidad a vender: "))
                if cantidad > 0:
                    break
            except ValueError:
                print("Cantidad no válida. Intenta de nuevo.")
        else:
            print("Demasiados intentos fallidos.")
            return

        # Pedir DNI del comprador
        for intentos in range(5):
            dni = input("Introduce el DNI del comprador: ")
            if len(dni) == 9 and dni[:-1].isdigit() and dni[-1].isalpha():
                break
            else:
                print("DNI no válido. Intenta de nuevo.")
        else:
            print("Demasiados intentos fallidos.")
            return

        # Calcular precio total
        precio_total = precio_unitario * cantidad
        print(f"Precio total: {precio_total:.2f}")

        # Confirmar la venta
        confirmar = input("Confirmar venta? (s/n): ").lower()
        if confirmar != 's':
            print("Venta cancelada.")
            return

        # Registrar la venta en la base de datos
        cursor.execute("""
            INSERT INTO ventas (disco_id, cantidad, dni_comprador, precio_total)
            VALUES (%s, %s, %s, %s)
        """, (disco_id, cantidad, dni, precio_total))
        conexion.commit()
        print("Venta registrada correctamente.")

    except pymysql.Error as err:
        print("Error: ", err)

    finally:
        # Cerrar la conexión
        cursor.close()
        conexion.close()

# Mostrar todas las ventas

def mostrar_ventas():
    try:
        # Conexión a la base de datos
        conexion = pymysql.connect(
            host="localhost",
            user="tu_usuario",
            password="tu_contraseña",
            database="tienda_discos"
        )
        cursor = conexion.cursor()

        # Recuperar ventas
        cursor.execute("SELECT * FROM ventas")
        ventas = cursor.fetchall()

        # Mostrar ventas
        print("Ventas realizadas:")
        for venta in ventas:
            print(venta)

    except pymysql.Error as err:
        print("Error: ", err)

    finally:
        # Cerrar la conexión
        cursor.close()
        conexion.close()

# Mostrar discos que nunca se han vendido

def mostrar_discos_no_vendidos():
    try:
        # Conexión a la base de datos
        conexion = pymysql.connect(
            host="localhost",
            user="tu_usuario",
            password="tu_contraseña",
            database="tienda_discos"
        )
        cursor = conexion.cursor()

        # Consultar discos no vendidos
        cursor.execute("""
            SELECT * FROM discos
            WHERE id NOT IN (SELECT disco_id FROM ventas)
        """)
        discos = cursor.fetchall()

        # Mostrar discos no vendidos
        print("Discos no vendidos:")
        for disco in discos:
            print(disco)

    except pymysql.Error as err:
        print("Error: ", err)

    finally:
        # Cerrar la conexión
        cursor.close()
        conexion.close()

# Programa principal

def main():
    while True:
        print("""
        Menú Principal:
        1. Crear base de datos y tablas
        2. Modificar discos
        3. Realizar venta
        4. Mostrar ventas
        5. Mostrar discos no vendidos
        6. Salir
        """)

        opcion = input("Elige una opción: ")

        if opcion == '1':
            crear_base_de_datos_y_tablas()
        elif opcion == '2':
            modificar_discos()
        elif opcion == '3':
            realizar_venta()
        elif opcion == '4':
            mostrar_ventas()
        elif opcion == '5':
            mostrar_discos_no_vendidos()
        elif opcion == '6':
            print("Saliendo del programa.")
            break
        else:
            print("Opción no válida. Intenta de nuevo.")

if __name__ == "__main__":
    main()

#1. Crea una función que cree la base de datos en caso de no existir. Se deberá crear la base de datos (create database ...) además de las tablas. Las tablas deberán contener las restricciones que sean necesarias. (2 puntos)
#2. Crear una función que sea llamada automáticamente después de crear las tablas, "solo" si las tablas se han creado. Esta función creará al menos 10 discos con los distintos formatos y tipos de música. El resto de campos podrán rellenarse a elección vuestra (aleatorios, fijos, ...). (1 punto)
#3. Crea una función diferente para realizar cada una de las siguientes operaciones:
  #A. Modificar discos. Permitir al usuario la modificación de los campos título, formato o precio; a elección del usuario. Deberá permitir modificaciones consecutivas (modificar distintos discos sin volver al menú). Antes de que se guarde la modificación, y no al principio, el programa pedirá confirmación al usuario. (2,5 puntos)
  #B. Realizar ventas. Se pedirá campo a campo y se validarán convenientemente. Se dará al usuario 5 oportunidades para introducir cada campo correctamente. Si sobrepasa los 5 intentos se cancelará la operación y no se pedirá el siguiente campo. (2,5 puntos)
  #C. Mostrar ventas (0,5 puntos)
  #D. Mostrar discos que no se han vendido nunca. Se debe hacer con una consulta SQL. Si se hace programando se penalizará. (1 punto)
#4. Realiza un programa principal que utilice todas las funciones anteriores de la manera más adecuada. (0,5 puntos)    
