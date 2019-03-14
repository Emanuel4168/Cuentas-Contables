package views;

import java.awt.*;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.table.*;

import components.JNumericFormField;
import controllers.ConsultaCuentasController;

public class ConsultaCuentas extends JPanel{
	private JPanel center;
	private JButton btnConsultar;
	private JScrollPane scroll;
	private JTable tablaConsultas;
	private final String[] columnNames = {"Cuenta","Nombre","Saldo","Cargo","Abono","Status"};
	private String[][] cuentas;
	private ConsultaCuentasController controller;
	
	public ConsultaCuentas() {
		super(new BorderLayout());
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
		cuentas = controller.getCuentas();
		tablaConsultas = new JTable(cuentas,columnNames);
		scroll = new JScrollPane(tablaConsultas);
		center.add(scroll);
		add(center,BorderLayout.CENTER);
	}
	
	public void updateTable() {
		cuentas = controller.getCuentas();
		tablaConsultas = new JTable(cuentas,columnNames);
		SwingUtilities.updateComponentTreeUI(tablaConsultas);
		tablaConsultas.update(tablaConsultas.getGraphics());
	}
	
	public void setCuentas(String[][] cuentas) {
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
