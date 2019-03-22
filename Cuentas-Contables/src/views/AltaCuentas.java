package views;

import java.awt.*;
import javax.swing.*;
import components.*;
import controllers.AltaCuentasController;

public class AltaCuentas extends JPanel{
	
	private JIntegerFormField txtCuenta;
	private JDecimalFormField txtSaldo;
	private JTextFormField txtNombre;
	private JButton btnGuardar,btnLimpiar;
	private AltaCuentasController controller;
	
	public AltaCuentas() {
		super(new BorderLayout());
		createNortPane();
		createCenterPane();
		createSouthPane();
	}
	
	private void addListeners() {
		btnGuardar.addActionListener(controller);
		btnLimpiar.addActionListener(controller);
	}

	private void createNortPane() {
		JPanel south = new JPanel(new GridLayout(0,2));
		JLabel lblTitle = new JLabel("Alta de Cuentas");
		lblTitle.setFont(new Font("Tahoma", 0, 24));
		south.add(lblTitle);
		add(lblTitle,BorderLayout.NORTH);
	}
	
	private void createCenterPane(){
		JPanel center = new JPanel(new GridLayout(0,2,20,60));
		txtCuenta = new JIntegerFormField(6);
		txtNombre = new JTextFormField(30);
		txtSaldo = new JDecimalFormField(8);
		
		txtCuenta.setNextComponent(txtNombre);
		txtNombre.setNextComponent(txtSaldo);
		
		JLabel cuenta = new JLabel("Cuenta:");
		JLabel name = new JLabel("Nombre:");
		JLabel saldo = new JLabel("Saldo:");
		cuenta.setHorizontalAlignment(SwingConstants.RIGHT);
		name.setHorizontalAlignment(SwingConstants.RIGHT);
		saldo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		center.add(cuenta);
		center.add(txtCuenta);
		center.add(name);
		center.add(txtNombre);
		center.add(saldo);
		center.add(txtSaldo);
		center.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(center,BorderLayout.CENTER);
	}
	
	private void createSouthPane(){
		JPanel south = new JPanel();
		btnGuardar = new JButton("Aceptar");
		btnLimpiar = new JButton("Limpiar");
		txtSaldo.setNextComponent(btnGuardar);
		south.add(btnGuardar);
		south.add(btnLimpiar);
		add(south,BorderLayout.SOUTH);
	}
	
	public void showError() {
		JOptionPane.showMessageDialog(this, "Error al llenar formulario");
	}
	
	public void limpiar() {
		txtCuenta.setText("");
		txtNombre.setText("");
		txtSaldo.setText("");
		
		txtCuenta.requestFocus();
	}

	public JIntegerFormField getTxtCuenta() {
		return txtCuenta;
	}

	public JDecimalFormField getTxtSaldo() {
		return txtSaldo;
	}

	public JTextFormField getTxtNombre() {
		return txtNombre;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setController(AltaCuentasController controller) {
		this.controller = controller;
		addListeners();
	}

}
