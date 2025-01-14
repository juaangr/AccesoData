package ficheros_serializados;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejer_operaciones_Agenda {

static Scanner sc = new Scanner(System.in);
	
	final static String NOM_FICHERO = "datos.ser";

	public static void main(String[] args) {
		System.out.println("Empezamos Ejer Serializados");
		crear3contactos();
		mostrar();
		borrar();
		mostrar2();
		
		System.out.println("Fin Ejer Serializados");

	}
	
	public static void crear3contactos() {
		FileOutputStream fo = null;
		BufferedOutputStream bo = null;
		ObjectOutputStream oout = null;
		
		ArrayList<Contacto> contactos = leer();
		try {
			fo = new FileOutputStream(NOM_FICHERO);
			bo = new BufferedOutputStream(fo);
			oout = new ObjectOutputStream(bo);
			for(Contacto con: contactos)
				oout.writeObject(con);

			Contacto c = new Contacto("nom_1","ape_1","dir_1","tel_1",(byte)25);			
			oout.writeObject(c);			
			c = new Contacto("nom_2","ape_2","dir_2","tel_2",(byte)29);
			oout.writeObject(c);
			
		} catch (IOException e) {
			System.out.println("Error");
		} finally {
			try {
				oout.close();
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
		escribirContacto("nom_3","ape_3","dir_3","tel_3",(byte)33);
	}
	
	public static void escribirContacto(String nom, String ape, String dir, String tel, byte edad) {
		FileOutputStream fo = null;
		BufferedOutputStream bo = null;
		ObjectOutputStream oout = null;
		ArrayList<Contacto> contactos = leer();		
		try {
			fo = new FileOutputStream(NOM_FICHERO);
			bo = new BufferedOutputStream(fo);
			oout = new ObjectOutputStream(bo);
			for(Contacto con: contactos)
				oout.writeObject(con);
			Contacto c = new Contacto(nom,ape,dir,tel,edad);			
			oout.writeObject(c);
						
		} catch (IOException e) {
			System.out.println("Error");
		} finally {
			try {
				oout.close();
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
		ObjectInputStream in = null;
		try {
			fi = new FileInputStream(NOM_FICHERO);
			bi = new BufferedInputStream(fi);
			in = new ObjectInputStream(bi);
			System.out.println("-- CONTACTOS --");
			System.out.println("---------------");
			while(true) {
				Contacto c = (Contacto)in.readObject();
				System.out.println(c);
			}
		} catch (ClassNotFoundException e) {
			//Este error no es producido por el bucle
			e.printStackTrace();
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
		ObjectInputStream in = null;
		try {
			fi = new FileInputStream(NOM_FICHERO);
			bi = new BufferedInputStream(fi);
			in = new ObjectInputStream(bi);
			System.out.println("-- CONTACTOS --");
			System.out.println("---------------");
			while(bi.available()>0) {
				Contacto c = (Contacto)in.readObject();
				System.out.println(c);
			}
		} catch (ClassNotFoundException e) {
			//Este error no es producido por el bucle
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error");
			//De esta forma no tiene que producirse ningun ERROR. Por eso dejo el print
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
		ObjectInputStream in = null;
		System.out.println("Introduce apellidos del contacto a borrar:");
		String ape_borrar = sc.nextLine();
		ArrayList<Contacto> contactos = new ArrayList();
		try {
			fi = new FileInputStream(NOM_FICHERO);
			bi = new BufferedInputStream(fi);
			in = new ObjectInputStream(bi);
			while(bi.available()>0) {
				Contacto c = (Contacto)in.readObject();
				if(c.getApellidos().equalsIgnoreCase(ape_borrar)) {
					System.out.println("Contacto encontrado:");
					System.out.println(c);
					System.out.println("Estas seguro de querer borrar el contacto? s/n");
					String opcion = sc.nextLine();
					if(!opcion.equalsIgnoreCase("s") && !opcion.equalsIgnoreCase("si")) {					
						contactos.add(c);
					}
				}else {
					contactos.add(c);
				}
			}
			guardar(contactos);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error");
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
	
	public static void guardar(ArrayList<Contacto> contactos) {
		FileOutputStream fo = null;
		BufferedOutputStream bo = null;
		ObjectOutputStream oout = null;
		
		try {
			fo = new FileOutputStream(NOM_FICHERO);
			bo = new BufferedOutputStream(fo);
			oout = new ObjectOutputStream(bo);
			for(Contacto con:contactos) {
				oout.writeObject(con);
			}
						
		} catch (Exception e) {
			System.out.println("Error");
		} finally {
			try {
				oout.close();
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
	
	public static ArrayList<Contacto> leer() {
		File f = new File(NOM_FICHERO);
		ArrayList<Contacto> contactos = new ArrayList();
		if(f.exists()) {
			FileInputStream fi = null;
			BufferedInputStream bi = null;
			ObjectInputStream in = null;
			try {
				fi = new FileInputStream(NOM_FICHERO);
				bi = new BufferedInputStream(fi);
				in = new ObjectInputStream(bi);
				while(bi.available()>0) {
					Contacto c = (Contacto)in.readObject();
					contactos.add(c);
				}
			} catch (ClassNotFoundException e) {
				//Este error no es producido por el bucle
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error");
				//De esta forma no tiene que producirse ningun ERROR. Por eso dejo el print
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
		return contactos;
		
	}

}
