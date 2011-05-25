package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vista.MenuInicio;

import Juego.Constantes;

public class BotonServidor implements ActionListener {
	
	protected JFrame vista;
	protected MenuInicio menu;
	protected int puerto = Constantes.PUERTO;	
	protected ControladorGeneral controlador;
	
	public BotonServidor(JFrame padre, ControladorGeneral controlador) {
		vista = padre;
		this.controlador= controlador;
	}

	protected int transformarPuerto(String p){		
		int puerto = -1;
		try{
			puerto = Integer.parseInt(p);
		}catch(NumberFormatException e){
		}
		return puerto;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String puertoServidor = (String) JOptionPane.showInputDialog(vista, "Puerto para escuchar conexiones", "Crear Servidor", 
				JOptionPane.PLAIN_MESSAGE,null, null, Constantes.PUERTO);
		
		if ((puertoServidor != null) && (puertoServidor.length() > 0)) {
			puerto = transformarPuerto(puertoServidor);
			if(puerto != -1) {
				System.out.println("Hay que crear en el Puerto " + puerto);
				controlador.levantarServidor(puerto);
			}
		}
	}

}
