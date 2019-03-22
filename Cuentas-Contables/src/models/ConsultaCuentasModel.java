package models;

import java.util.Vector;

public class ConsultaCuentasModel {
	AltaCuentasDAL dal;
	
	public ConsultaCuentasModel() {
		dal = AltaCuentasDAL.getInstance();
	}
	
	public long getTotalCuentas() {
		return dal.getTotalCuentas();
	}
	
	public Vector<Vector<String>> cuentasAsVector(){
		return dal.cuentasAsVector();
	}
	
//	public Vector<Vector<String>> getNewCuentas(int cuentasTabla){
//		long numeroCuentas = dal.getTotalCuentas();
//		Vector<Vector<String>> newCuentas = new Vector<Vector<String>>(), totalCuentas = dal.cuentasAsVector();
//		for(int i = cuentasTabla - 1 ; i < numeroCuentas; i++) {
//			newCuentas.add(totalCuentas.get(i));
//		}
//		return newCuentas;
//	}
//	
}
