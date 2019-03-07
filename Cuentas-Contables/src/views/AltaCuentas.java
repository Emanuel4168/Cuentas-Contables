package views;

import java.awt.*;
import javax.swing.*;
import components.*;

public class AltaCuentas extends JPanel{
	
	private JNumericFormField txtCuenta,txtSaldo;
	private JTextFormField txtNombre;
	private JButton btnGuardar,btnLimpiar;
	
	public AltaCuentas() {
		super(new BorderLayout());
		createNortPane();
		createCenterPane();
		createSouthPane();
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
		txtCuenta = new JNumericFormField(6);
		txtNombre = new JTextFormField(15);
		txtSaldo = new JNumericFormField(8);
		
		txtCuenta.setNextField(txtNombre);
		txtNombre.setNextField(txtSaldo);
		
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
		south.add(btnGuardar);
		south.add(btnLimpiar);
		add(south,BorderLayout.SOUTH);
	}

}
