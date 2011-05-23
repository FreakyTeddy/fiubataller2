package Remoto;

import java.util.ArrayList;
import java.util.Observable;

import Remoto.GTP.ConstantesGtp;
import Remoto.GTP.Gtp;
import Remoto.GTP.ProcesadorMsjsEntrantes;
import Remoto.GTP.ProcesadorRespuestaObtenida;

public abstract class Remoto extends Observable {

	private ProcesadorMsjsEntrantes procesador;
	private ProcesadorRespuestaObtenida procesadorRta;
	private Gtp gtp; 
	private ArrayList<String> tipoUltimoMensaje;
	protected boolean conectado;
	//Datos de respuestas obtenidas
	private String posicionObtenida;
	
	public Remoto() {
		procesador= new ProcesadorMsjsEntrantes(this);
		procesadorRta= new ProcesadorRespuestaObtenida(this);
		gtp= new Gtp();
		tipoUltimoMensaje= new ArrayList<String>();
	}
	
	public abstract boolean iniciar(String ip, int puerto);
	
	public abstract void enviarMensaje(String mensaje);
	
	public void procesarMensajeEntrante(String mensaje) {
		String mensajeRta= procesador.procesarMensaje(mensaje);
		if(!mensajeRta.equals(""))           
				enviarMensaje(mensajeRta);
	}
	
	public String getTipoUltimoMensaje() {
		return tipoUltimoMensaje.get(0);
	}
	
	public void mensajeProcesado() {
		tipoUltimoMensaje.remove(0);
	}
	
	public void procesarRespuestaObtenida(String respuestaObtenida) {
		System.out.println("Respuesta obtenida: " + respuestaObtenida);
		procesadorRta.procesarRespuestaObtenida(respuestaObtenida);
	}

	/*Para modificar datos*/
	public void setPosicionObtenida(String posicionObtenida) {
		this.posicionObtenida= posicionObtenida;
		super.notifyObservers();
	}
	
	public abstract void terminar();
	
	public void terminarConexion() {
		conectado= false;
		terminar();
	}
	
	/*Datos obtenidos como respuesta*/
	public String getPosicionObtenida() {
		return posicionObtenida;
	}
	
	//TODO: hago los necesarios por ahora
	/*Mensaje posibles a enviar*/
	
	//Setup
	public void enviarMensajeTamanioTablero(int tamanio) {
		tipoUltimoMensaje.add(ConstantesGtp.BOARDSIZE);
		enviarMensaje(gtp.mensajeTamanioTablero(tamanio));	
	}
	//Juego
	public void enviarMensajeJugar(String color, String posicion) {
		tipoUltimoMensaje.add(ConstantesGtp.PLAY);
		enviarMensaje(gtp.mensajeJugar(color, posicion));	
	}
		
	public void enviarMensajeGenerarMovimiento(String color) {
		tipoUltimoMensaje.add(ConstantesGtp.GENMOVE);
		enviarMensaje(gtp.mensajeGenMovimiento(color));
	}
	//Salida
	public void enviarMensajeSalida() {
		tipoUltimoMensaje.add(ConstantesGtp.QUIT);
		enviarMensaje(gtp.mensajeSalir());
	}
	
	/********* Test *********/
	public abstract void terminarTest();
}
