package views;

import java.awt.*;
import javax.swing.*;
import components.*;

public class ModificacionCuenta extends JPanel{
	private JNumericFormField txtCuenta;
	private JButton btnGuardar,btnLimpiar;
	
	public ModificacionCuenta() {
		super(new BorderLayout());
		createNortPane();
		createCenterPane();
		createSouthPane();
	}
	
	private void createNortPane() {
		JPanel south = new JPanel(new GridLayout(0,2));
		JLabel lblTitle = new JLabel("Modificacion cuentas");
		lblTitle.setFont(new Font("Tahoma", 0, 24));
		south.add(lblTitle);
		add(lblTitle);
	}
	
	private void createCenterPane(){
		JPanel center = new JPanel();
		txtCuenta = new JNumericFormField(6,null);
		txtCuenta.setPreferredSize(new Dimension(100,50));
		
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
