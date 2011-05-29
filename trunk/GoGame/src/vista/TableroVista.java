package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Juego.ColorPiedra;
import Juego.Posicion;
import Juego.Tablero;


public class TableroVista extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private static double w=50 , h=50;
	private static int margen=25;
	private static int ancho = 450;
	private int TAMANIO=9;
	
	private Image imagenTablero;
	private Image imagenBlanca;
	private Image imagenNegra;


	
	private Tablero tablero;

	private SoundEffect ultimoReproducido;
	
	//--------------------ENUM PARA LOS SONIDOS
	
	public enum SoundEffect {
		PIEZA1(PathImages.SonidoPieza1),
		PIEZA2(PathImages.SonidoPieza2);

		private Clip clip;

		SoundEffect(String soundFileName) {
			try {

				File soundFile = new File(soundFileName);

				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
				clip = AudioSystem.getClip();
				System.out.println("Se consiguio el clip :" + soundFileName);
				clip.open(audioInputStream);
				System.out.println("Se abrio el clip :" + soundFileName);

			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				System.out.println("Archivo problematico :" + soundFileName);		
				e.printStackTrace();
			} catch (RuntimeException e) {
				System.out.println("*********Sonido: " + e.getMessage());
			}
		}


		public void play() {
			if (clip.isRunning())
				clip.stop();
			clip.setFramePosition(0);
			clip.start();
		}


		static void init() {
			values();
		}
	}
	
	
	//-------------FIN ENUM--------------------------------------
	
	
	
	
	public TableroVista(Tablero tablero) {
		super();
		cargarSonidos();
		this.tablero= tablero;
		TAMANIO = tablero.getAncho();
		w = (double)ancho/(double)(TAMANIO-1);
		h = w;
		cargarImagenes();
	}
	
	private void cargarImagenes() {
		//TODO:Usar class loader para que funcione cuando se hace el jar.
		ImageIcon imageicon = new ImageIcon(PathImages.Tablero);  
		imagenTablero = imageicon.getImage();
		ImageIcon imageicon1 = new ImageIcon(PathImages.Blanca);  
		imagenBlanca = imageicon1.getImage();
		ImageIcon imageicon2 = new ImageIcon(PathImages.Negra);  
		imagenNegra = imageicon2.getImage();
	}

	/**
	 * Carga los sonidos. Hace lo mismo para los dos archivos,
	 * deberia unificarlo pero primero que me funcione xD.
	 */
	private void cargarSonidos() {
//		SoundEffect.init();
	    ultimoReproducido = SoundEffect.PIEZA2;

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

		for (int j = 0; j < TAMANIO; j++) {
			for (int i = 0; i < TAMANIO; i++) {
				ColorPiedra color = tablero.getCasillero(new Posicion(i,j));

				if (color == ColorPiedra.BLANCO)
					g.drawImage(imagenBlanca,(int)(margen-w/2+w*i),(int)(margen-h/2+h*j), (int)(w*0.8), (int)(h*0.8), this);

				if (color == ColorPiedra.NEGRO)
					g.drawImage(imagenNegra,(int)(margen-w/2+w*i),(int)(margen-h/2+h*j), (int)(w*0.8), (int)(h*0.8), this);
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

	/**
	 * transforma coordenadas de vista a una posicion en el tablero
	 */
	static public Posicion transformarPosicionFicha(int x, int y) {
		Posicion p = new Posicion((int)((x-margen+w/2)/w),(int)((y-margen+h/2)/h));
		//(int)((x-w/2)/w), (int)((y-h/2)/h));
		return p;
	}

	public void actualizar() {
		repaint(getBounds(null));
		reproducirSonido();
	}
	
	private void reproducirSonido() {
		try {
		if (ultimoReproducido == SoundEffect.PIEZA2){
			SoundEffect.PIEZA1.play();
			ultimoReproducido=SoundEffect.PIEZA1;
		}
		else{
			SoundEffect.PIEZA2.play();
			ultimoReproducido = SoundEffect.PIEZA2;
		} 
		} catch (RuntimeException e) {
			System.out.println("Error al reproducir sonido: " + e.getMessage());
		}

	}
}
