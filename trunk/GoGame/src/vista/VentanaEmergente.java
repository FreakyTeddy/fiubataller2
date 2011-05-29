package vista;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VentanaEmergente {

	private JFrame ventanaPadre;
	private JDialog ventanaEsperandoOponente;
	JButton botonCancelarEsperandoOponente;

	public VentanaEmergente(JFrame ventanaPadre) {
		this.ventanaPadre= ventanaPadre;
		crearVentanaEsperandoOponente();
	}

	//Ventana esperando oponente
	private void crearVentanaEsperandoOponente() {
		ventanaEsperandoOponente= new JDialog();
		ventanaEsperandoOponente.setTitle("Fullmoon");
		ventanaEsperandoOponente.setIconImage((new ImageIcon(PathImages.Icono)).getImage());
		Container panel= ventanaEsperandoOponente.getContentPane();
		panel.setLayout(null);
		ImageIcon icon= new ImageIcon(PathImages.EsperandoOponente); 		
		JLabel label= new JLabel("Esperando Oponente...", icon, JLabel.CENTER);
		label.setBounds(0, 0, 250, 60);
		panel.add(label);
		botonCancelarEsperandoOponente= new JButton("Cancelar");
		botonCancelarEsperandoOponente.setBounds(130, 65, 100, 20);
		panel.add(botonCancelarEsperandoOponente);
		ventanaEsperandoOponente.setResizable(false);
		ventanaEsperandoOponente.setUndecorated(true);
		ventanaEsperandoOponente.setModal(true);
	}
	
	public void mostrarVentanaEsperandoOponente() {
		ventanaEsperandoOponente.setSize(new Dimension(250, 90));
		ventanaEsperandoOponente.setLocationRelativeTo(ventanaPadre);
		ventanaEsperandoOponente.setVisible(true);
	}
	
	public void ocultarVentanaEsperandoOponente() {
		ventanaEsperandoOponente.setVisible(false);
	}
	
	public JButton getBotonCancelarEsperandoOponente() {
		return botonCancelarEsperandoOponente;
	}
	
	//Ventana Error al conectarse al servidor
	public void mostrarVentanaErrorAlConectarseAlServidor() {
		JOptionPane.showMessageDialog(ventanaPadre, "El servidor no responde.",
		    "Error al conectarse...", JOptionPane.ERROR_MESSAGE);
	}
	
	//Ventana Error al levantar servidor
	public void mostrarVentanaErrorAlLevantarServidor() {
		JOptionPane.showMessageDialog(ventanaPadre, "Servidor no creado.",
		    "Error al crear servidor...", JOptionPane.ERROR_MESSAGE);		
	}
}
