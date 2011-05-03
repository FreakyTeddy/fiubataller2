package Remoto.GTP;

import Remoto.Cliente;

public class GTP {

	//Datos programa
	private static String NRO_PROTOCOL_VERSION="1";
	private static String PROGRAM_NAME="GNU Go";
	private static String PROGRAM_VERSION="1";
	
	private ControladorMsjsEntrantes controladorEntrante;
	private int id;
	
	public GTP(Cliente remoto) {
		controladorEntrante= new ControladorMsjsEntrantes(remoto);
		this.id= 0;
	}
	
	/****************************
	 * Mensajes administrativos *
	 ****************************/
	
	public String mensajeVersionProtocolo() {
		this.id++;
		return id + " " + Constantes.PROTOCOL_VERSION + " " + NRO_PROTOCOL_VERSION + Constantes.FIN_MSJ;
	}
	
	public String mensajeSalir() {
		this.id++;
		return id + " " + Constantes.QUIT + Constantes.FIN_MSJ;
	}

	/*******************************
	 * Identificacion del programa *
	 *******************************/
	
	public String mensajeNombre() {
		this.id++;
		return id + " " + Constantes.NAME + " " + PROGRAM_NAME + Constantes.FIN_MSJ;
	}
	
	public String mensajeVersion() {
		this.id++;
		return id + " " + Constantes.VERSION + " " + PROGRAM_VERSION + Constantes.FIN_MSJ;
	}
	
	/*********************
	 * Mensajes comandos *
	 *********************/
	public String mensajeComandoSoportado(String comando) {
		this.id++;
		return id + " " + Constantes.KNOWN_COMMAND + " " + comando + Constantes.FIN_MSJ;
	}
	
	public String mensajeListarCommandos() {
		this.id++;
		return id + " " + Constantes.LIST_COMMANDS + Constantes.FIN_MSJ;
	}
	
	/******************
	 * Mensajes setup *
	 ******************/
	public String mensajeTamanioTablero(int tamanio) {
		this.id++;
		return id + " " + Constantes.BOARDSIZE + " " + tamanio + Constantes.FIN_MSJ;
	}	
	
	public String mensajeLimpiarTablero() {
		this.id++;
		return id + " " + Constantes.CLEAR_BOARD + Constantes.FIN_MSJ;
	}	
	
	public String mensajeKomi(double komi) {
		this.id++;
		return id + " " + Constantes.KOMI + " " + komi + Constantes.FIN_MSJ;
	}	

	/******************
	 * Mensajes juego *
	 ******************/
	public String mensajeJugar(String color, String posicion) {
		this.id++;
		return id + " " + Constantes.PLAY + " " + color + " " + posicion + Constantes.FIN_MSJ;
	}	

	public String mensajeGenMovimiento(String color) {
		this.id++;
		return id + " " + Constantes.GENMOVE + " " + color + Constantes.FIN_MSJ;
	}	
	
	/*******************************
	 * Procesar mensajes entrentes *
	 *******************************/	
	public String procesarMensajeEntrante(String mensaje) {
		return controladorEntrante.procesarMensaje(mensaje);
	}
}
