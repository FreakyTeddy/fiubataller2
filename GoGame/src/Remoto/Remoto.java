package Remoto;

import java.util.ArrayList;

import Juego.EstrategiaRemoto;
import Juego.Posicion;
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
	protected boolean conectado;
	
	public Remoto(EstrategiaRemoto estrategiaRemoto) {
		this.estrategiaRemoto= estrategiaRemoto;
		procesador= new ProcesadorMsjsEntrantes(this);
		procesadorRta= new ProcesadorRespuestaObtenida(this);
		gtp= new Gtp();
		tipoUltimoMensaje= new ArrayList<String>();
	}
	
	public abstract boolean iniciar(String ip, int puerto);
	
	protected abstract void enviarMensaje(String mensaje);
	
	public void procesarMensajeEntrante(String mensaje) {
		String mensajeRta= procesador.procesarMensaje(mensaje);
		if(!mensajeRta.equals(""))           
				enviarMensaje(mensajeRta);
	}
	
	public boolean estaConectado() {
		return conectado;
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

	public abstract void terminar();
	
	public void terminarConexion() {
		//TODO: avisar del fin de la conexion
		//conectado= false;
		//terminar();
	}
	
	/***PARA MODIFICAR EN ESTRATEGIA REMOTO***/
	public void setPosicionObtenida(String posicionObtenida) {
		if(estrategiaRemoto != null)
			estrategiaRemoto.setUltimaPiedra(new Posicion(posicionObtenida));
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
