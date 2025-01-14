package ficheros_texto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejer_matriz {

	static Scanner sc = new Scanner(System.in);
	
	final static String NOM_FICHERO = "matriz.txt";
	final static byte ALTO = 15;
	final static byte ANCHO = 5;
	final static byte LIMITE = 99;

	public static void main(String[] args) {
		System.out.println("Empezamos");
		generar();
		mostrar();
		sumar();
		System.out.println("Fin");
	}
	
	public static void generar() {
		File file = new File(
				NOM_FICHERO);
		// Passing the path to the file as a parameter
		FileWriter fw=null;
		BufferedWriter bw=null;
		try {
			//fw = new FileWriter(file,true);//No Borro el contenido anterior
			fw = new FileWriter(file);//Borro el contenido anterior
			bw = new BufferedWriter(fw);
			for(int i=0;i<ALTO;i++) {
				for(int j=0;j<ANCHO-1;j++) {
					int valor = (int)(Math.random()*(LIMITE+1));
					bw.write(valor+"\t");
				};
				bw.write(((int)(Math.random()*(LIMITE+1)))+"\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
	
	public static void mostrar() {
		File file = new File(
				NOM_FICHERO);
		if(file.exists()) {
			FileReader fr=null;
			BufferedReader br=null;
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String linea;
				while ((linea = br.readLine()) != null) {
					// Print all the content of a file
					System.out.println(linea);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
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
			System.out.println("Fichero no existente");
		}
	}
	
	public static void sumar() {
		File file = new File(
				NOM_FICHERO);
		if(file.exists()) {
			int suma = 0;
			FileReader fr=null;
			BufferedReader br=null;
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String linea;
				while ((linea = br.readLine()) != null) {
					String datos[] = linea.split("\t");
					for(int i=0;i<datos.length;i++) {
						suma += Integer.parseInt(datos[i]);
					}
				}
				System.out.println("\n\nLa suma es:"+suma);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
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
			System.out.println("Fichero no existente");
		}
	}

}
