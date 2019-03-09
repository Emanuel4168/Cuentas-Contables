package components;

import java.awt.event.*;
import javax.swing.*;

public class JFormField extends JTextField implements ActionListener{

	private JComponent nextComponent;
	
	public JFormField(JComponent nextComponent) {
		this.nextComponent = nextComponent;
		addActionListener(this);
	}

	public JComponent getNextComponent() {
		return nextComponent;
	}

	public void setNextComponent(JComponent nextComponent) {
		this.nextComponent = nextComponent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(nextComponent != null) {
			nextComponent.grabFocus();
			
			if(nextComponent instanceof JButton) {
				JButton button = (JButton) nextComponent;
				button.doClick();
			}
		}
	}	
	
}