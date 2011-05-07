package Remoto.GTP;

import java.util.ArrayList;

public class Gtp {

	private int id;
	
	public Gtp() {
		this.id= 0;
	}
	
	/****************************
	 * Mensajes administrativos *
	 ****************************/
	
	public String mensajeVersionProtocolo() {
		this.id++;
		return id + Constantes.ESPACIO + Constantes.PROTOCOL_VERSION + Constantes.FIN_MSJ;
	}
	
	public String mensajeSalir() {
		this.id++;
		return id + Constantes.ESPACIO + Constantes.QUIT + Constantes.FIN_MSJ;
	}

	/*******************************
	 * Identificacion del programa *
	 *******************************/
	
	public String mensajeNombre() {
		this.id++;
		return id + Constantes.ESPACIO + Constantes.NAME + Constantes.FIN_MSJ;
	}
	
	public String mensajeVersion() {
		this.id++;
		return id + Constantes.ESPACIO + Constantes.VERSION + Constantes.FIN_MSJ;
	}
	
	/*********************
	 * Mensajes comandos *
	 *********************/
	public String mensajeComandoSoportado(String comando) {
		this.id++;
		return id + Constantes.ESPACIO + Constantes.KNOWN_COMMAND + Constantes.ESPACIO + comando + Constantes.FIN_MSJ;
	}
	
	public String mensajeListarCommandos() {
		this.id++;
		return id + Constantes.ESPACIO + Constantes.LIST_COMMANDS + Constantes.FIN_MSJ;
	}
	
	/******************
	 * Mensajes setup *
	 ******************/
	public String mensajeTamanioTablero(int tamanio) {
		this.id++;
		return id + Constantes.ESPACIO + Constantes.BOARDSIZE + Constantes.ESPACIO + tamanio + Constantes.FIN_MSJ;
	}	
	
	public String mensajeLimpiarTablero() {
		this.id++;
		return id + Constantes.ESPACIO + Constantes.CLEAR_BOARD + Constantes.FIN_MSJ;
	}	
	
	public String mensajeKomi(double komi) {
		this.id++;
		return id + Constantes.ESPACIO + Constantes.KOMI + Constantes.ESPACIO + komi + Constantes.FIN_MSJ;
	}	

	/******************
	 * Mensajes juego *
	 ******************/
	public String mensajeJugar(String color, String posicion) {
		this.id++;
		return id + Constantes.ESPACIO + Constantes.PLAY + Constantes.ESPACIO + color + Constantes.ESPACIO + posicion + Constantes.FIN_MSJ;
	}	

	public String mensajeGenMovimiento(String color) {
		this.id++;
		return id + Constantes.ESPACIO + Constantes.GENMOVE + Constantes.ESPACIO + color + Constantes.FIN_MSJ;
	}
	
	/**********************
	 * Mensajes respuesta *
	 **********************/
	public String mensajeRespuestaOk(String id, ArrayList<String> lista) {
		String respuesta= Constantes.INICIO_MSJ_RTA;
		respuesta+= crearRespuesta(id, lista);
		return respuesta;
	}
	
	public String mensajeRespuestaError(String id, ArrayList<String> lista) {
		String respuesta= Constantes.INICIO_MSJ_RTA_ERROR;
		respuesta+= crearRespuesta(id, lista);
		return respuesta;
	}
	
	private String crearRespuesta(String id, ArrayList<String> lista) {
		String respuesta= "";
		respuesta+= agregarId(id);
		if(lista != null) {
			for(int i = 0; i < lista.size(); i++) {
				respuesta+= Constantes.ESPACIO + lista.get(i);
			}
			respuesta+= Constantes.FIN_MSJ_RTA;  
		}
		respuesta+= Constantes.FIN_MSJ_RTA;
		return respuesta;
	}

	private String agregarId(String id) {
		String idDigito= "";
		if(!id.equals("")) {
			try {
				Integer.parseInt(id);
				idDigito= id;
			} catch(NumberFormatException e) { }	
		}
		return idDigito;
	}
}
