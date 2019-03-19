package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.BajaCuentaModel;
import views.BajaCuenta;

public class BajaCuentaController implements ActionListener{

	private BajaCuenta view;
	private BajaCuentaModel model;
	
	public BajaCuentaController(BajaCuentaModel model,BajaCuenta view) {
		this.view = view;
		this.model = model;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view.getBtnGuardar()) {
			String cuentaBaja = view.getTxtCuenta().getText();
			if(cuentaBaja.length() < 6) {
				view.showError();
				return;
			}
			if(model.bajaCuenta(cuentaBaja)) {
				view.showSucces();
				return;
			}
			view.showError();
			view.limpiar();
			return;
		}
		if(e.getSource() == view.getBtnLimpiar()) {
			view.limpiar();
		}
	}

}
