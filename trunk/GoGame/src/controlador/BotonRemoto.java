package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Juego.Constantes;

import vista.MenuInicio;


public class BotonRemoto extends BotonServidor implements ActionListener {
	
	public BotonRemoto(JFrame padre, ControladorGeneral controlador) {
		super(padre, controlador);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String host = (String) JOptionPane.showInputDialog(vista, "Ip:puerto del host", "Conectar Con Servidor", 
				JOptionPane.PLAIN_MESSAGE,null, null, Constantes.IP +":"+Constantes.PUERTO);
		
		if ((host != null) && (host.length() > 0)) {
			int division = host.indexOf(':');
			String ip = host.substring(0, division);
			String nuevopuerto = host.substring(division+1);
			puerto = transformarPuerto(nuevopuerto);
			if(puerto != -1){
				System.out.println("Ip: "+ip + " - Puerto: " + puerto);
				controlador.jugarEnRed(ip,puerto);	
			}
			
		}
	}


}
