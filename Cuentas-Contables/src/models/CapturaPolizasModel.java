package models;

public class CapturaPolizasModel {

	private PolizasDAL dal;
	
	public CapturaPolizasModel() {
		dal = PolizasDAL.getInstance();
	}
	
	public void grabarAsiento(Asiento asiento) {
		 dal.escribirAsiento(asiento); 
	}
	
	public void afectar() {
		dal.afectar();
	}
	
	public boolean cuentaExist(String cuenta) {
		return dal.cuentaExist(cuenta);
	}
	
	public boolean polizaExist(String poliza) {
		return dal.polizaExist(poliza);
	}
	
}
