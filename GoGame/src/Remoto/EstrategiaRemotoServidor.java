package Remoto;

import Juego.ColorPiedra;
import Juego.FullMoonGo;

/**
 * El servidor contesta los genmove con la jugada local y 
 * recibe play con la jugada remota
 * 
 */

public class EstrategiaRemotoServidor extends EstrategiaRemoto {
	
	public EstrategiaRemotoServidor(ColorPiedra colorRemoto,ColorPiedra colorLocal) {
		super(colorRemoto, colorLocal);
	}

	@Override
	protected Remoto crearRemoto() {
		return new Servidor(this);
	}

	@Override
	protected synchronized void obtenerJugadaLocal() {
		ultimaPiedraLocal.add(FullMoonGo.getInstancia().getTablero().getUltimaJugada());
		finDeEspera();	//contesto el genmove	
	}
	
	@Override
	protected synchronized void obtenerJugadaRemota() {
		if(ultimaPiedraRemoto.isEmpty())
			esperar();	//espero que me llegue un play del cliente.
	}

}
