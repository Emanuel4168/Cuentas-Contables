package models;

import java.io.*;

public class PolizasDAL {
	private RandomAccessFile polizas;
	private final int REGISTRY_LENGTH = 22;
	
	public PolizasDAL() {
		try {
			polizas = new RandomAccessFile("polizas.dat","rw");
		} catch (FileNotFoundException e) {}
	}
	
	public void escribirAsiento(Asiento asiento) {
		try {
			polizas.seek(polizas.length());
			polizas.writeUTF(asiento.getPoliza()); 		//6 bytes
			polizas.writeUTF(asiento.getSubSubCuenta());//8 bytes
			polizas.writeChar(asiento.getTipo());       //2 bytes   22 bytes
			polizas.writeChar(asiento.getStatus());     //2 bytes
			polizas.writeFloat(asiento.getImporte());   //4 bytes
		}catch (IOException e) {}
	}
}
