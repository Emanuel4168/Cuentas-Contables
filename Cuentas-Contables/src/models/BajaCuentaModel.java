package models;

public class BajaCuentaModel {

	private AltaCuentasDAL dal;
	
	public BajaCuentaModel() {
		dal = AltaCuentasDAL.getInstance();
	}
	
	public boolean bajaCuenta(String cuentaBaja) {
		return dal.bajaCuenta(cuentaBaja);
	}
}
