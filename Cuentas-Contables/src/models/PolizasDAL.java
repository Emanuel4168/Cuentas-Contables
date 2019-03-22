package models;

import java.io.*;

public class PolizasDAL {
	private RandomAccessFile polizas;
	private AltaCuentasDAL cuentasDAL;
	private static PolizasDAL instance;
	private final int REGISTRY_LENGTH = 22;
	
	private PolizasDAL() {
		try {
			cuentasDAL = AltaCuentasDAL.getInstance();
			polizas = new RandomAccessFile("polizas.dat","rw");
		} catch (FileNotFoundException e) {}
	}
	
	public static PolizasDAL getInstance() {
		if(instance == null)
			instance = new PolizasDAL();
		
		return instance;
	}
	
	public boolean escribirAsiento(Asiento asiento) {
//		System.out.println(asiento);
		if(cuentasDAL.busquedaBinaria(asiento.getSubSubCuenta()) == -1 || cuentasDAL.getCuenta(asiento.getSubSubCuenta()).getStatus() == 'B')
			return false;
		
		try {
			polizas.seek(polizas.length());
			polizas.writeUTF(asiento.getPoliza()); 		//6 bytes
			polizas.writeUTF(asiento.getSubSubCuenta());//8 bytes
			polizas.writeChar(asiento.getTipo());       //2 bytes   22 bytes
			polizas.writeChar(asiento.getStatus());     //2 bytes
			polizas.writeFloat(asiento.getImporte());   //4 bytes
			return true;
		}catch (IOException e) {return false;}
	}
	
	public void afectar() {
		int totalRegistros;
		char status,tipo;
		String subSub;
		float importe;
		try {
			totalRegistros = (int) (polizas.length()/REGISTRY_LENGTH);
		
			for(int i = 0; i < totalRegistros; i++) {
				polizas.seek((i*REGISTRY_LENGTH)+6);
				subSub = polizas.readUTF();
				tipo = polizas.readChar();
				status = polizas.readChar();
				importe = polizas.readFloat();
				if(status == 'B')
					continue;
				
				polizas.seek(polizas.getFilePointer() - 6);
				polizas.writeChar('B');
				cuentasDAL.afectar(subSub, tipo, importe);
			}
		} catch (IOException e) {e.printStackTrace();}
	}
}
