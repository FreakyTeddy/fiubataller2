package controlador;

import Remoto.Remoto;

public class LanzadorRemoto extends Thread {

	private ControladorGeneral controlador;
	private Remoto remoto;
	private int puerto;
	private String ip;
	private boolean resultadoConexion;
	
	public LanzadorRemoto(ControladorGeneral controlador) {
		this.controlador= controlador;
		resultadoConexion= false;
	}
	
	public void setDatosConexion(Remoto remoto, int puerto, String ip) {
		this.remoto= remoto;
		this.puerto= puerto;
		this.ip= ip;
	}
	
	public void run() {
		resultadoConexion= remoto.iniciar(ip, puerto);
		controlador.ocultarVentanaEsperandoOponente();
	}
	
	public boolean getResultadoConexion() {
		return resultadoConexion;
	}
}
