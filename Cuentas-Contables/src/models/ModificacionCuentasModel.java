package models;

public class ModificacionCuentasModel {
	AltaCuentasDAL dal;

	public ModificacionCuentasModel() {
		dal = new AltaCuentasDAL();
	}
	
	public boolean modificarCuenta(String cuenta, String nNombre) {
		return dal.modificarCuenta(cuenta, nNombre);
	}
}
