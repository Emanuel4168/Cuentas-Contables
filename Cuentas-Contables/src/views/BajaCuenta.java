package views;

import java.awt.*;
import javax.swing.*;

import components.JNumericFormField;

public class BajaCuenta  extends JPanel{
	private JNumericFormField txtCuenta;
	private JButton btnGuardar,btnLimpiar;
	
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
		txtCuenta = new JNumericFormField(6,null);
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
}
