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
			
			Asiento[] polizaAr = view.polizaAsArray();
			System.out.println("POLIZAS: "+polizaAr.length);
			for(int i = 0;i < polizaAr.length; i++) {
				if(!model.grabarAsiento(polizaAr[i])) {
					view.showNotFoundError(polizaAr[i].getSubSubCuenta());
					return;
				}
			}
			
			view.restart();
			
			return;
		}
		if(e.getSource() == view.getBtnAfectar()) {
			model.afectar();
			
			return;
		}
		if(e.getSource() == view.getBtnModificarAsiento()) {
			view.getBtnModificarAsiento().setEnabled(false);
			DefaultTableModel model = (DefaultTableModel) view.getPolizaConsulta().getModel();
			int selectedRow = view.getPolizaConsulta().getSelectedRow();
			
			model.setValueAt(poliza, selectedRow, 0);
			model.setValueAt(subSubC, selectedRow, 1);
			model.setValueAt(importe, selectedRow, 3);
			return;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == view.getPolizaConsulta()) {
			System.out.println("YES");
			DefaultTableModel model = (DefaultTableModel) view.getPolizaConsulta().getModel();
			selectedRow = view.getPolizaConsulta().getSelectedRow();
			
			view.getTxtPoliza().setText(model.getValueAt(selectedRow, 0).toString());
			view.getTxtSubSubCuenta().setText(model.getValueAt(selectedRow, 1).toString());
			view.getTxtImporte().setText(model.getValueAt(selectedRow, 3).toString());
			
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
