package components;

import java.awt.event.*;

public class JNumericFormField extends JFormField implements KeyListener{
	private int size;

	public JNumericFormField(int size){
		this(size,null);
	}
	
	public JNumericFormField(int size, JFormField field){
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
