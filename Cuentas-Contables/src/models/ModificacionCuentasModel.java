package models;

public class ModificacionCuentasModel {
	AltaCuentasDAL dal;

	public ModificacionCuentasModel() {
		dal = AltaCuentasDAL.getInstance();
	}
	
	public boolean modificarCuenta(String cuenta, String nNombre) {
		return dal.modificarCuenta(cuenta, nNombre);
	}
}
