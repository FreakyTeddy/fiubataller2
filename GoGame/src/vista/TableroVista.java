package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
	
	private JLabel labelBlanco;
	private JLabel labelNegro;
	private JLabel labelTurno;
	private JLabel labelNombreBlanco;
	private JLabel labelNombreNegro;
	private JLabel labelNombreTurno;
	
	private static Color colorNegro = new Color(0, 0, 0);
	
	public TableroVista(Tablero tablero, VentanaAplicacionGo vistaJuego) {
		super();
		
		setLayout(null);
		
		cargarSonidos();
		this.tablero= tablero;
		TAMANIO = tablero.getAncho();
		w = (double)ancho/(double)(TAMANIO-1);
		h = w;
		cargarImagenes();
		cargarInformacionJugadores();
		
		add(vistaJuego.getCheckBoxSonido());
		vistaJuego.getCheckBoxSonido().setForeground(colorNegro);
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
	
	private void cargarInformacionJugadores() {
		//negro
		labelNegro = new JLabel("Jugador Negro", JLabel.CENTER);
		labelNegro.setBounds(495, 65, 100, 20);
		labelNegro.setForeground(colorNegro);
		labelNombreNegro = new JLabel(FullMoonGo.getInstancia().getNombreNegro(), JLabel.CENTER);
		labelNombreNegro.setBounds(495,115, 95, 50);
		labelNombreNegro.setForeground(colorNegro);
		
		//blanco
		labelBlanco = new JLabel("Jugador Blanco");
		labelBlanco.setBounds(495, 370, 100, 20);
		labelBlanco.setForeground(colorNegro);
		labelNombreBlanco = new JLabel(FullMoonGo.getInstancia().getNombreBlanco(), JLabel.CENTER);
		labelNombreBlanco.setBounds(495,430, 95, 50);
		labelNombreBlanco.setForeground(colorNegro);
		
		//Turno
		labelTurno = new JLabel("Turno", JLabel.CENTER);
		labelTurno.setBounds(500, 210, 80, 20);
		labelTurno.setForeground(colorNegro);
		labelNombreTurno = new JLabel();
		labelNombreTurno.setBounds(495,270, 95, 50);
		labelNombreTurno.setForeground(colorNegro);
		
		add(labelNegro);
		add(labelNombreNegro);
		add(labelBlanco);
		add(labelNombreBlanco);
		add(labelTurno);
		add(labelNombreTurno);
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
		g.drawImage(imagenNegra, 520, 90, this);
		
		//dibujar blanco
		g.drawImage(imagenBlanca, 520, 400, this);

		
		//dibujar turno
		labelNombreTurno.setText(FullMoonGo.getInstancia().getJugadorDeTurno().getNombre());
		labelNombreTurno.setHorizontalAlignment(JLabel.CENTER);
				
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
