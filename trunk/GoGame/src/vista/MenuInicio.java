package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
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
	private JCheckBox checkBoxSonido;
	
	public MenuInicio(VentanaAplicacionGo vistaJuego) {
		
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
		
		JLabel labelTablero = new JLabel("Tamaño del Tablero");
		labelTablero.setBounds(40, 40, 160, 30);
		labelTablero.setForeground(new Color(250, 250, 250));
		comboTamanioTablero= new JComboBox();
		comboTamanioTablero.setBounds(200, 40, 100, 30);
		comboTamanioTablero.setFont(fuente12);
		
		JLabel labelNegro = new JLabel("Jugador Negro");
		labelNegro.setBounds(40, 80, 160, 30);
		labelNegro.setForeground(new Color(250, 250, 250));
		comboNegro.setBounds(200, 80, 100, 30);
		comboNegro.setFont(fuente11);
		nombreNegro = new JTextField("Nombre Negro");
		nombreNegro.setBounds(320, 80, 150, 30);
		
		JLabel labelBlanco = new JLabel("Jugador Blanco");
		labelBlanco.setBounds(40, 120, 160, 30);
		labelBlanco.setForeground(new Color(250, 250, 250));
		comboBlanco.setBounds(200, 120, 100, 30);
		comboBlanco.setFont(fuente11);
		nombreBlanco = new JTextField("Nombre Blanco");
		nombreBlanco.setBounds(320, 120, 150, 30);
		
		botonJugarLocal = new JButton("Jugar");
		botonJugarLocal.setBounds(350, 430, 100, 40);
		botonJugarLocal.setFont(fuente11);
		
		botonCrearServidor = new JButton("Crear Partida");
		botonCrearServidor.setBounds(180, 430, 120, 40);
		botonCrearServidor.setFont(fuente11);
		
		botonJugarEnRed = new JButton("Jugar en Red");
		botonJugarEnRed.setBounds(50, 430, 100, 40);
		botonJugarEnRed.setFont(fuente11);
		
		checkBoxSonido = new JCheckBox("Musica");
		checkBoxSonido.setBounds(320, 45, 129, 23);
		checkBoxSonido.setFont(fuente12);
		checkBoxSonido.setForeground(new Color(250, 250, 250));
		
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
		add(checkBoxSonido);

	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if (imagenTablero != null)  
			 g.drawImage(imagenTablero, 0, 0, getWidth(), getHeight(), this);  	
	}

	//Obtener objetos de la vista
	public VentanaAplicacionGo getVentanaAplicacionGo() {
		return vista;
	}
	
	public JCheckBox getCheckBoxSonido(){
		return checkBoxSonido;
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