package models;

public class CapturaPolizasModel {

	private PolizasDAL dal;
	private AltaCuentasDAL cuentasDAL;
	
	public CapturaPolizasModel() {
		dal = new PolizasDAL();
		cuentasDAL = new AltaCuentasDAL();
	}
	
	public boolean grabarAsiento(Asiento asiento) {
		if(cuentasDAL.busquedaBinaria(asiento.getSubSubCuenta()) == -1)
			return false;
		
		dal.escribirAsiento(asiento);
		return true;
	}
	
}
