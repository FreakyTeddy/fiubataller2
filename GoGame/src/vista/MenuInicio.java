package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.net.URL;

/**
 * @author matias
 */
public class MenuInicio extends JPanel {
	
	private static final long serialVersionUID = -3634635831507335567L;

	private Image imagenTablero;
	
	private JComboBox comboTamanioTablero;
	private JComboBox comboBlanco;
	private JComboBox comboNegro;
	private JTextField nombreNegro;
	private JTextField nombreBlanco;
	private VentanaAplicacionGo vista;
	private JButton botonJugarEnRed;
	private JButton botonCrearServidor;
	private JButton botonJugarLocal;
	
	private Color colorBlanco;
	private JCheckBox checkBoxSonido;
	
	public MenuInicio(VentanaAplicacionGo vistaJuego) {
		
		super();
		
		vista = vistaJuego;
		ClassLoader cldr = this.getClass().getClassLoader();
		URL uRLImagenMenuInicio   = cldr.getResource(Paths.MenuInicio);
		ImageIcon imageicon = new ImageIcon(uRLImagenMenuInicio);  
		imagenTablero = imageicon.getImage();
		
		comboBlanco= new JComboBox();
		comboNegro= new JComboBox();
		
		setLayout(null);
		
		Font fuente12 = new Font("Dialog", Font.PLAIN, 12);
		Font fuente11 = new Font("Dialog", Font.PLAIN, 11);
		colorBlanco = new Color(250, 250, 250);
		
		JLabel labelTablero = new JLabel("Tamaño del Tablero");
		labelTablero.setBounds(40, 40, 160, 30);
		labelTablero.setForeground(colorBlanco);
		comboTamanioTablero= new JComboBox();
		comboTamanioTablero.setBounds(200, 40, 130, 30);
		comboTamanioTablero.setFont(fuente12);
		
		JLabel labelNegro = new JLabel("Jugador Negro");
		labelNegro.setBounds(40, 80, 160, 30);
		labelNegro.setForeground(colorBlanco);
		comboNegro.setBounds(200, 80, 130, 30);
		comboNegro.setFont(fuente12);
		nombreNegro = new JTextField("Nombre Negro");
		nombreNegro.setBounds(360, 80, 160, 30);
		
		JLabel labelBlanco = new JLabel("Jugador Blanco");
		labelBlanco.setBounds(40, 120, 160, 30);
		labelBlanco.setForeground(colorBlanco);
		comboBlanco.setBounds(200, 120, 130, 30);
		comboBlanco.setFont(fuente12);
		nombreBlanco = new JTextField("Nombre Blanco");
		nombreBlanco.setBounds(360, 120, 160, 30);
		
		botonJugarLocal = new JButton("Jugar");
		botonJugarLocal.setBounds(390, 440, 120, 40);
		botonJugarLocal.setFont(fuente12);
		
		botonCrearServidor = new JButton("Crear Partida en Red");
		botonCrearServidor.setBounds(200, 440, 150, 40);
		botonCrearServidor.setFont(fuente11);
		
		botonJugarEnRed = new JButton("Jugar en Red");
		botonJugarEnRed.setBounds(40, 440, 120, 40);
		botonJugarEnRed.setFont(fuente12);
		
		checkBoxSonido = vista.getCheckBoxSonido();
		checkBoxSonido.setForeground(colorBlanco);
		
		add(checkBoxSonido);
		add(labelTablero);
		add(comboTamanioTablero);
		add(labelBlanco);
		add(comboBlanco);
		add(nombreBlanco);
		add(labelNegro);
		add(comboNegro);
		add(nombreNegro);
		add(botonJugarLocal);
		add(botonJugarEnRed);
		add(botonCrearServidor);
		
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if (imagenTablero != null)  
			 g.drawImage(imagenTablero, 0, 0, getWidth(), getHeight(), this);  	
		
		checkBoxSonido.setForeground(colorBlanco);
		add(checkBoxSonido);
	}

	//Obtener objetos de la vista
	public VentanaAplicacionGo getVentanaAplicacionGo() {
		return vista;
	}
	
	public JButton getBotonJugarEnRed() {
		return botonJugarEnRed;
	}
	
	public JButton getBotonCrearServidor() {
		return botonCrearServidor;
	}
	
	public JButton getBotonJugarLocal() {
		return botonJugarLocal;
	}
	
	public  JComboBox getComboJugadorBlanco() {
		return comboBlanco;
	}

	public  JComboBox getComboJugadorNegro() {
		return comboNegro;
	}
	
	public JComboBox getComboTamanioTablero() {
		return comboTamanioTablero;
	}
	
	public JTextField getJTextFieldNombreJugadorBlanco() {
		return nombreBlanco;
	}
	
	public JTextField getJTextFieldNombreJugadorNegro() {
		return nombreNegro;
	}
}