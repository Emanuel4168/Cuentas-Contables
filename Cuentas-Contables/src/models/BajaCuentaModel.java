package models;

public class BajaCuentaModel {

	private AltaCuentasDAL dal;
	
	public BajaCuentaModel() {
		dal = new AltaCuentasDAL();
	}
	
	public boolean bajaCuenta(String cuentaBaja) {
		return dal.bajaCuenta(cuentaBaja);
	}
}
