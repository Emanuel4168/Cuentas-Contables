package views;

import java.awt.*;
import javax.swing.*;

import components.JNumericFormField;

public class ConsultaCuentas extends JPanel{
	private JScrollPane scroll;
	private JTable tablaConsultas;
	private final String[] columnNames = {"Cuenta","Nombre","Saldo","Cargo","Abono","Status"};
	private String[][] cuentas;
	
	public ConsultaCuentas() {
		super(new BorderLayout());
		createNortPane();
		createCenterPane();
	}
	
	private void createNortPane() {
		JPanel north = new JPanel(new GridLayout(0,2));
		JLabel lblTitle = new JLabel("Consulta de cuentas");
		lblTitle.setFont(new Font("Tahoma", 0, 24));
		north.add(lblTitle);
		add(north,BorderLayout.NORTH);
	}
	
	private void createCenterPane(){
		JPanel center = new JPanel();
		if(cuentas == null) {
			JLabel lblNotice = new JLabel("Aún no hay cuentas");
			lblNotice.setFont(new Font("Tahoma", 0, 20));
			center.add(lblNotice);
			add(center,BorderLayout.CENTER);
			return;
		}
		scroll = new JScrollPane();
		tablaConsultas = new JTable(cuentas,columnNames);
		scroll.add(tablaConsultas);
		center.add(scroll);
		add(center,BorderLayout.CENTER);
	}
	
	
	public void setCuentas(String[][] cuentas) {
		this.cuentas = cuentas;
	}
	
}
