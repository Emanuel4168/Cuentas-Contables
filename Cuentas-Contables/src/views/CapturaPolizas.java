package views;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import components.*;
import models.*;

public class CapturaPolizas extends JPanel{
	
	private JPanel north,center,south;
	private JScrollPane southScroll;
	private JNumericFormField txtPoliza,txtSubSubCuenta,txtImporte;
	private JRadioButton rbtCargo,rbtAbono;
	private ButtonGroup group;
	private JButton btnNuevaCuenta,btnGrabar,btnAfectar;
	private Vector<Poliza> polizas; 
	
	public CapturaPolizas() {
		super(new BorderLayout());
		polizas = new Vector<Poliza>();
		createNortPane();
		createCenterPane();
	}
	
	private void createNortPane() {
		JPanel north = new JPanel(new GridLayout(0,2));
		JLabel lblTitle = new JLabel("Captura de polizas y afectacion");
		lblTitle.setFont(new Font("Tahoma", 0, 24));
		north.add(lblTitle);
		add(north,BorderLayout.NORTH);
	}
	
	private void createCenterPane() {
		JPanel center = new JPanel(new GridLayout(0,2,5,5));
		txtPoliza = new JNumericFormField(4);
		txtSubSubCuenta = new JNumericFormField(6);
		txtImporte = new JNumericFormField(8);
		txtPoliza.setNextField(txtSubSubCuenta);
		txtSubSubCuenta.setNextField(txtPoliza);
		
		JLabel poliza = new JLabel("Poliza:");
		JLabel subSubCuenta = new JLabel("SubSub Cuenta:");
		JLabel importe = new JLabel("Importe:");
		poliza.setHorizontalAlignment(SwingConstants.RIGHT);
		subSubCuenta.setHorizontalAlignment(SwingConstants.RIGHT);
		importe.setHorizontalAlignment(SwingConstants.RIGHT);
		
		group = new ButtonGroup();
		rbtCargo = new JRadioButton("Cargo");
		rbtAbono = new JRadioButton("Abono");
		group.add(rbtCargo);
		group.add(rbtAbono);
		
		btnNuevaCuenta = new JButton("Nuevo Asiento");
		btnGrabar = new JButton("Grabar en disco");
		btnAfectar = new JButton("Afectar");
		
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
		
		add(center,BorderLayout.CENTER);
	}
	
	public void addToSouth(Poliza poliza) {
		south = new JPanel(new GridLayout(0,2,5,5));
		southScroll = new JScrollPane();
		
		
	}

}
