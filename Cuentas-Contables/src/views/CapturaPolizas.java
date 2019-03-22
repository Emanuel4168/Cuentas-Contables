package views;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import components.*;
import controllers.CapturaPolizasController;
import models.*;

public class CapturaPolizas extends JPanel{
	
	private JPanel north,center,south;
	private JTable polizaConsulta;
	private JIntegerFormField txtPoliza,txtSubSubCuenta;
	private JDecimalFormField txtImporte;
	private JRadioButton rbtCargo,rbtAbono;
	private ButtonGroup group;
	private JButton btnNuevaCuenta,btnGrabar,btnAfectar,btnModificarAsiento;
	private Vector<Vector<String>> poliza;
	private final String[] COL_NAMES_ARRAY = {"N° poliza","sub-Sub Cuenta","Tipo","Importe"};
	private final Vector<String> COLUMN_NAMES = new Vector<String>(Arrays.asList(COL_NAMES_ARRAY));
	private Vector<JButton> editButtons;
	private CapturaPolizasController controller;
	
	public CapturaPolizas() {
		super(new BorderLayout());
		poliza = new Vector<Vector<String>>();
		editButtons = new Vector<JButton>();
		createNortPane();
		createCenterPane();
		createSouthPane();
	}
	
	private void createNortPane() {
		JPanel north = new JPanel(new GridLayout(0,2));
		JLabel lblTitle = new JLabel("Captura de polizas/afectacion");
		lblTitle.setFont(new Font("Tahoma", 0, 24));
		north.add(lblTitle);
		add(north,BorderLayout.NORTH);
	}
	
	private void createCenterPane() {
		JPanel center = new JPanel(new GridLayout(0,2,5,5));
		txtPoliza = new JIntegerFormField(4);
		txtSubSubCuenta = new JIntegerFormField(6);
		txtImporte = new JDecimalFormField(8);
		txtPoliza.setNextComponent(txtSubSubCuenta);
		txtSubSubCuenta.setNextComponent(txtImporte);		
		
		JLabel poliza = new JLabel("Poliza:");
		JLabel subSubCuenta = new JLabel("SubSub Cuenta:");
		JLabel importe = new JLabel("Importe:");
		poliza.setHorizontalAlignment(SwingConstants.RIGHT);
		subSubCuenta.setHorizontalAlignment(SwingConstants.RIGHT);
		importe.setHorizontalAlignment(SwingConstants.RIGHT);
		
		group = new ButtonGroup();
		rbtCargo = new JRadioButton("Cargo");
		rbtAbono = new JRadioButton("Abono");
		rbtCargo.setSelected(true);
		group.add(rbtCargo);
		group.add(rbtAbono);
		
		btnNuevaCuenta = new JButton("Nuevo Asiento");
		txtImporte.setNextComponent(btnNuevaCuenta);
		btnGrabar = new JButton("Grabar en disco");
		btnAfectar = new JButton("Afectar");
		btnModificarAsiento = new JButton("Modificar Asiento");
		
		center.add(poliza);
		center.add(txtPoliza);
		center.add(subSubCuenta);
		center.add(txtSubSubCuenta);
		center.add(importe);
		center.add(txtImporte);
		center.add(rbtCargo);
		center.add(rbtAbono);
		center.add(btnNuevaCuenta);
		center.add(btnGrabar);
		center.add(btnAfectar);
		center.add(btnModificarAsiento);
		btnModificarAsiento.setEnabled(false);
		
		add(center,BorderLayout.CENTER);
	}
	
	private void createSouthPane() {
		south = new JPanel();
		
		editButtons.add(new JButton(" "));
		editButtons.get(0).setEnabled(false);
		
		JScrollPane scroll = new JScrollPane();

		if(poliza.size() < 1) 
			initializePoliza();
		polizaConsulta = new JTable(poliza,COLUMN_NAMES);
		polizaConsulta.setPreferredSize(new Dimension(400,200));
		south.add(polizaConsulta);
		
		scroll.add(south);

		add(south,BorderLayout.SOUTH);
	}
	
	public void addPoliza() {
		Vector<String> row = new Vector<String>();
		row.add(txtPoliza.getText());
		row.add(txtSubSubCuenta.getText());
		row.add(rbtCargo.isSelected()? "C":"A");
		row.add(txtImporte.getText());
		poliza.addElement(row);
//		DefaultTableModel tableModel = (DefaultTableModel)polizaConsulta.getModel();
//		tableModel.addRow(row);
		editButtons.add(new JButton());
		SwingUtilities.updateComponentTreeUI(polizaConsulta);
	}
	
	public void limpiar() {
		txtPoliza.setText("");
		txtSubSubCuenta.setText("");
		txtImporte.setText("");
		
		txtPoliza.requestFocus();
	}
	
	public void showError() {
		JOptionPane.showMessageDialog(this, "Error al llenar el formulario");
	}
	
	public void showBalanceError() {
		JOptionPane.showMessageDialog(this, "La poliza no está balanceada");
	}
	
	public void showNotFoundError(String cuenta) {
		JOptionPane.showMessageDialog(this, "La sub sub cuenta "+ cuenta+" no ha sido registrada");
	}
	
	public void showSuccesWrite() {
		JOptionPane.showMessageDialog(this, "Poliza Grabada con exito");
	}
	
	public void showSuccesaffectation() {
		JOptionPane.showMessageDialog(this, "Afectacion exitosa");
	}
	
	public Asiento[] polizaAsArray() {
		Asiento[] polizaArray = new Asiento[poliza.size()-1];
		Vector<String> currentRegistry;
		for(int i = 1; i < poliza.size(); i++) {
			currentRegistry = poliza.get(i);
			polizaArray[i-1] = new Asiento(currentRegistry.get(0)
											,currentRegistry.get(1)
											,currentRegistry.get(2).charAt(0)
											,'A'
											,Float.parseFloat(currentRegistry.get(3)));
		}
		return polizaArray;
	}
	
	private void initializePoliza() {
		Vector<String> row = new Vector<String>();
		row.add("N° Poliza");
		row.add("Sub Sub Cuenta");
		row.add("Tipo");
		row.add("Importe");
		poliza.addElement(row);
	}
	
	private void addListeners() {
		btnNuevaCuenta.addActionListener(controller);
		btnGrabar.addActionListener(controller);
		btnAfectar.addActionListener(controller);
		btnModificarAsiento.addActionListener(controller);
		
		polizaConsulta.addMouseListener(controller);
	}
	
	public boolean isBalanced() {
		float cargo = 0, abono = 0, importCR;
		Vector<String> currentRegistry;
		for(int i = 1; i < poliza.size(); i++) {
			currentRegistry = poliza.get(i);
			importCR = Float.parseFloat(currentRegistry.get(3));
			if(currentRegistry.get(2).equals("C")) {
				cargo += importCR;
				continue;
			}
			abono += importCR;
		}
		return cargo == abono;
	}
	
	public void restart() {
		poliza.removeAllElements();
		initializePoliza();
		SwingUtilities.updateComponentTreeUI(polizaConsulta);
	}

	public JIntegerFormField getTxtPoliza() {
		return txtPoliza;
	}

	public JIntegerFormField getTxtSubSubCuenta() {
		return txtSubSubCuenta;
	}

	public JDecimalFormField getTxtImporte() {
		return txtImporte;
	}

	public JButton getBtnNuevaCuenta() {
		return btnNuevaCuenta;
	}

	public JButton getBtnGrabar() {
		return btnGrabar;
	}

	public JButton getBtnAfectar() {
		return btnAfectar;
	}
	
	public JButton getBtnModificarAsiento() {
		return this.btnModificarAsiento;
	}

	public void setController(CapturaPolizasController controller) {
		this.controller = controller;
		addListeners();
	}

	public JTable getPolizaConsulta() {
		return polizaConsulta;
	}

}
