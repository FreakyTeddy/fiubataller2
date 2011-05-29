package Remoto;

import java.util.ArrayList;

import Juego.EstrategiaRemoto;
import Remoto.GTP.ConstantesGtp;
import Remoto.GTP.Gtp;
import Remoto.GTP.ProcesadorMsjsEntrantes;
import Remoto.GTP.ProcesadorRespuestaObtenida;

public abstract class Remoto {

	private EstrategiaRemoto estrategiaRemoto;
	private ProcesadorMsjsEntrantes procesador;
	private ProcesadorRespuestaObtenida procesadorRta;
	private Gtp gtp; 
	private ArrayList<String> tipoUltimoMensaje;
	
	public Remoto(EstrategiaRemoto estrategia) {
		estrategiaRemoto= estrategia;
		procesador= new ProcesadorMsjsEntrantes(this);
		procesadorRta= new ProcesadorRespuestaObtenida(this);
		gtp= new Gtp();
		tipoUltimoMensaje= new ArrayList<String>();
	}
	
	public abstract boolean iniciar(String ip, int puerto);
	
	public abstract boolean hayRemoto();
	
	protected abstract void enviarMensaje(String mensaje);
	
	public EstrategiaRemoto getArbitro() {
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
	
	//TODO: hago los necesarios por ahora
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
