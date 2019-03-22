package views;

import java.awt.*;
import javax.swing.*;

import components.*;
import controllers.BajaCuentaController;

public class BajaCuenta  extends JPanel{
	private JIntegerFormField txtCuenta;
	private JButton btnGuardar,btnLimpiar;
	private BajaCuentaController controller;
	
	public BajaCuenta() {
		super(new BorderLayout());
		createNortPane();
		createCenterPane();
		createSouthPane();
	}
	
	private void createNortPane() {
		JPanel north = new JPanel(new GridLayout(0,2));
		JLabel lblTitle = new JLabel("Baja Cuentas");
		lblTitle.setFont(new Font("Tahoma", 0, 24));
		north.add(lblTitle);
		add(north,BorderLayout.NORTH);
	}
	
	private void createCenterPane(){
		JPanel center = new JPanel();
		txtCuenta = new JIntegerFormField(6,null);
		txtCuenta.setPreferredSize(new Dimension(150,30));
		
		JLabel cuenta = new JLabel("Cuenta:");
		cuenta.setHorizontalAlignment(SwingConstants.RIGHT);
		center.add(cuenta);
		center.add(txtCuenta);
		center.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(center,BorderLayout.CENTER);
	}
	
	private void createSouthPane(){
		JPanel south = new JPanel();
		btnGuardar = new JButton("Aceptar");
		btnLimpiar = new JButton("Limpiar");
		south.add(btnGuardar);
		south.add(btnLimpiar);
		add(south,BorderLayout.SOUTH);
	}
	
	public void showError() {
		JOptionPane.showMessageDialog(this, "La cuenta no ha sido Encontrada");
	}
	
	public void showSucces() {
		JOptionPane.showMessageDialog(this, "La cuenta se ha dado de baja");
	}
	
	public void limpiar() {
		txtCuenta.setText("");
		txtCuenta.requestFocus();
	}
	
	private void addListeners() {
		btnGuardar.addActionListener(controller);
		btnLimpiar.addActionListener(controller);;
	}
	
	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public JIntegerFormField getTxtCuenta() {
		return txtCuenta;
	}

	public void setController(BajaCuentaController controller) {
		this.controller = controller;
		addListeners();
	}
}
