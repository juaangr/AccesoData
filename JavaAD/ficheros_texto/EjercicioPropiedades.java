package ficheros_texto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class EjercicioPropiedades {

	public static void main(String[] args) {
		System.out.println("Empezamos");
		
		HashMap<String,String> datos = leer("datos.prop");
		mostrar(datos);
		
		System.out.println("Fin");
	}
	
	public static void mostrar(HashMap<String,String> datos) {
		for(String campo:datos.keySet()) {
			System.out.println("Campo: "+campo+" -> valor: "+datos.get(campo));
		}
	}
	
	public static HashMap<String,String> leer(String fichero) {
		HashMap<String,String> propiedades = new HashMap<String,String>();
		
		File file = new File(
	            fichero);
		// Passing the path to the file as a parameter
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String linea;
			// Holds true till there is nothing to read
			while ((linea = br.readLine()) != null) {
				// Print all the content of a file
				//System.out.println(linea);
				if(linea.trim().length()>2) {
					String [] campos = linea.trim().split("=");
					if(campos.length == 2) {
						if(propiedades.containsKey(campos[0]))
							System.out.println("Campo ya repetido");
						else
							propiedades.put(campos[0], campos[1]);
					}
				}
				
				
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
		
		
		return propiedades;
	}
	
	

}
