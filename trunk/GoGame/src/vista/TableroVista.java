package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.EstrategiaComputadoraAtaqueCuidadosoMasInteligente;
import Juego.EstrategiaComputadoraMiniMax;
import Juego.FinDelJuegoException;
import Juego.JugadaInvalidaException;
import Juego.Posicion;
import Juego.Tablero;

@SuppressWarnings("serial")
public class TableroVista extends JPanel implements Observer {
	
	private static final int TAMANIO=9, w=50 , h=50;
	
	private Image imagenTablero;
	private Image imagenBlanca;
	private Image imagenNegra;

	private static final String pathImagenTablero = "./images/woodboard.jpg";
	private static final String pathImagenBlanca = "./images/blanca.png";
	private static final String pathImagenNegra = "./images/negra.png";
	
	private Tablero tablero;
	private Estrategia estrategiaNegro;

	
	/**
	 * Create the panel.
	 */
	public TableroVista(Tablero tablero) {
		super();

		/* Lo pongo para probar, cuando este el controlador TIENE que volar*/
		this.tablero = tablero;
		tablero.addObserver(this);
		cargarImagenes();
	}
	
	private void cargarImagenes() {
		//TODO:Usar class loader para que funcione cuando se hace el jar.
		ImageIcon imageicon = new ImageIcon(pathImagenTablero);  
		imagenTablero = imageicon.getImage();
		ImageIcon imageicon1 = new ImageIcon(pathImagenBlanca);  
		imagenBlanca = imageicon1.getImage();
		ImageIcon imageicon2 = new ImageIcon(pathImagenNegra);  
		imagenNegra = imageicon2.getImage();
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		if (imagenTablero != null)  
		 g.drawImage(imagenTablero, 0, 0, getWidth(), getHeight(), this);  
	
		dibujarTablero((Graphics2D) g);		
		dibujarImagenFichas(g);	
	}

	private void dibujarImagenFichas(Graphics g) {
		if (tablero == null)
			return;

		for (int j = 1; j <= TAMANIO; j++) {
			for (int i = 1; i <= TAMANIO; i++) {
				ColorPiedra color = tablero.getCasillero(new Posicion(i-1,j-1));

				if (color == ColorPiedra.BLANCO)
					g.drawImage(imagenBlanca, w*i-w*3/8,h*j-h*3/8, this);

				if (color == ColorPiedra.NEGRO)
					g.drawImage(imagenNegra,w*i-w*3/8,h*j-h*3/8, this);
			}
		}
	}
	
	private void dibujarTablero(Graphics2D g) {
		g.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND ));
		g.setColor(Color.BLACK);
		
		//Lineas horizontales
		for(int horizontal = 1; horizontal<= TAMANIO;horizontal++)
			g.drawLine(w, horizontal*h, TAMANIO*w, horizontal*h);
		
		//Lineas verticales
		for(int vertical = 1; vertical<= TAMANIO;vertical++)
			g.drawLine(vertical*w, h, vertical*w, h*TAMANIO);
				
	}

	@Override
	public void update(Observable arg0, Object arg1) {
	    repaint(getBounds(null));
		
	}
	
//	public void click(int x, int y, int n){
//		Posicion p =  transformarPosicionFicha(x, y);
//		try{
//			if(n == 0)
//				tablero.agregarPiedra(p, ColorPiedra.BLANCO);
//			else
//				tablero.agregarPiedra(estrategiaNegro.getJugada(), ColorPiedra.NEGRO);
//		}
//		catch(JugadaInvalidaException e){
//			System.out.println("Eeeeeeeeeeeepa");
//			System.out.println(e.toString());
//		}
//		catch(FinDelJuegoException e){
//			System.out.println("Se terminó eljuego");
//		}
//	}
	
	/**
	 * transforma coordenadas de vista a una posicion en el tablero
	 */
	static public Posicion transformarPosicionFicha(int x, int y) {
		Posicion p = new Posicion((int)((x-w/2)/w), (int)((y-h/2)/h));
		return p;
	}
	
//	public Estrategia estrategiaNegro(){
//		return estrategiaNegro;
//	}

}
