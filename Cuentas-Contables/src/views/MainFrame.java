package views;

import javax.swing.*;

import controllers.*;
import models.*;

public class MainFrame extends JFrame{
	
	private JTabbedPane mainMenu;
	private JPanel altaCuentas,modCuenta,bajaCuenta,consultaCuentas,capturaPolizas;
	private AltaCuentasModel altaCM;
	private AltaCuentasController altaCC;
	private ModificacionCuentasModel modificacionCM;
	private ModificacionCuentasController modificacionCC;
	private ConsultaCuentasModel consultaCM;
	private ConsultaCuentasController consultaCC;
	private BajaCuentaModel bajaCM;
	private BajaCuentaController bajaCC;
	private CapturaPolizasModel capturaPM;
	private CapturaPolizasController capturaPC;
	
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
		modificacionCM = new ModificacionCuentasModel();
		consultaCM = new ConsultaCuentasModel();
		bajaCM = new BajaCuentaModel();
		capturaPM = new CapturaPolizasModel();
	}
	
	private void addConrollers() {
		altaCC = new AltaCuentasController(altaCM,(AltaCuentas) altaCuentas);
		((AltaCuentas)altaCuentas).setController(altaCC);
		
		modificacionCC = new ModificacionCuentasController(modificacionCM, (ModificacionCuenta) modCuenta);
		((ModificacionCuenta) modCuenta).setController(modificacionCC);
		
		consultaCC = new ConsultaCuentasController(consultaCM,(ConsultaCuentas) consultaCuentas);
		((ConsultaCuentas)consultaCuentas).setController(consultaCC);
		
		bajaCC = new BajaCuentaController(bajaCM,(BajaCuenta)bajaCuenta);
		((BajaCuenta)bajaCuenta).setController(bajaCC);
		
		capturaPC = new CapturaPolizasController(capturaPM, (CapturaPolizas)capturaPolizas); 
		((CapturaPolizas)capturaPolizas).setController(capturaPC);
	}

}
