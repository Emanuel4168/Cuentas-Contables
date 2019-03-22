package components;

import java.awt.event.*;

public class JIntegerFormField extends JFormField implements KeyListener{
	private int size;

	public JIntegerFormField(int size){
		this(size,null);
	}
	
	public JIntegerFormField(int size, JFormField field){
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

		if(T=='-' && getText().length()>0){
			e.consume();
			return;
		}

	}
}
