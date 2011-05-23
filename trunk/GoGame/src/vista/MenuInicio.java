package vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import Juego.ColorPiedra;
import Juego.FullMoonGo;

import controlador.BotonJugador;
import controlador.BotonMaquina;
import controlador.BotonRemoto;
import controlador.ComboJugador;
import controlador.ComboTamanioTablero;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * TODO: Habria que refactorizar un poco el tema de la imagen de fondo.
 * Solucionar el tema del singleton.
 * @author matias
 *
 */
public class MenuInicio extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3634635831507335567L;

	private Image imagenTablero;
	
	private static final String pathImagenTablero = "./images/go-game1.jpg";
	
	
	private ComboTamanioTablero comboTablero = new ComboTamanioTablero();
	private ComboJugador comboBlanco;
	private ComboJugador comboNegro;
	private JTextField nombreNegro;
	private JTextField nombreBlanco;
	private VentanaAplicacionGo vista;
	
	public MenuInicio(VentanaAplicacionGo vistaJuego) {
		
		vista = vistaJuego;
		ImageIcon imageicon = new ImageIcon(pathImagenTablero);  
		imagenTablero = imageicon.getImage();
		
		comboBlanco = new ComboJugador(ColorPiedra.BLANCO, vista);
		comboNegro = new ComboJugador(ColorPiedra.NEGRO, vista);
		
		setLayout(null);
		
		JLabel labelTablero = new JLabel("Tamaño del Tablero");
		labelTablero.setBounds(40, 40, 160, 30);
		labelTablero.setForeground(new Color(250, 250, 250));
		comboTablero.getCombo().setBounds(200, 40, 100, 30);
		
		JLabel labelBlanco = new JLabel("Jugador Blanco");
		labelBlanco.setBounds(40, 80, 160, 30);
		labelBlanco.setForeground(new Color(250, 250, 250));
		comboBlanco.getCombo().setBounds(200, 80, 100, 30);
		nombreBlanco = new JTextField("Nombre Blanco");
		nombreBlanco.setBounds(320, 80, 150, 30);
		
		JLabel labelNegro = new JLabel("Jugador Negro");
		labelNegro.setBounds(40, 120, 160, 30);
		labelNegro.setForeground(new Color(250, 250, 250));
		comboNegro.getCombo().setBounds(200, 120, 100, 30);
		nombreNegro = new JTextField("Nombre Negro");
		nombreNegro.setBounds(320, 120, 150, 30);
		
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(this);
		btnJugar.setBounds(380, 430, 100, 40);
		
		add(labelTablero);
		add(comboTablero.getCombo());
		add(labelBlanco);
		add(comboBlanco.getCombo());
		add(nombreBlanco);
		add(labelNegro);
		add(comboNegro.getCombo());
		add(nombreNegro);
		add(btnJugar);
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if (imagenTablero != null)  
			 g.drawImage(imagenTablero, 0, 0, getWidth(), getHeight(), this);  
		
	}

	@Override
	public void actionPerformed(ActionEvent e) { //accion del boton jugar
		FullMoonGo.getInstancia().crearJugador(nombreBlanco.getText(), ColorPiedra.BLANCO, comboBlanco.getEstrategiaElegida());
		FullMoonGo.getInstancia().crearJugador(nombreNegro.getText(), ColorPiedra.NEGRO, comboNegro.getEstrategiaElegida());
		Thread juego = new Thread(FullMoonGo.getInstancia());
		juego.start();
		vista.mostrarTablero(FullMoonGo.getInstancia().getTablero());
	}

}
