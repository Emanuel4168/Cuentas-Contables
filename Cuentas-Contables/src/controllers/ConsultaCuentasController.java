package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.ConsultaCuentasModel;
import views.ConsultaCuentas;

public class ConsultaCuentasController  implements ActionListener{
	private ConsultaCuentasModel model;
	private ConsultaCuentas view; 
	
	public ConsultaCuentasController(ConsultaCuentasModel model, ConsultaCuentas view) {
		super();
		this.model = model;
		this.view = view;
	}
	
	public String[][] getCuentas() {
		return model.cuentasAsMatrix();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.updateTable();
	}
}
