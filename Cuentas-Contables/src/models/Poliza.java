package models;

public class Poliza {
	private int poliza;
	private String subSubCuenta;
	private char tipo;
	private float importe;
	
	public Poliza(int poliza, String subSubCuenta, char tipo, float importe) {
		this.poliza = poliza;
		this.subSubCuenta = subSubCuenta;
		this.tipo = tipo;
		this.importe = importe;
	}

	public int getPoliza() {
		return poliza;
	}

	public void setPoliza(int poliza) {
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
	
	
}
