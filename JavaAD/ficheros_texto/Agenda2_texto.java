package ficheros_texto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Agenda2_texto {

	static Scanner sc = new Scanner(System.in);
	
	final static String NOM_FICHERO = "agenda2.txt";

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
					//System.out.println("Buscar");
					buscar();
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
				NOM_FICHERO);
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
			bw.write(nom+"\n"+ape+"\n"+dir+"\n"+tel+"\n"+edad+"\n\n");
			
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
		File file = new File(
				NOM_FICHERO);
		if(file.exists()) {
			System.out.println("Introduce apellidos a buscar:");
			String ape_bus = sc.nextLine();
			// Passing the path to the file as a parameter
			FileReader fr=null;
			BufferedReader br=null;
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String linea;
				boolean encontrado = false;
				// Holds true till there is nothing to read
				while ((linea = br.readLine()) != null) {
					if(linea.trim().length()!=0) {//Salto posibles lineas vacias iniciales
						String nombre = linea;
						String ape = br.readLine().trim();
						String dir = br.readLine();
						String tel = br.readLine();
						String edad = br.readLine();
						br.readLine();
						if(ape_bus.equalsIgnoreCase(ape)) {
							System.out.println("Contacto encontrado:");
							System.out.println(nombre+" "+ape+" "+dir+" "+tel+" "+edad);
							encontrado = true;
						}
					}
				}
				if(!encontrado) {
					System.out.println("Contacto no encontrado");
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
		}else {
			System.out.println("Agenda no existente");
		}
	}
	
	public static void mostrar() {
		System.out.println("Mostrar");
		File file = new File(
				NOM_FICHERO);
		if(file.exists()) {
			// Passing the path to the file as a parameter
			FileReader fr=null;
			BufferedReader br=null;
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				System.out.println("-------------");
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
		}else {
			System.out.println("Agenda no existente");
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
