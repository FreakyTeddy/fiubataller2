package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Juego.ColorPiedra;
import Juego.FullMoonGo;
import Juego.Posicion;
import Juego.Tablero;


public class TableroVista extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private static double w=50 , h=50;
	private static final int margen = 25;
	private static final int ancho = 450;
	private int TAMANIO=9;
	
	private Image imagenTablero;
	private Image imagenBlanca;
	private Image imagenNegra;

	private Tablero tablero;

	private SoundEffect ultimoReproducido;
	
	
	public TableroVista(Tablero tablero, VentanaAplicacionGo vistaJuego) {
		super();
		
		setLayout(null);
		
		cargarSonidos();
		this.tablero= tablero;
		TAMANIO = tablero.getAncho();
		w = (double)ancho/(double)(TAMANIO-1);
		h = w;
		cargarImagenes();
		
		add(vistaJuego.getCheckBoxSonido());
		vistaJuego.getCheckBoxSonido().setForeground(new Color(0, 0, 0));
	}
	
	private void cargarImagenes() {
		ClassLoader cldr = this.getClass().getClassLoader();
		URL imagenTableroURL   = cldr.getResource(Paths.Tablero);
		URL imagenBlancaURL   = cldr.getResource(Paths.Blanca);
		URL imagenNegraURL   = cldr.getResource(Paths.Negra);
		
		ImageIcon imageicon = new ImageIcon(imagenTableroURL);  
		imagenTablero = imageicon.getImage();
		ImageIcon imageicon1 = new ImageIcon(imagenBlancaURL);  
		imagenBlanca = imageicon1.getImage();
		ImageIcon imageicon2 = new ImageIcon(imagenNegraURL);  
		imagenNegra = imageicon2.getImage();
	}

	private void cargarSonidos() {
	    ultimoReproducido = SoundEffect.PIEZA2;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if (imagenTablero != null)  
		 g.drawImage(imagenTablero, 0, 0, getWidth(), getHeight(), this);  
	
		dibujarTablero((Graphics2D) g);		
		dibujarImagenFichas(g);	
		dibujarTurno(g);
	}

	private void dibujarImagenFichas(Graphics g) {
		if (tablero == null)
			return;

		for (int j = 0; j < TAMANIO; j++) {
			for (int i = 0; i < TAMANIO; i++) {
				ColorPiedra color = tablero.getCasillero(new Posicion(i,j));

				if (color == ColorPiedra.BLANCO)
					g.drawImage(imagenBlanca,(int)(margen-w/2+w*i),(int)(margen-h/2+h*j), (int)w, (int)h, this);

				if (color == ColorPiedra.NEGRO)
					g.drawImage(imagenNegra,(int)(margen-w/2+w*i),(int)(margen-h/2+h*j), (int)w, (int)h, this);
			}
		}
	}
	
	private void dibujarTablero(Graphics2D g) {
		g.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND ));
		g.setColor(Color.BLACK);
		
		//Lineas horizontales
		for(int horizontal = 0; horizontal< TAMANIO;horizontal++)
			g.drawLine(margen, margen+(int)(horizontal*h), 450+margen, margen+(int)(horizontal*h));
		
		//Lineas verticales
		for(int vertical = 0; vertical< TAMANIO;vertical++)
			g.drawLine(margen+(int)(vertical*w), margen, margen+(int)(vertical*w), 450+margen);
	}
	
	private void dibujarTurno(Graphics g) {
		
		//dibujar negro
		g.drawString("Jugador Negro", 495, 80);
		g.drawImage(imagenNegra, 520, 90, this);
		g.drawString(FullMoonGo.getInstancia().getNombreNegro(), 495, 150);
		
		//dibujar blanco
		g.drawString("Jugador Blanco", 495, 390);
		g.drawImage(imagenBlanca, 520, 400, this);
		g.drawString(FullMoonGo.getInstancia().getNombreNegro(), 495, 460);
		
		//dibujar turno
		g.drawString("Turno", 520, 220);
		g.drawString(FullMoonGo.getInstancia().getJugadorDeTurno().getNombre(), 495, 300);
		
		Image imagenTurno = null;
		if (FullMoonGo.getInstancia().getJugadorDeTurno().getColor() == ColorPiedra.BLANCO)
			imagenTurno = imagenBlanca;
		if (FullMoonGo.getInstancia().getJugadorDeTurno().getColor() == ColorPiedra.NEGRO)
			imagenTurno = imagenNegra;
		
		g.drawImage(imagenTurno, 520, 235, this);
	}

	/**
	 * transforma coordenadas de vista a una posicion en el tablero
	 */
	static public Posicion transformarPosicionFicha(int x, int y) {
		Posicion p = new Posicion((int)((x-margen+w/2)/w),(int)((y-margen+h/2)/h));
		return p;
	}

	public void actualizar() {
		repaint(getBounds(null));
		reproducirSonido();
	}
	
	private void reproducirSonido() {
		
		if (ultimoReproducido == SoundEffect.PIEZA2){
			SoundEffect.PIEZA1.play();
			ultimoReproducido=SoundEffect.PIEZA1;
		}
		else{
			SoundEffect.PIEZA2.play();
			ultimoReproducido = SoundEffect.PIEZA2;
		} 

	}
}
