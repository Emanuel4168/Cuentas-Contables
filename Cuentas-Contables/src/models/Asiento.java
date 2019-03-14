package models;

public class Asiento {
	private String poliza;
	private String subSubCuenta;
	private char tipo;
	private char status;
	private float importe;
	
	public Asiento(String poliza, String subSubCuenta, char tipo, char status, float importe) {
		this.poliza = poliza;
		this.subSubCuenta = subSubCuenta;
		this.tipo = tipo;
		this.importe = importe;
		this.status = status;
	}

	public String getPoliza() {
		return poliza;
	}

	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}

	public String getSubSubCuenta() {
		return subSubCuenta;
	}

	public void setSubSubCuenta(String subSubCuenta) {
		this.subSubCuenta = subSubCuenta;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	
}
