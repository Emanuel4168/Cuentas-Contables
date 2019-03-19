package controllers;

import java.awt.event.*;

import models.ModificacionCuentasModel;
import views.ModificacionCuenta;

public class ModificacionCuentasController implements ActionListener{

	private ModificacionCuentasModel model;
	private ModificacionCuenta view;
	
	public ModificacionCuentasController(ModificacionCuentasModel model, ModificacionCuenta view) {
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view.getBtnGuardar()) {
			if(view.getTxtCuenta().getText().length() < 6 || view.getTxtNombre().getText().length() <1) {
				view.showError();
				return;
			}
			
			String cuentaMod = view.getTxtCuenta().getText(), 
					nuevoNombre = view.getTxtNombre().getText();
			
			if(model.modificarCuenta(cuentaMod, nuevoNombre)) {
				view.showSucces();
				return;
			}	
			
			view.showError();
			view.limpiar();
			return;
		}
		if(e.getSource() == view.getBtnLimpiar()) {
			view.limpiar();
			return;
		}
		
	}

}
