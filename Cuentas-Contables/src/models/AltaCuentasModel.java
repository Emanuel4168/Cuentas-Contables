package models;

public class AltaCuentasModel {
	
	AltaCuentasDAL dal;
	
	public AltaCuentasModel() {
		dal = new AltaCuentasDAL();
	}
	
	public boolean guardarCenta(Cuenta cuenta) {
		String count= cuenta.getCuenta(),ct,sub,subSub;
		boolean aux = true;
		ct = count.substring(0,2);
		sub = count.substring(2,4);
		subSub = count.substring(4,6);
		
		if(!subSub.equals("00")) 
			return validarSubSubCuenta(count, cuenta);
		if(!sub.equals("00"))
			return validarSubCuenta(count,cuenta);
		
		if(dal.busquedaBinaria(count) != -1)
			return false;
				
		System.out.println("3. "+count+" Busqueda"+dal.busquedaBinaria(count));
		dal.escribirRegistro(cuenta);
		return true;
	}

	private boolean validarSubSubCuenta(String subSub, Cuenta cuentaOBJ) {
		String subCuenta = subSub.substring(0,4)+"00";
		System.out.println("1. "+subCuenta+" Busqueda"+dal.busquedaBinaria(subCuenta));
		if(dal.busquedaBinaria(subCuenta) != -1 && dal.busquedaBinaria(subSub) == -1) {
			dal.escribirRegistro(cuentaOBJ);
			return true;
		}
		return false;
	}
	
	private boolean validarSubCuenta(String sub, Cuenta cuentaOBJ) {
		String cuenta = sub.substring(0,2)+"0000";
		System.out.println("2. "+cuenta+" Busqueda"+dal.busquedaBinaria(cuenta));
		if(dal.busquedaBinaria(cuenta) != -1 && dal.busquedaBinaria(sub) == -1) {
			dal.escribirRegistro(cuentaOBJ);
			return true;
		}
		return false;
	}
	
}
