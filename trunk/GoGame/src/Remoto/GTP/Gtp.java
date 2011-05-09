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
		return id + ConstantesGtp.ESPACIO + ConstantesGtp.PROTOCOL_VERSION + ConstantesGtp.FIN_MSJ;
	}
	
	public String mensajeSalir() {
		this.id++;
		return id + ConstantesGtp.ESPACIO + ConstantesGtp.QUIT + ConstantesGtp.FIN_MSJ;
	}

	/*******************************
	 * Identificacion del programa *
	 *******************************/
	
	public String mensajeNombre() {
		this.id++;
		return id + ConstantesGtp.ESPACIO + ConstantesGtp.NAME + ConstantesGtp.FIN_MSJ;
	}
	
	public String mensajeVersion() {
		this.id++;
		return id + ConstantesGtp.ESPACIO + ConstantesGtp.VERSION + ConstantesGtp.FIN_MSJ;
	}
	
	/*********************
	 * Mensajes comandos *
	 *********************/
	public String mensajeComandoSoportado(String comando) {
		this.id++;
		return id + ConstantesGtp.ESPACIO + ConstantesGtp.KNOWN_COMMAND + ConstantesGtp.ESPACIO + comando + ConstantesGtp.FIN_MSJ;
	}
	
	public String mensajeListarCommandos() {
		this.id++;
		return id + ConstantesGtp.ESPACIO + ConstantesGtp.LIST_COMMANDS + ConstantesGtp.FIN_MSJ;
	}
	
	/******************
	 * Mensajes setup *
	 ******************/
	public String mensajeTamanioTablero(int tamanio) {
		this.id++;
		return id + ConstantesGtp.ESPACIO + ConstantesGtp.BOARDSIZE + ConstantesGtp.ESPACIO + tamanio + ConstantesGtp.FIN_MSJ;
	}	
	
	public String mensajeLimpiarTablero() {
		this.id++;
		return id + ConstantesGtp.ESPACIO + ConstantesGtp.CLEAR_BOARD + ConstantesGtp.FIN_MSJ;
	}	
	
	public String mensajeKomi(double komi) {
		this.id++;
		return id + ConstantesGtp.ESPACIO + ConstantesGtp.KOMI + ConstantesGtp.ESPACIO + komi + ConstantesGtp.FIN_MSJ;
	}	

	/******************
	 * Mensajes juego *
	 ******************/
	public String mensajeJugar(String color, String posicion) {
		this.id++;
		return id + ConstantesGtp.ESPACIO + ConstantesGtp.PLAY + ConstantesGtp.ESPACIO + color + ConstantesGtp.ESPACIO + posicion + ConstantesGtp.FIN_MSJ;
	}	

	public String mensajeGenMovimiento(String color) {
		this.id++;
		return id + ConstantesGtp.ESPACIO + ConstantesGtp.GENMOVE + ConstantesGtp.ESPACIO + color + ConstantesGtp.FIN_MSJ;
	}
	
	/**********************
	 * Mensajes respuesta *
	 **********************/
	public String mensajeRespuestaOk(String id, ArrayList<String> lista) {
		String respuesta= ConstantesGtp.INICIO_MSJ_RTA;
		respuesta+= crearRespuesta(id, lista);
		return respuesta;
	}
	
	public String mensajeRespuestaError(String id, ArrayList<String> lista) {
		String respuesta= ConstantesGtp.INICIO_MSJ_RTA_ERROR;
		respuesta+= crearRespuesta(id, lista);
		return respuesta;
	}
	
	private String crearRespuesta(String id, ArrayList<String> lista) {
		String respuesta= "";
		respuesta+= agregarId(id);
		if(lista != null) {
			for(int i = 0; i < lista.size(); i++) {
				respuesta+= ConstantesGtp.ESPACIO + lista.get(i);
			}
			respuesta+= ConstantesGtp.FIN_MSJ_RTA;  
		}
		respuesta+= ConstantesGtp.FIN_MSJ_RTA;
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
