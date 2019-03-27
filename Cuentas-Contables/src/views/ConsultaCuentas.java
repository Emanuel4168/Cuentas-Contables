package views;

import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

import components.*;
import controllers.ConsultaCuentasController;

public class ConsultaCuentas extends JPanel{
	private JPanel center;
	private JButton btnConsultar;
	private JScrollPane scroll;
	private JTable tablaConsultas;
	private Vector<String> columnNames; 
	private Vector<Vector<String>>cuentas;
	private ConsultaCuentasController controller;
	
	public ConsultaCuentas() {
		super(new BorderLayout());
		String[] cn = {"Cuenta","Nombre","Saldo","Cargo","Abono","Status"};
		columnNames = new Vector<String>(Arrays.asList(cn));
		createNorthPane();
		createSouthPane();
	}
	
	private void createNorthPane() {
		JPanel north = new JPanel(new GridLayout(0,2));
		JLabel lblTitle = new JLabel("Consulta de cuentas");
		lblTitle.setFont(new Font("Tahoma", 0, 24));
		north.add(lblTitle);
		add(north,BorderLayout.NORTH);
	}
	
	private void createSouthPane(){
		JPanel south = new JPanel();
		btnConsultar = new JButton("Actualizar");
		btnConsultar.setHorizontalAlignment(SwingConstants.CENTER);
		btnConsultar.setPreferredSize(new Dimension(150,35));
		south.add(btnConsultar);
		add(south,BorderLayout.SOUTH);
	}
	
	private void createCenterPane() {
		center = new JPanel();
//		cuentas = controller.getCuentas();
		tablaConsultas = new JTable(cuentas,columnNames);
		scroll = new JScrollPane(tablaConsultas);
		center.add(scroll);
		add(center,BorderLayout.CENTER);
	}
	
	public void updateTable(Vector<Vector<String>> totalCuentas) {
		if(totalCuentas.size() == 0)
			return;
		
		for(int i = 0; i < totalCuentas.size(); i++) {
			if(i < cuentas.size()) {
				System.out.println(totalCuentas.size()+"Actual: "+i);
				cuentas.set(i, totalCuentas.get(i));
				continue;
			}
			cuentas.add(totalCuentas.get(i));
		}
		SwingUtilities.updateComponentTreeUI(tablaConsultas);
	}
	
	public void setCuentas(Vector<Vector<String>> cuentas) {
		this.cuentas = cuentas;
	}

	public JTable getTablaConsultas() {
		return tablaConsultas;
	}

	public void setController(ConsultaCuentasController controller) {
		this.controller = controller;
		this.cuentas = this.controller.getCuentas();
		createCenterPane();
		btnConsultar.addActionListener(controller);
	}
	
}
