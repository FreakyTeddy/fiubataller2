package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Juego.ColorPiedra;
import Juego.JugadaInvalidaException;
import Juego.Jugador;
import Juego.Posicion;
import Juego.Tablero;

public class TableroGo extends JPanel implements Observer{
	
	private final int TAMANIO=9, x=50 , y=50;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Tablero tablero;

	/**
	 * Create the panel.
	 */
	public TableroGo(Tablero tablero) {
		super();

		/* Lo pongo para probar, cuando esté el controlador TIENE que volar*/
		this.tablero = tablero;
		tablero.addObserver(this);
		// Jugador jugadorBlanco = new Jugador("Soy Blanco", ColorPiedra.BLANCO, tablero);
		// Jugador jugadorNegro = new Jugador("Soy Negro", ColorPiedra.NEGRO, tablero);
		// try {
			
		// 	jugadorBlanco.jugar(new Posicion(0,0));
		// 	jugadorNegro.jugar(new Posicion(1,1));
		// 	jugadorBlanco.jugar(new Posicion(0,1));
			
		// } catch (JugadaInvalidaException e) {
		// 	System.out.println(e);
		// }
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		dibujarTablero(g2d);
		dibujarFichas(g2d);
	}

	private void dibujarFichas(Graphics2D g2d) {
		if (tablero == null)
			return;

		for (int j = 1; j <= TAMANIO; j++)
			for (int i = 1; i <= TAMANIO; i++) {
				ColorPiedra color = tablero.getCasillero(new Posicion(i-1,j-1));

				/* cof cof*/
				if (color == ColorPiedra.VACIO)
					continue;
				if (color == ColorPiedra.BLANCO)
					g2d.setPaint(Color.WHITE);
				else
					g2d.setPaint(Color.BLACK);

				/* cof cof cof cof cof....*/
				g2d.fill(new Ellipse2D.Float(x*i-x*3/8,y*j-y*3/8,x*3/4,y*3/4));
			}
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
	    repaint(getBounds(null));
		
	}
	
	public void click(int x, int y, int n){
		System.out.println("Click X:" + (int)((x-this.x)/this.x) + " Y:" + (int)((y-this.y)/this.y) );
		try{
			tablero.agregarPiedra(new Posicion((int)((x-this.x)/this.x), (int)((y-this.y)/this.y)), n==0?ColorPiedra.BLANCO:ColorPiedra.NEGRO);
		}
		catch(JugadaInvalidaException e){
			System.out.println("Eeeeeeeeeeeepa");
			System.out.println(e.toString());
		}
	}

}







