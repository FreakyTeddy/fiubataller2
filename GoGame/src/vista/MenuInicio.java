package vista;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;

import controlador.BotonJugador;
import controlador.BotonMaquina;
import controlador.BotonRemoto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * TODO: Habria que refactorizar un poco el tema de la imagen de fondo.
 * Solucionar el tema del singleton.
 * @author matias
 *
 */
public class MenuInicio extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3634635831507335567L;

	private Image imagenTablero;
	
	private final String pathImagenTablero = "./images/woodboard.jpg";
	
	public MenuInicio() {
		
		ImageIcon imageicon = new ImageIcon(pathImagenTablero);  
		imagenTablero = imageicon.getImage();
		
		setLayout(null);
		
		JButton btnJugarVsOtro = new JButton("Jugar vs Jugador");
		btnJugarVsOtro.setBounds(66, 36, 251, 91);
		btnJugarVsOtro.addActionListener(new BotonJugador());

		
		JButton btnJugarVsComputadora = new JButton("Jugar vs Computadora");
		btnJugarVsComputadora.addActionListener(new BotonMaquina());
		btnJugarVsComputadora.setBounds(66, 171, 251, 91);

		JButton btnJugarVsRemoto = new JButton("Jugar vs Remoto");
		btnJugarVsRemoto.addActionListener(new BotonRemoto());
		btnJugarVsRemoto.setBounds(66, 300, 251, 91);
		
		add(btnJugarVsOtro);
		add(btnJugarVsComputadora);
		add(btnJugarVsRemoto);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if (imagenTablero != null)  
			 g.drawImage(imagenTablero, 0, 0, getWidth(), getHeight(), this);  
		
	
		
		
		
	}
	
	
	

}
