package Remoto;

import java.util.ArrayList;

import Juego.Posicion;
import Remoto.GTP.ConstantesGtp;
import Remoto.GTP.Gtp;
import Remoto.GTP.ProcesadorMsjsEntrantes;
import Remoto.GTP.ProcesadorRespuestaObtenida;

public abstract class Remoto {

	private Arbitro estrategiaRemoto;
	private ProcesadorMsjsEntrantes procesador;
	private ProcesadorRespuestaObtenida procesadorRta;
	private Gtp gtp; 
	private ArrayList<String> tipoUltimoMensaje;
	
	public Remoto(Arbitro estrategia) {
		estrategiaRemoto= estrategia;
		procesador= new ProcesadorMsjsEntrantes(this);
		procesadorRta= new ProcesadorRespuestaObtenida(this);
		gtp= new Gtp();
		tipoUltimoMensaje= new ArrayList<String>();
	}
	
	public abstract boolean iniciar(String ip, int puerto);
	
	public abstract boolean hayRemoto();
	
	protected abstract void enviarMensaje(String mensaje);
	
	public Arbitro getArbitro() {
		return estrategiaRemoto;
	}
	
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
		procesadorRta.procesarRespuestaObtenida(respuestaObtenida);
	}

	public abstract void terminarHilo();
	
	protected abstract void terminar();
	
	public void terminarConexion() {
		terminar();
	}
	
	/****************************
	 * Traduccion de posiciones	*
	 ****************************/
	
	public static String traducirPosicion(Posicion pos) {
		if(pos == null)
			return ConstantesGtp.PASAR_TURNO;
		return pos.toString();
	}
	
	public static Posicion traducirPosicion(String pos) {
		if (pos.equalsIgnoreCase(ConstantesGtp.PASAR_TURNO))
			return null;
		return new Posicion(pos);
	}
	
	
	//Se implementan solo los necesarios
	/***Mensajes para enviar al remoto***/
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
