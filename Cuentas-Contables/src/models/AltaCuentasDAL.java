package models;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

import routines.Rutinas;

public class AltaCuentasDAL {
	RandomAccessFile cuentas;
	RandomAccessFile indices;
	private final int TOTAL_REGISTRATIONS= 44; 
	
	public AltaCuentasDAL() {
		try {
			cuentas = new RandomAccessFile("cuentas.dat","rw");
			indices = new RandomAccessFile("indices.dat","rw");
		} catch (FileNotFoundException e) {}
	}
	
	public void escribirRegistro(Cuenta registro) {
		try{
			cuentas.seek(cuentas.length());
			cuentas.writeUTF(Rutinas.PonBlancos(registro.getCuenta(),6));	//8 bytes
			cuentas.writeUTF(Rutinas.PonBlancos(registro.getNombre(),20));	//22 bytes
			cuentas.writeFloat(registro.getSaldo());						//4 bytes
			cuentas.writeFloat(registro.getCargo());						//4 bytes	44 bytes
			cuentas.writeFloat(registro.getAbono());						//4 bytes
			cuentas.writeChar(registro.getStatus());						//2 bytes	
			
			indices.seek(indices.length());
			indices.writeUTF(Rutinas.PonBlancos(registro.getCuenta(),6));	//8 bytes
			indices.writeLong(cuentas.length()/TOTAL_REGISTRATIONS);		//8 bytes	
			System.out.println(cuentas.length()+"\t"+indices.length());
			quickSort(1,(int) (indices.length()/16));
		} catch (IOException e) {
			return;
		}
		
	}
	
	public int busquedaBinaria(String cuenta) {
		try {
			String cuentaActual;
			int largo = (int) (indices.length()/ 16),inferior =1, mitad, superior = largo;
			while(inferior <= superior) {
				mitad = (inferior + superior)/2;
				System.out.println(largo+" "+inferior+" "+superior+" "+mitad);
				indices.seek((mitad - 1)*16);
				cuentaActual = indices.readUTF();
				if(cuenta.compareTo(cuentaActual) == 0)
					return mitad;
				if(cuentaActual.compareTo(cuenta) > 0)
					superior = mitad - 1;
				else
					inferior = mitad + 1;
			}
			return -1;
		} catch (IOException e) {
			return -1;
		}
		
	}
	
	public Cuenta getCuenta(String cuenta) {
		int pos = this.busquedaBinaria(cuenta);
		Cuenta cuentaOBJ = null;
		if(pos == -1)
			return cuentaOBJ;	
		
		try {
			indices.seek((pos -1)*16);
			indices.readUTF();
			long posCuenta = indices.readLong();
			cuentas.seek((posCuenta - 1)*TOTAL_REGISTRATIONS);
			String cta = cuentas.readUTF(),nombre = cuentas.readUTF();
			float saldo = cuentas.readFloat(),cargo= cuentas.readFloat(),abono = cuentas.readFloat();
			char status = cuentas.readChar();
			cuentaOBJ = new Cuenta(cta,nombre,saldo,cargo,abono,status);
		}catch(IOException e) {
			
		}
		return cuentaOBJ;
	}

	public boolean modificarCuenta(String cuenta, String nNombre) {
		int pos = this.busquedaBinaria(cuenta);
		if(pos == -1)
			return false;
		
		try {
			indices.seek((pos -1)*16);
			indices.readUTF();
			long posCuenta = indices.readLong();
			cuentas.seek((posCuenta - 1)*TOTAL_REGISTRATIONS);
			cuentas.readUTF();
			cuentas.writeUTF(Rutinas.PonBlancos(nNombre,20));
			return true;
		} catch (IOException e) {return false;}
	}
	
	public Vector<Vector<String>> cuentasAsVector(){
		Vector<Vector<String>> cuentasVector = null; 
		Vector<String> currentCuenta;
		long registros;
		try {
			registros = cuentas.length()/TOTAL_REGISTRATIONS;
			cuentasVector = new Vector<Vector<String>>();
			for(int i = 0; i < registros; i++) {
				cuentas.seek(i * TOTAL_REGISTRATIONS);
				currentCuenta = new Vector<String>();
				currentCuenta.add(cuentas.readUTF());
				currentCuenta.add(cuentas.readUTF());
				currentCuenta.add(cuentas.readFloat()+"");
				currentCuenta.add(cuentas.readFloat()+"");
				currentCuenta.add(cuentas.readFloat()+"");
				currentCuenta.add(cuentas.readChar()+"");
				cuentasVector.add(currentCuenta);
			}
			return cuentasVector;
		} catch (IOException e) {}
		return cuentasVector;
	}
	
	public Long getTotalCuentas() {
		try {
			return cuentas.length()/TOTAL_REGISTRATIONS;
		} catch (IOException e) {return 0l;}	
	}
	
	public String[] getLastCuentaAsArray() {
		String [] cuenta = null;
		long registros;
		try {
			registros = cuentas.length()/TOTAL_REGISTRATIONS;
			cuenta = new String[6];
			cuentas.seek((registros-1) * TOTAL_REGISTRATIONS);
			cuenta[0] = cuentas.readUTF();
			cuenta[1] = cuentas.readUTF();
			cuenta[2] = cuentas.readFloat()+"";
			cuenta[3] = cuentas.readFloat()+"";
			cuenta[4] = cuentas.readFloat()+"";
			cuenta[5] = cuentas.readChar()+"";
			return cuenta;
		} catch (IOException e) {}
		return cuenta;
	}
	
	public boolean bajaCuenta(String cuentaBaja) {
		int pos = busquedaBinaria(cuentaBaja);
		if(pos == -1)
			return false;
		
		try {
			cuentas.seek(((pos-1)*TOTAL_REGISTRATIONS)+42);
			cuentas.writeChar('B');
			return true;
		}catch(Exception e) {return false;}
	}
	
	public void afectar(String subSubCuenta, char tipo, float importe) {
		String cuentaPrincipal = subSubCuenta.substring(0,2)+"0000",
				subCuenta= subSubCuenta.substring(0,4)+"00";
		
		System.out.println("Cuentas: "+cuentaPrincipal+" "+subCuenta+" "+subSubCuenta);
		int posSubSub = busquedaBinaria(subSubCuenta), 
				posCuenta = busquedaBinaria(cuentaPrincipal) ,
				posSub = busquedaBinaria(subCuenta);
		float newSaldo = 0;
		
			try {
				if(tipo == 'C') {
					cuentas.seek(((posCuenta - 1)*TOTAL_REGISTRATIONS)+34);
					cuentas.writeFloat(importe);
					cuentas.seek(((posSub - 1)*TOTAL_REGISTRATIONS)+34);
					cuentas.writeFloat(importe);
					cuentas.seek(((posSubSub - 1)*TOTAL_REGISTRATIONS)+34);
					cuentas.writeFloat(importe);
					
					return;
				}
				
				cuentas.seek(((posCuenta - 1)*TOTAL_REGISTRATIONS)+38);
				cuentas.writeFloat(importe);
				cuentas.seek(((posSub - 1)*TOTAL_REGISTRATIONS)+38);
				cuentas.writeFloat(importe);
				cuentas.seek(((posSubSub - 1)*TOTAL_REGISTRATIONS)+38);
				cuentas.writeFloat(importe);

			} catch (IOException e) {}
		
	}
	
	public void quickSort(int limIzq, int limDer) {
		try {
			int i = limIzq, d = limDer, m = (i + d)/2;
			indices.seek((m -1) * 16);
			String pivote = indices.readUTF();
			indices.seek((i -1) * 16);
			String izq = indices.readUTF();
			indices.seek((d -1) * 16);
			String der = indices.readUTF();
			
			
			do {
				while(izq.compareTo(pivote) <0 && i < limDer) {
					i++;
					indices.seek((i -1) * 16);
					izq = indices.readUTF();
				}

				while(pivote.compareTo(der) <0 && d >limIzq) {
					d--;
					indices.seek((d - 1) * 16);
					der = indices.readUTF();
				}
				
				if(i <= d) {
					escribirRegistroIndices(d,izq);
					escribirRegistroIndices(i,der);
					i++; d--;
					indices.seek((i -1) * 16);
					izq = indices.readUTF();
					indices.seek((d - 1) * 16);
					der = indices.readUTF();
				}
			}while (i <= d);
			
			if(limIzq < d) quickSort(limIzq,d);
			if(i < limDer) quickSort(i,limDer);
			
		} catch (IOException e) {
			return;
		}
	}
	
	private void escribirRegistroIndices(int pos, String reg) {
		try {
			indices.seek((pos-1)*16);
			indices.writeUTF(reg);
			indices.writeLong(pos);
		} catch (IOException e) {
			return;
		}
	}
	
}
