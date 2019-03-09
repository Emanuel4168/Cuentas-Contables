package models;

import java.io.*;

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
			int largo = (int) (indices.length()/ 16),inferior =0, mitad, superior = largo;
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
	
	public void quickSort(int limIzq, int limDer) {
		try {
			int i = limIzq, d = limDer, m = (i + d)/2;
			indices.seek((m -1) * TOTAL_REGISTRATIONS);
			String pivote = indices.readUTF();
			indices.seek((i -1) * TOTAL_REGISTRATIONS);
			String izq = indices.readUTF();
			indices.seek((d -1) * TOTAL_REGISTRATIONS);
			String der = indices.readUTF();
			
			
			do {
				while(izq.compareTo(pivote) <0 && i < limDer) {
					i++;
					indices.seek((i -1) * TOTAL_REGISTRATIONS);
					izq = indices.readUTF();
				}

				while(pivote.compareTo(der) <0 && d >limIzq) {
					d--;
					indices.seek((d - 1) * TOTAL_REGISTRATIONS);
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
