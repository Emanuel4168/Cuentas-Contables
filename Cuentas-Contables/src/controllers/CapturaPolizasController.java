package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Asiento;
import models.CapturaPolizasModel;
import views.CapturaPolizas;

public class CapturaPolizasController implements ActionListener {
	private CapturaPolizasModel model;
	private CapturaPolizas view;
	
	public CapturaPolizasController(CapturaPolizasModel model, CapturaPolizas view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view.getBtnNuevaCuenta()) {
			String poliza = view.getTxtPoliza().getText(),
					subSubC = view.getTxtSubSubCuenta().getText(),
					importe = view.getTxtImporte().getText();
			
			if(poliza.length() < 4 || subSubC.length() < 6 || importe.length() < 1) {
				view.showError();
				return;
			}
			
			view.addPoliza();
			return;
		}
		if(e.getSource() == view.getBtnGrabar()) {
			if(!view.isBalanced()) {
				view.showBalanceError();
				return;
			}
			
			Asiento[] poliza = view.polizaAsArray();
			for(int i = 0;i < poliza.length; i++) {
				if(!model.grabarAsiento(poliza[i])) {
					view.showNotFoundError(poliza[i].getSubSubCuenta());
					return;
				}
			}
			
			view.restart();
			
			return;
		}
		if(e.getSource() == view.getBtnAfectar()) {
			
		}
		
	}
	
	
}
