package vista;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VentanaEmergente {

	private JFrame ventanaPadre;
	private JDialog ventanaEsperandoOponente;

	private static final String pathImageEsperandoOponente= "./images/ajax-loader.gif";
	
	public VentanaEmergente(JFrame ventanaPadre) {
		this.ventanaPadre= ventanaPadre;
		crearVentanaEsperandoOponente();
	}

	//Ventana esperando oponente
	private void crearVentanaEsperandoOponente() {
		ventanaEsperandoOponente= new JDialog();
		Container panel= ventanaEsperandoOponente.getContentPane();
		ImageIcon icon= new ImageIcon(pathImageEsperandoOponente); 		
		JLabel label= new JLabel("Esperando Oponente...", icon, JLabel.CENTER);
		panel.add(label);
		ventanaEsperandoOponente.setModal(true);
		ventanaEsperandoOponente.setUndecorated(true);
	}
	
	public void mostrarVentanaEsperandoOponente() {
		ventanaEsperandoOponente.setSize(new Dimension(250, 60));
		ventanaEsperandoOponente.setLocationRelativeTo(ventanaPadre);
		ventanaEsperandoOponente.setVisible(true);
	}
	
	public void ocultarVentanaEsperandoOponente() {
		ventanaEsperandoOponente.setVisible(false);
	}
	
	//Ventana Error al conectarse al servidor
	public void mostrarVentanaErrorAlConectarseAlServidor() {
		JOptionPane.showMessageDialog(ventanaPadre, "El servidor no responde.",
		    "Error al conectarse...", JOptionPane.ERROR_MESSAGE);
	}
}
