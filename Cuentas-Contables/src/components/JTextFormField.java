package components;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JTextFormField extends JFormField implements KeyListener{
	private int size;

	public JTextFormField(int size){
		this(size,null);
	}
	
	public JTextFormField(int size, JFormField field){
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
		if (  ! ( T>='a' && T<='z' ||  T>='A' && T<='Z')    ){
			e.consume();
			return;
		}

	}
}