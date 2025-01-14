package ficheros_Binarios;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejer2 {
	
	static Scanner sc = new Scanner(System.in);
	
	final static String NOM_FICHERO = "datos2.bin";

	public static void main(String[] args) {
		System.out.println("Empezamos Ejer2");
		crear3contactos();
		mostrar();
		borrar();
		mostrar();
		
		System.out.println("Fin Ejer2");

	}
	
	public static void crear3contactos() {
		FileOutputStream fo = null;
		BufferedOutputStream bo = null;
		DataOutputStream dout = null;
		
		try {
			fo = new FileOutputStream(NOM_FICHERO);
			bo = new BufferedOutputStream(fo);
			dout = new DataOutputStream(bo);
			dout.writeUTF("nom_1");
			dout.writeUTF("ape_1");
			dout.writeUTF("dir_1");
			dout.writeUTF("tel_1");
			dout.writeInt(25);

			dout.writeUTF("nom_2");
			dout.writeUTF("ape_2");
			dout.writeUTF("dir_2");
			dout.writeUTF("tel_2");
			dout.writeInt(29);
			
		} catch (IOException e) {
			System.out.println("Error");
		} finally {
			try {
				dout.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar");
			}finally {
				try {
					bo.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar2");
				}finally {
					try {
						fo.close();
					} catch (IOException e) {
						System.out.println("Error al cerrar3");
					}
				}
			}
		}
		escribirContacto("nom_3","ape_3","dir_3","tel_3",33);
	}
	
	public static void escribirContacto(String nom, String ape, String dir, String tel, int edad) {
		FileOutputStream fo = null;
		BufferedOutputStream bo = null;
		DataOutputStream dout = null;
		
		try {
			fo = new FileOutputStream(NOM_FICHERO,true);
			bo = new BufferedOutputStream(fo);
			dout = new DataOutputStream(bo);
			dout.writeUTF(nom);
			dout.writeUTF(ape);
			dout.writeUTF(dir);
			dout.writeUTF(tel);
			dout.writeInt(edad);			
		} catch (IOException e) {
			System.out.println("Error");
		} finally {
			try {
				dout.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar");
			}finally {
				try {
					bo.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar2");
				}finally {
					try {
						fo.close();
					} catch (IOException e) {
						System.out.println("Error al cerrar3");
					}
				}
			}
		}
	}
	
	public static void mostrar() {
		FileInputStream fi = null;
		BufferedInputStream bi = null;
		DataInputStream in = null;
		try {
			fi = new FileInputStream(NOM_FICHERO);
			bi = new BufferedInputStream(fi);
			in = new DataInputStream(bi);
			System.out.println("-- CONTACTOS --");
			System.out.println("---------------");
			while(true) {
				String nom = in.readUTF();
				String ape = in.readUTF();
				String dir = in.readUTF();
				String tel = in.readUTF();
				int edad = in.readInt();
				System.out.println("Contacto:"+nom+" ,"+ape+" ,"+dir+" ,"+tel+" ,"+edad);
			}
		} catch (IOException e) {
			//System.out.println("Error");
			//No pongo nada porque el error se produce siempre
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar");
			}finally {
				try {
					bi.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar");
				}finally {
					try {
						fi.close();
					} catch (IOException e) {
						System.out.println("Error al cerrar");
					}
				}
			}
		}
		
	}
	
	public static void mostrar2() {
		FileInputStream fi = null;
		BufferedInputStream bi = null;
		DataInputStream in = null;
		try {
			fi = new FileInputStream(NOM_FICHERO);
			bi = new BufferedInputStream(fi);
			in = new DataInputStream(bi);
			System.out.println("-- CONTACTOS --");
			System.out.println("---------------");
			while(bi.available()>0) {
				String nom = in.readUTF();
				String ape = in.readUTF();
				String dir = in.readUTF();
				String tel = in.readUTF();
				int edad = in.readInt();
				System.out.println("Contacto:"+nom+" ,"+ape+" ,"+dir+" ,"+tel+" ,"+edad);
			}
		} catch (IOException e) {
			System.out.println("Error");
			//De esta forma no tiene que producirse ningun ERROR
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar");
			}finally {
				try {
					bi.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar");
				}finally {
					try {
						fi.close();
					} catch (IOException e) {
						System.out.println("Error al cerrar");
					}
				}
			}
		}
		
	}
	
	public static void borrar() {
		FileInputStream fi = null;
		BufferedInputStream bi = null;
		DataInputStream in = null;
		System.out.println("Introduce apellidos del contacto a borrar:");
		String ape_borrar = sc.nextLine();
		ArrayList<String> contactos = new ArrayList();
		try {
			fi = new FileInputStream(NOM_FICHERO);
			bi = new BufferedInputStream(fi);
			in = new DataInputStream(bi);
			while(bi.available()>0) {
				String nom = in.readUTF();
				String ape = in.readUTF();
				String dir = in.readUTF();
				String tel = in.readUTF();
				int edad = in.readInt();
				if(ape.equalsIgnoreCase(ape_borrar)) {
					System.out.println("Contacto encontrado:");
					System.out.println(nom+";"+ape+";"+dir+";"+tel+";"+edad);
					System.out.println("Estas seguro de querer borrar el contacto? s/n");
					String opcion = sc.nextLine();
					if(!opcion.equalsIgnoreCase("s") && !opcion.equalsIgnoreCase("si")) {
						String contacto = nom+";"+ape+";"+dir+";"+tel+";"+edad;
						contactos.add(contacto);
					}
				}else {
					String contacto = nom+";"+ape+";"+dir+";"+tel+";"+edad;
					contactos.add(contacto);
				}
			}
			guardar(contactos);
		} catch (IOException e) {
			System.out.println("Error");
			//De esta forma no tiene que producirse ningun ERROR
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar");
			}finally {
				try {
					bi.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar");
				}finally {
					try {
						fi.close();
					} catch (IOException e) {
						System.out.println("Error al cerrar");
					}
				}
			}
		}
		
	}
	
	public static void guardar(ArrayList<String> contactos) {
		FileOutputStream fo = null;
		BufferedOutputStream bo = null;
		DataOutputStream dout = null;
		
		try {
			fo = new FileOutputStream(NOM_FICHERO);
			bo = new BufferedOutputStream(fo);
			dout = new DataOutputStream(bo);
			for(String con:contactos) {
				String campos[]=con.split(";");
				if(campos.length==5) {
					dout.writeUTF(campos[0]);
					dout.writeUTF(campos[1]);
					dout.writeUTF(campos[2]);
					dout.writeUTF(campos[3]);
					dout.writeInt(Integer.parseInt(campos[4]));
				}
				
			}
						
		} catch (Exception e) {
			System.out.println("Error");
		} finally {
			try {
				dout.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar");
			}finally {
				try {
					bo.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar2");
				}finally {
					try {
						fo.close();
					} catch (IOException e) {
						System.out.println("Error al cerrar3");
					}
				}
			}
		}
		
	}

}

