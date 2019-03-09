package views;

import javax.swing.*;

import controllers.*;
import models.*;

public class MainFrame extends JFrame{
	
	private JTabbedPane mainMenu;
	private JPanel altaCuentas,modCuenta,bajaCuenta,consultaCuentas,capturaPolizas;
	private AltaCuentasModel altaCM;
	private AltaCuentasController altaCC;
	
	public MainFrame() {
		super("Cuentas Contables");
		mainMenu = new JTabbedPane();
		setSize(600,500);
		setLocationRelativeTo(null);
		setResizable(false);
		add(mainMenu);
		altaCuentas = new AltaCuentas();
		modCuenta = new ModificacionCuenta ();
		bajaCuenta = new BajaCuenta();
		consultaCuentas = new ConsultaCuentas();
		capturaPolizas = new CapturaPolizas();
		
		mainMenu.addTab("Alta", altaCuentas);
		mainMenu.addTab("Modificación", modCuenta);
		mainMenu.addTab("Baja", bajaCuenta);
		mainMenu.addTab("Consulta", consultaCuentas);
		mainMenu.addTab("Crear Polizas", capturaPolizas);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createModels();
		addConrollers();
		setVisible(true);
	}
	
	private void createModels() {
		altaCM= new AltaCuentasModel();
	}
	
	private void addConrollers() {
		altaCC = new AltaCuentasController(altaCM,(AltaCuentas) altaCuentas);
		((AltaCuentas)altaCuentas).setController(altaCC);
	}

}
