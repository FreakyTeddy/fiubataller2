package vista;

import java.awt.Graphics;

import javax.swing.JPanel;

public class TableroGo extends JPanel {
	
	private final int TAMANIO=9, x=50 , y=50;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TableroGo() {
		super();

	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		dibujarTablero(g);
	}

	private void dibujarTablero(Graphics g) {
		//Dibujo las lineas horizontales
		for(int horizontal = 1; horizontal<= TAMANIO;horizontal++)
			g.drawLine(x, horizontal*y, TAMANIO*x, horizontal*y);
		
		//Dibujo las lineas verticales
		for(int vertical = 1; vertical<= TAMANIO;vertical++)
			g.drawLine(vertical*x, y, vertical*x, y*TAMANIO);
					
	}
	
	

}
