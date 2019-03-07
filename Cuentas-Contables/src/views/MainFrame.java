package views;

import javax.swing.*;

public class MainFrame extends JFrame{
	
	private JTabbedPane mainMenu;
	private JPanel altaCuentas,modCuenta,bajaCuenta,consultaCuentas;
	
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
		
		mainMenu.addTab("Alta", altaCuentas);
		mainMenu.addTab("Modificación", modCuenta);
		mainMenu.addTab("Baja", bajaCuenta);
		mainMenu.addTab("Consulta", consultaCuentas);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
