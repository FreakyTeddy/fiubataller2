package Juego;

import Remoto.Mensajero;

public class EstrategiaRemoto implements Estrategia {

	
	Tablero _tablero;
	Mensajero _mensajero;
	
	public EstrategiaRemoto(Tablero tablero, Mensajero mensajero){
		_tablero = tablero;
		_mensajero = mensajero;
	}
	
	@Override
	public Posicion getJugada() {
		return _mensajero.recibirJugada();
	}

	@Override
	public void informarJugadaInvalida() {
		_mensajero.informarJugadaInvalida();		
	}

}
