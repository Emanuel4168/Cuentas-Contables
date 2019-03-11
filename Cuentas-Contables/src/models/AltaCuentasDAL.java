package models;

import java.io.*;
import java.util.ArrayList;

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
	
	public String[][] cuentasAsMatrix(){
		String[][] cuentasMatrix= null; 
		long registros;
		try {
			registros = cuentas.length()/TOTAL_REGISTRATIONS;
			cuentasMatrix = new String[(int) registros][6];
			for(int i = 0; i < registros; i++) {
				cuentas.seek(i * TOTAL_REGISTRATIONS);
				cuentasMatrix[i][0] = cuentas.readUTF();
				cuentasMatrix[i][1] = cuentas.readUTF();
				cuentasMatrix[i][2] = cuentas.readFloat()+"";
				cuentasMatrix[i][3] = cuentas.readFloat()+"";
				cuentasMatrix[i][4] = cuentas.readFloat()+"";
				cuentasMatrix[i][5] = cuentas.readChar()+"";
			}
			return cuentasMatrix;
		} catch (IOException e) {}
		return cuentasMatrix;
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
