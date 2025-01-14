package ficheros_texto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Agenda1_texto {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Empezamos AGENDA1");
		String opcion="";
		do {
			opcion = menu();
			switch(opcion) {
				case "1":
					//System.out.println("Alta");
					alta();
					break;
				case "2":
					System.out.println("Baja");
					break;
				case "3":
					System.out.println("Modificacion");
					break;
				case "4":
					System.out.println("Buscar");
					break;
				case "5":
					//System.out.println("Mostrar Todos");
					mostrar();
					break;
				case "0":
					System.out.println("TERMINAMOS");
					break;
				default:
					System.out.println("Opcion no valida");
			}
		}while(!opcion.equals("0"));
		
		
		System.out.println("Fin AGENDA1");
	}
	
	public static void alta() {
		System.out.println("Alta");
		File file = new File(
	            "agenda1.txt");
		// Passing the path to the file as a parameter
		FileWriter fw=null;
		BufferedWriter bw=null;
		try {
			//fw = new FileWriter(file,true);//No Borro el contenido anterior
			fw = new FileWriter(file,true);//Borro el contenido anterior
			bw = new BufferedWriter(fw);
			String nom,ape,dir,tel;
			int edad = -1;
			System.out.print("Introduce nombre:");
			nom = sc.nextLine();
			System.out.print("Introduce apellidos:");
			ape = sc.nextLine();
			System.out.print("Introduce direccion:");
			dir = sc.nextLine();
			System.out.print("Introduce telefono:");
			tel = sc.nextLine();
			boolean error = true;
			while(error) {
				System.out.print("Introduce edad:");
				try {
					edad = sc.nextInt();					
					error = false;
				}catch(Exception e) {
					System.out.println("Error en el valor de la edad");
				}
				sc.nextLine();//Limpiamos el buffer de lectura
			}			
			bw.write(nom+";"+ape+";"+dir+";"+tel+";"+edad+"\n");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				bw.close();
			} catch (IOException e) {
			} finally {
				try {
					fw.close();
				} catch (IOException e) {
				}
			}
			
			System.out.println("-------------");
		}
	}
	
	public static void baja() {
		System.out.println("Baja");
	}
	
	public static void modificacion() {
		System.out.println("Modificacion");
	}
	
	public static void buscar() {
		System.out.println("Buscar");
	}
	
	public static void mostrar() {
		System.out.println("Mostrar");
		File file = new File(
	            "agenda1.txt");
		// Passing the path to the file as a parameter
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			System.out.println("- CONTACTOS -");
			System.out.println("-------------");
			String linea;
			// Holds true till there is nothing to read
			while ((linea = br.readLine()) != null) {
				// Print all the content of a file
				System.out.println(linea);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
			} finally {
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.println("-------------");
		}
		
	}
	
	public static String menu() {
		System.out.println("\n\n\nAGENDA");
		System.out.println("1.Alta");
		System.out.println("2.Baja");
		System.out.println("3.Modificacion");
		System.out.println("4.Busqueda");
		System.out.println("5.Mostrar Todos");
		System.out.println("0.Salir");
		System.out.print("Introduce opcion:");
		String opcion = sc.nextLine();
		return opcion;
	}

}
