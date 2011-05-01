package Remoto.GTP;

public class GTP {

	//Datos programa
	private static String NRO_PROTOCOL_VERSION="1";
	private static String PROGRAM_NAME="GNU Go";
	private static String PROGRAM_VERSION="1";
	
	int id;
	
	public GTP() {
		this.id= 0;
	}
	
	/****************************
	 * Mensajes administrativos *
	 ****************************/
	
	public String mensajeVersionProtocolo() {
		this.id++;
		return id + " " + Constantes.PROTOCOL_VERSION + " " + NRO_PROTOCOL_VERSION + "\n";
	}
	
	public String mensajeSalir() {
		this.id++;
		return id + " " + Constantes.QUIT + "\n";
	}

	/*******************************
	 * Identificacion del programa *
	 *******************************/
	
	public String mensajeNombre() {
		this.id++;
		return id + " " + Constantes.NAME + " " + PROGRAM_NAME + "\n";
	}
	
	public String mensajeVersion() {
		this.id++;
		return id + " " + Constantes.VERSION + " " + PROGRAM_VERSION + "\n";
	}
	
	/*********************
	 * Mensajes comandos *
	 *********************/
	public String mensajeComandoSoportado(String comando) {
		this.id++;
		return id + " " + Constantes.KNOWN_COMMAND + " " + comando + "\n";
	}
	
	public String mensajeListarCommandos() {
		this.id++;
		return id + " " + Constantes.LIST_COMMANDS + "\n";
	}
	
	/******************
	 * Mensajes setup *
	 ******************/
	public String mensajeTamanioTablero(int tamanio) {
		this.id++;
		return id + " " + Constantes.BOARDSIZE + " " + tamanio + "\n";
	}	
	
	public String mensajeLimpiarTablero() {
		this.id++;
		return id + " " + Constantes.CLEAR_BOARD + "\n";
	}	
	
	public String mensajeKomi(double komi) {
		this.id++;
		return id + " " + Constantes.KOMI + " " + komi + "\n";
	}	

	/******************
	 * Mensajes juego *
	 ******************/
	public String mensajeJugar(String color, String posicion) {
		this.id++;
		return id + " " + Constantes.PLAY + " " + color + " " + posicion + "\n";
	}	

	public String mensajeGenMovimiento(String color) {
		this.id++;
		return id + " " + Constantes.GENMOVE + " " + color + "\n";
	}	
}
