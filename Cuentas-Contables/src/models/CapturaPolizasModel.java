package models;

public class CapturaPolizasModel {

	private PolizasDAL dal;
	
	public CapturaPolizasModel() {
		dal = new PolizasDAL();
	}
	
	public boolean grabarAsiento(Asiento asiento) {
		return dal.escribirAsiento(asiento); 
	}
	
	public void afectar() {
		dal.afectar();
	}
	
}
