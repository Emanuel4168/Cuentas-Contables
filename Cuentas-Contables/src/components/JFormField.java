package components;

import java.awt.event.*;
import javax.swing.*;

public class JFormField extends JTextField implements ActionListener{

	private JFormField nextField;
	
	public JFormField(JFormField nextField) {
		this.nextField = nextField;
	}

	public JFormField getNextField() {
		return nextField;
	}

	public void setNextField(JFormField nextField) {
		this.nextField = nextField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		nextField.requestFocus();
	}	
	
}
