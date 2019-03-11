package models;

public class ConsultaCuentasModel {
	AltaCuentasDAL dal;
	
	public ConsultaCuentasModel() {
		dal = new AltaCuentasDAL();
	}
	
	public String[][] cuentasAsMatrix(){
		return dal.cuentasAsMatrix();
	}
	
	public String[] getLastCuentaAsArray() {
		return dal.getLastCuentaAsArray();
	}
	
}
