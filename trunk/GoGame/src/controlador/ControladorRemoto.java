package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Juego.Constantes;

import vista.VentanaAplicacionGo;

public class ControladorRemoto {

	private ControladorGeneral controlador;
	private VentanaAplicacionGo ventana;
	
	public ControladorRemoto(ControladorGeneral controlador, VentanaAplicacionGo ventana) {
		this.controlador= controlador;
		this.ventana= ventana;
		initCallbacks();
	}

	private int transformarPuerto(String p){		
		int puerto = -1;
		try{
			puerto = Integer.parseInt(p);
		}catch(NumberFormatException e){
		}
		return puerto;
	}
	
	private void initCallbacks() {
		JButton botonCrearServidor= ventana.getMenuInicio().getBotonCrearServidor();
		botonCrearServidor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String puertoServidor = (String) JOptionPane.showInputDialog(ventana.getFramePrincipal(), "Puerto para escuchar conexiones", "Crear Servidor", 
						JOptionPane.PLAIN_MESSAGE,null, null, Constantes.PUERTO);
				
				if ((puertoServidor != null) && (puertoServidor.length() > 0)) {
					int puerto = transformarPuerto(puertoServidor);
					if(puerto != -1) {
						controlador.levantarServidor(puerto);
					}
				}		
			}
		});
		
		JButton botonJugarEnRed= ventana.getMenuInicio().getBotonJugarEnRed();
		botonJugarEnRed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String host = (String) JOptionPane.showInputDialog(ventana.getFramePrincipal(), "Ip:puerto del host", "Conectar Con Servidor", 
						JOptionPane.PLAIN_MESSAGE,null, null, Constantes.IP +":"+Constantes.PUERTO);
				
				if ((host != null) && (host.length() > 0)) {
					int division = host.indexOf(':');
					String ip = host.substring(0, division);
					String nuevopuerto = host.substring(division+1);
					int puerto = transformarPuerto(nuevopuerto);
					if(puerto != -1){
						controlador.jugarEnRed(ip,puerto);	
					}
				}				
			}
		});
	}
}
