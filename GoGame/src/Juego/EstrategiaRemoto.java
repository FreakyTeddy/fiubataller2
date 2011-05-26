package Juego;

import Remoto.Remoto;


public class EstrategiaRemoto implements Estrategia {

	private Remoto _remoto;
	private Posicion _ultimaPiedra;
	private String _color;

	public EstrategiaRemoto(Remoto remoto, ColorPiedra color){
		_remoto = remoto;
		if(color == ColorPiedra.BLANCO) //mejorar
			_color = "white";
		if(color == ColorPiedra.NEGRO)
			_color = "black";
		_ultimaPiedra = null;
	}
	
	public synchronized void setUltimaPiedra(Posicion p){
		_ultimaPiedra = p;
		this.notifyAll();
	}
	
	private synchronized Posicion getUltimaPiedra(){
		try {
			this.wait();
		} catch (InterruptedException e) {
		}
		return _ultimaPiedra;
	}
	
	@Override
	public Posicion getJugada() { 
		System.out.println(">Obteniendo jugada de remoto...");
		//Envio el mensaje de generar jugada al remoto
		//TODO: 1) enviar "genmove color_adversario" o "play mi_color"?
		//      2) antes de enviar pregunta si esta conectado
		//		3) informar la jugada del otro
		if(_remoto.hayRemoto())
			_remoto.enviarMensajeGenerarMovimiento(_color);
		//Espero respuesta 
		return getUltimaPiedra();
	}

}
