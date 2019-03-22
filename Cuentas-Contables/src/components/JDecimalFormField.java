package components;

import java.awt.event.*;

public class JDecimalFormField extends JFormField implements KeyListener{
	private int size;

	public JDecimalFormField(int size){
		this(size,null);
	}
	
	public JDecimalFormField(int size, JFormField field){
		super(field);
		this.size = size;
		addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent e) {
		
	}


	public void keyReleased(KeyEvent e) {
		
	}


	public void keyTyped(KeyEvent e) {
		if(getText().length() == size){
			e.consume();
			return;
		}
		
		char T=e.getKeyChar();
		if (  ! ( T>='0' && T<='9' || T=='.' || T=='-')    ){
			e.consume();
			return;
		}

		if(T=='.' && getText().indexOf(".") >=0 ){
			e.consume();
			return;
		}
		if(T=='-' && getText().length()>0){
			e.consume();
			return;
		}

	}
}
