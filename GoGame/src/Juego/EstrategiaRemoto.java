package Juego;

import Remoto.Remoto;


public class EstrategiaRemoto implements Estrategia {

	private Remoto _remoto;
	private Posicion _ultimaPiedra;
	private String _miColor;
	private String _contrarioColor;

	public EstrategiaRemoto(Remoto remoto, ColorPiedra micolor, ColorPiedra contrarioColor){
		_remoto = remoto;
		_miColor = traducirColor(micolor);
		_contrarioColor = traducirColor(contrarioColor);
		_ultimaPiedra = null;
	}
	
	private String traducirColor(ColorPiedra color) {
		if(color == ColorPiedra.BLANCO) 
			return new String("white");
		return new String("black");
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
		
		//Envio el mensaje de generar jugada al remoto
		//TODO: 1) enviar "genmove color_adversario" o "play mi_color"?
		//      2) antes de enviar pregunta si esta conectado
		//		3) informar la jugada del otro
		if(_remoto.hayRemoto()) {
			System.out.println(">Envio la jugada local");
			_remoto.enviarMensajeJugar(_contrarioColor, FullMoonGo.getInstancia().getTablero().getUltimaJugada().toString());
			System.out.println(">Obteniendo jugada de remoto...");
			_remoto.enviarMensajeGenerarMovimiento(_miColor);
		}
		//Espero respuesta 
		return getUltimaPiedra();
	}

}
