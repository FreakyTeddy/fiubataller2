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

	private static final int TAMANIO=9, w=50 , h=50;
	
	private Image imagenTablero;
	private Image imagenBlanca;
	private Image imagenNegra;
	
	private SoundEffect ultimoReproducido;
	
	private static final String pathSonidoPieza1 = "./sounds/pieza1.wav";
	private static final String pathSonidoPieza2 = "./sounds/pieza2.wav";
	private static final String pathImagenTablero = "./images/woodboard.jpg";
	private static final String pathImagenBlanca = "./images/blanca.png";
	private static final String pathImagenNegra = "./images/negra.png";
	
	private Tablero tablero;

	
	public enum SoundEffect {
		   PIEZA1(pathSonidoPieza1),  
		   PIEZA2(pathSonidoPieza2);  
   
		   private Clip clip;

		   SoundEffect(String soundFileName) {
		      try {
		    	  
		    	File soundFile = new File(soundFileName);
		         
		         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		         
		         clip = AudioSystem.getClip();
		         clip.open(audioInputStream);
		      } catch (UnsupportedAudioFileException e) {
		         e.printStackTrace();
		      } catch (IOException e) {
		         e.printStackTrace();
		      } catch (LineUnavailableException e) {
		         e.printStackTrace();
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
	
	public TableroVista(Tablero tablero) {
		super();
		this.tablero= tablero;
		cargarSonidos();
		cargarImagenes();
	}
	
	/**
	 * Carga los sonidos. Hace lo mismo para los dos archivos, 
	 * deberia unificarlo pero primero que me funcione xD.
	 */
	private void cargarSonidos() {
		SoundEffect.init();
		ultimoReproducido = SoundEffect.PIEZA2;
		
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

	/**
	 * transforma coordenadas de vista a una posicion en el tablero
	 */
	static public Posicion transformarPosicionFicha(int x, int y) {
		Posicion p = new Posicion((int)((x-w/2)/w), (int)((y-h/2)/h));
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
