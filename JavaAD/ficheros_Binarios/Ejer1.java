package ficheros_Binarios;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejer1 {

	final static String NOM_FICHERO = "datos.bin";

	public static void main(String[] args) {
		System.out.println("Empezamos");
		escribir3datos_2();
		leer3datos_2();
		System.out.println("Fin");

	}
	
	public static void escribir3datos() {
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(NOM_FICHERO)));
			out.writeByte((byte) 123);
			out.writeShort((short) 1_234);
			out.writeInt(1_234_567);
			out.writeLong(1_234_567_890_123_456L);
			out.writeFloat((float) Math.E);
			out.writeDouble(Math.PI);
			out.writeBoolean(true);
			out.writeChar('€');
		} catch (IOException e) {
			System.out.println("Error");
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar");
			}
		}
	}
	
	public static void escribir3datos_2() {
		FileOutputStream fo = null;
		BufferedOutputStream bo = null;
		DataOutputStream dout = null;
		
		try {
			fo = new FileOutputStream(NOM_FICHERO);
			bo = new BufferedOutputStream(fo);
			dout = new DataOutputStream(bo);
			/*
			 Sin buffer
			 fo = new FileOutputStream(NOM_FICHERO);			
			 dout = new DataOutputStream(fo);
			*/
			
			
			dout.writeByte((byte) 123);
			dout.writeShort((short) 1_234);
			dout.writeInt(1_234_567);
			dout.writeLong(1_234_567_890_123_456L);
			dout.writeFloat((float) Math.E);
			dout.writeDouble(Math.PI);
			dout.writeBoolean(true);
			dout.writeChar('€');
			//dout.writeUTF("asdfads");
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
	
	public static void leer3datos() {
		DataInputStream in = null;
		try {
			in = new DataInputStream(new BufferedInputStream(new FileInputStream(NOM_FICHERO)));
			System.out.println(in.readByte());
			System.out.println(in.readShort());
			System.out.println(in.readInt());
			System.out.println(in.readLong());
			System.out.println(in.readFloat());
			System.out.println(in.readDouble());
			System.out.println(in.readBoolean());
			System.out.println(in.readChar());
		} catch (IOException e) {
			System.out.println("Error");
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar");
			}
		}
	}
	
	public static void leer3datos_2() {
		FileInputStream fi = null;
		BufferedInputStream bi = null;
		DataInputStream in = null;
		try {
			fi = new FileInputStream(NOM_FICHERO);
			bi = new BufferedInputStream(fi);
			in = new DataInputStream(bi);
			//in = new DataInputStream(new BufferedInputStream(new FileInputStream(NOM_FICHERO)));
			System.out.println(in.readByte());
			System.out.println(in.available()+"*");
			System.out.println(in.readShort());
			System.out.println(in.readInt());
			System.out.println(in.readLong());
			System.out.println(in.readFloat());
			System.out.println(in.readDouble());
			System.out.println(in.readBoolean());
			System.out.println(in.available()+"*");
			System.out.println(in.readChar());
			System.out.println(in.available()+"*");
		} catch (IOException e) {
			System.out.println("Error");
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar");
			}
		}
	}

	public static void escribir3datos_prohibido() {
		try (var out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(NOM_FICHERO)))) {
			out.writeByte((byte) 123);
			out.writeShort((short) 1_234);
			out.writeInt(1_234_567);
			out.writeLong(1_234_567_890_123_456L);
			out.writeFloat((float) Math.E);
			out.writeDouble(Math.PI);
			out.writeBoolean(true);
			out.writeChar('€');
		} catch (IOException e) {
			System.out.println("Error");
		}
	}

	public static void leer3datos_prohibido() {
		try (var in = new DataInputStream(new BufferedInputStream(new FileInputStream(NOM_FICHERO)))) {
			System.out.println(in.readByte());
			System.out.println(in.readShort());
			System.out.println(in.readInt());
			System.out.println(in.readLong());
			System.out.println(in.readFloat());
			System.out.println(in.readDouble());
			System.out.println(in.readBoolean());
			System.out.println(in.readChar());
		} catch (IOException e) {
			System.out.println("Error");
		}
	}

}
