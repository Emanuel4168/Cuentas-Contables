package views;

import javax.swing.*;

public class MainFrame extends JFrame{
	
	private JTabbedPane mainMenu;
	private JPanel altaCuentas;
	
	public MainFrame() {
		super("Cuentas Contables");
		mainMenu = new JTabbedPane();
		setSize(600,500);
		setLocationRelativeTo(null);
		setResizable(false);
		add(mainMenu);
		altaCuentas = new AltaCuentas();
		ModificacionCuenta modCuenta = new ModificacionCuenta ();
		mainMenu.addTab("Alta", altaCuentas);
		mainMenu.addTab("Modificación", modCuenta);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
