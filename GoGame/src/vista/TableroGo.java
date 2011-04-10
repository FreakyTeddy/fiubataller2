package vista;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

public class TableroGo extends JPanel implements Observer {
	
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
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		dibujarTablero(g2d);
		dibujarFichas(g2d);
	}

	private void dibujarFichas(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	private void dibujarTablero(Graphics2D g) {
		g.setStroke(new BasicStroke(4));
		
		//Dibujo las lineas horizontales
		for(int horizontal = 1; horizontal<= TAMANIO;horizontal++)
			g.drawLine(x, horizontal*y, TAMANIO*x, horizontal*y);
		
		//Dibujo las lineas verticales
		for(int vertical = 1; vertical<= TAMANIO;vertical++)
			g.drawLine(vertical*x, y, vertical*x, y*TAMANIO);
					
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	

}
