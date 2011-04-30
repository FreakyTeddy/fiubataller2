package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.EstrategiaComputadoraAtacar;
import Juego.EstrategiaComputadoraAtaqueCuidadoso;
import Juego.EstrategiaComputadoraAtaqueCuidadosoMasInteligente;
import Juego.JugadaInvalidaException;
import Juego.Posicion;
import Juego.Tablero;

@SuppressWarnings("serial")
public class TableroGo extends JPanel implements Observer{
	
	private final int TAMANIO=9, x=50 , y=50;
	private Image piedraBlanca;
	private Image piedraNegra;
	

	Tablero tablero;
	Estrategia estrategiaNegro;

	String imageFile = "./images/woodboard.jpg";
	
	/**
	 * Create the panel.
	 */
	public TableroGo(Tablero tablero) {
		super();

		/* Lo pongo para probar, cuando este el controlador TIENE que volar*/
		this.tablero = tablero;
		tablero.addObserver(this);
		estrategiaNegro = new EstrategiaComputadoraAtaqueCuidadosoMasInteligente(tablero, ColorPiedra.NEGRO);
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		//TODO:Usar class loader para que funcione cuando se hace el jar.
		ImageIcon imageicon = new ImageIcon(imageFile);  
		Image image = imageicon.getImage();  
		if (image != null)  
		 g.drawImage(image, 0, 0, getWidth(), getHeight(), this);  
	
	
		Graphics2D g2d = (Graphics2D) g;
		dibujarTablero(g2d);
		dibujarFichas(g2d);
		
	}

	//TODO: Mejorar esto, podria ser un gradiente, o directamente usar images.
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

				/* cof cof cof cof cof.... */

		
				g2d.fill(new Ellipse2D.Float(x*i-x*3/8,y*j-y*3/8,x*3/4,y*3/4));
			}
	}


	
	private void dibujarTablero(Graphics2D g) {
		g.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND ));
		g.setColor(Color.BLACK);
		
		//Lineas horizontales
		for(int horizontal = 1; horizontal<= TAMANIO;horizontal++)
			g.drawLine(x, horizontal*y, TAMANIO*x, horizontal*y);
		
		//Lineas verticales
		for(int vertical = 1; vertical<= TAMANIO;vertical++)
			g.drawLine(vertical*x, y, vertical*x, y*TAMANIO);
				
	}

	@Override
	public void update(Observable arg0, Object arg1) {
	    repaint(getBounds(null));
		
	}
	
	public void click(int x, int y, int n){
		Posicion p =  transformarPosicionFicha(x, y);
		try{
			if(n == 0)
				tablero.agregarPiedra(p, ColorPiedra.BLANCO);
			else
				tablero.agregarPiedra(estrategiaNegro.getJugada(), ColorPiedra.NEGRO);
		}
		catch(JugadaInvalidaException e){
			System.out.println("Eeeeeeeeeeeepa");
			System.out.println(e.toString());
		}
	}
	
	/**
	 * transforma coordenadas de vista a una posicion en el tablero
	 */
	public Posicion transformarPosicionFicha(int x, int y) {
		Posicion p = new Posicion((int)((x-this.x/2)/this.x), (int)((y-this.y/2)/this.y));
		System.out.println("Click X:" +  p.getX() + " Y:" + p.getY());
		return p;
	}
	
	public Estrategia estrategiaNegro(){
		return estrategiaNegro;
	}

}







