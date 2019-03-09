package controllers;

import java.awt.event.*;

import models.*;
import views.*;

public class AltaCuentasController implements ActionListener{

	private AltaCuentasModel model;
	private AltaCuentas view;
	
	public AltaCuentasController(AltaCuentasModel model, AltaCuentas view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == view.getBtnGuardar()) {
			if(view.getTxtCuenta().getText().length() < 6 || view.getTxtNombre().getText().length() < 1 || view.getTxtSaldo().getText().length() < 1) {
				view.showError();
				return;
			}
			String cuenta = view.getTxtCuenta().getText(), nombre = view.getTxtNombre().getText();
			float saldo = Float.parseFloat(view.getTxtSaldo().getText());
			Cuenta cuentaReg = new Cuenta(cuenta,nombre,saldo,0f,0f,'A');
			
			if(model.guardarCenta(cuentaReg))
				view.limpiar();
			else 
				view.showError();
			return;
		}
		if(evt.getSource() == view.getBtnLimpiar()) {
			view.limpiar();
		}
		
	}

}
