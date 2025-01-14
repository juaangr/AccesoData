package ficheros_texto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Empezamos");
		//escribir();
		//leer();
		escribir2();
		leer2();
		System.out.println("Fin");
	}
	
	//Ejemplo 2
	//Usando BUFFERS
	private static void escribir2() {
		System.out.println("escribir");
		File file = new File(
	            "fichero2.txt");
		// Passing the path to the file as a parameter
		FileWriter fw;
		BufferedWriter bw;
		try {
			//fw = new FileWriter(file,true);//No Borro el contenido anterior
			fw = new FileWriter(file);//Borro el contenido anterior
			bw = new BufferedWriter(fw);
			bw.write("Linea 1\n");
			bw.write("Linea 2\n");
			bw.write("Linea 3\n");
			bw.close();
			fw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void leer2() {
		System.out.println("leer");
		File file = new File(
	            "fichero2.txt");
		// Passing the path to the file as a parameter
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			System.out.println("FICHERO");
			String linea;
			// Holds true till there is nothing to read
			while ((linea = br.readLine()) != null) {
				// Print all the content of a file
				System.out.println(linea);
			}
			br.close();
			fr.close();
			System.out.println("Fin leer2");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Ejemplo 1
	private static void escribir() {
		System.out.println("escribir");
		File file = new File(
	            "fichero1.txt");
		// Passing the path to the file as a parameter
		FileWriter fw;
		try {
			//fw = new FileWriter(file,true);//No Borro el contenido anterior
			fw = new FileWriter(file);//Borro el contenido anterior
			fw.write("Texto de ejemplo");
			fw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void leer() {
		System.out.println("leer");
		File file = new File(
	            "fichero1.txt");
		// Passing the path to the file as a parameter
		FileReader fr;
		try {
			fr = new FileReader(file);
			// Declaring loop variable
			int i;
			// Holds true till there is nothing to read
			while ((i = fr.read()) != -1) {
				// Print all the content of a file
				System.out.print((char) i);
			}
			fr.close();
			System.out.println();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
