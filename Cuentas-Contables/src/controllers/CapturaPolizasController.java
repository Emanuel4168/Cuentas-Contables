package controllers;

import java.awt.event.*;

import javax.swing.table.DefaultTableModel;

import models.Asiento;
import models.CapturaPolizasModel;
import views.CapturaPolizas;

public class CapturaPolizasController implements ActionListener, MouseListener {
	private CapturaPolizasModel model;
	private CapturaPolizas view;
	private int selectedRow;
	
	public CapturaPolizasController(CapturaPolizasModel model, CapturaPolizas view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String poliza = view.getTxtPoliza().getText(),
				subSubC = view.getTxtSubSubCuenta().getText(),
				importe = view.getTxtImporte().getText();
		
		if(e.getSource() == view.getBtnNuevaCuenta()) {
			if(view.getTxtPoliza().isEnabled() && model.polizaExist(poliza)) {
				view.showPolizaIsRegisteredError();
				view.limpiar();
				return;
			}
			
			view.getTxtPoliza().setEnabled(false);

			if(poliza.length() < 4 || subSubC.length() < 6 || importe.length() < 1) {
				view.showError();
				view.limpiar();
				return;
			}
			
			if(!model.cuentaExist(subSubC)) {
				view.showCuentaNotFoundError(subSubC);
				view.limpiar();
				return;
			}
			
			view.addPoliza();
			view.limpiar();
			return;
		}
		if(e.getSource() == view.getBtnGrabar()) {
			if(!view.isBalanced()) {
				view.showBalanceError();
				view.limpiar();
				return;
			}
			
			Asiento[] polizaAr = view.polizaAsArray();
			System.out.println("POLIZAS: "+polizaAr.length);
			for(int i = 0;i < polizaAr.length; i++) 
				model.grabarAsiento(polizaAr[i]);
			
			view.showSuccesWrite();
			view.limpiar();
			view.restart();
			
			return;
		}
		if(e.getSource() == view.getBtnAfectar()) {
			model.afectar();
			view.showSuccesaffectation();
			view.limpiar();
			view.restart();
			return;
		}
		if(e.getSource() == view.getBtnModificarAsiento()) {
			view.getBtnModificarAsiento().setEnabled(false);
			view.rewrite();
			view.limpiar();
			return;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == view.getPolizaConsulta()) {
			view.modify();
			
			view.getBtnModificarAsiento().setEnabled(true);
			return;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
