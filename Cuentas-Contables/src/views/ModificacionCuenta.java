package views;

import java.awt.*;
import javax.swing.*;
import components.*;
import controllers.ModificacionCuentasController;

public class ModificacionCuenta extends JPanel{
	private JNumericFormField txtCuenta;
	private JTextFormField txtNombre;
	private JButton btnGuardar,btnLimpiar;
	private ModificacionCuentasController controller;
	
	public ModificacionCuenta() {
		super(new BorderLayout());
		createNortPane();
		createCenterPane();
		createSouthPane();
	}
	
	private void createNortPane() {
		JPanel north = new JPanel(new GridLayout(0,2));
		JLabel lblTitle = new JLabel("Modificacion Cuentas");
		lblTitle.setFont(new Font("Tahoma", 0, 24));
		north.add(lblTitle);
		add(north,BorderLayout.NORTH);
	}
	
	private void createCenterPane(){
		JPanel center = new JPanel(new GridLayout(0,1)),
				field1 = new JPanel(), field2 = new JPanel();
		txtCuenta = new JNumericFormField(6);
		txtNombre = new JTextFormField(30);
		
		txtCuenta.setNextComponent(txtNombre);
		
		txtCuenta.setPreferredSize(new Dimension(150,30));
		txtNombre.setPreferredSize(new Dimension(150,30));
		
		JLabel cuenta = new JLabel("Cuenta:");
		JLabel nombre = new JLabel("Nuevo Nombre:");
		cuenta.setHorizontalAlignment(SwingConstants.RIGHT);
		nombre.setHorizontalAlignment(SwingConstants.RIGHT);
		
		field1.add(cuenta);
		field1.add(txtCuenta);
		field2.add(nombre);
		field2.add(txtNombre);
		center.add(field1);
		center.add(field2);
		center.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(center,BorderLayout.CENTER);
	}
	
	private void createSouthPane(){
		JPanel south = new JPanel();
		btnGuardar = new JButton("Aceptar");
		btnLimpiar = new JButton("Limpiar");
		txtNombre.setNextComponent(btnGuardar);
		south.add(btnGuardar);
		south.add(btnLimpiar);
		add(south,BorderLayout.SOUTH);
	}
	
	public void showError() {
		JOptionPane.showMessageDialog(this, "La cuenta no ha sido Encontrada");
	}
	
	public void showSucces() {
		JOptionPane.showMessageDialog(this, "La cuenta ha sido Modificada");
	}
	
	public void limpiar() {
		txtCuenta.setText("");
		txtNombre.setText("");
	}
	
	private void addListeners() {
		btnGuardar.addActionListener(controller);
		btnLimpiar.addActionListener(controller);
	}
	
	public void setController(ModificacionCuentasController cont) {
		controller = cont;
		addListeners();
	}

	public JNumericFormField getTxtCuenta() {
		return txtCuenta;
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
}
