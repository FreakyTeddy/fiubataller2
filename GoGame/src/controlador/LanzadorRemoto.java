package controlador;

import Remoto.Arbitro;

public class LanzadorRemoto extends Thread {

	private ControladorGeneral controlador;
	private Arbitro arbitro;
	private int puerto;
	private String ip;
	private boolean resultadoConexion;
	
	public LanzadorRemoto(ControladorGeneral controlador) {
		this.controlador= controlador;
		resultadoConexion= false;
	}
	
	public void setDatosConexion(Arbitro remoto, int puerto, String ip) {
		this.arbitro= remoto;
		this.puerto= puerto;
		this.ip= ip;
	}
	
	public void run() {
		resultadoConexion= arbitro.iniciarConexion(ip, puerto);
		controlador.ocultarVentanaEsperandoOponente();
	}
	
	public boolean getResultadoConexion() {
		return resultadoConexion;
	}
}
