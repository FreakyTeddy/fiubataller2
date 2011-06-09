package Remoto;

import Juego.ColorPiedra;
import Juego.FullMoonGo;

/**
 * El cliente envia un play con la jugada local y
 * un genmove para obtener la jugada remota
 * 
 * */

public class EstrategiaRemotoCliente extends EstrategiaRemoto {

	public EstrategiaRemotoCliente(ColorPiedra colorRemoto, ColorPiedra colorLocal) {
		super(colorRemoto, colorLocal);
	}

	@Override
	protected Remoto crearRemoto() {
		return new Cliente(this);
	}

	@Override
	protected synchronized void obtenerJugadaLocal() {
		remoto.enviarMensajeJugar(colorLocal, Remoto.traducirPosicion(FullMoonGo.getInstancia().getTablero().getUltimaJugada()));
	}
	
	@Override
	protected synchronized void obtenerJugadaRemota() {
		remoto.enviarMensajeGenerarMovimiento(colorRemoto);
		if(ultimaPiedraRemoto.isEmpty()) {
			System.out.println(">Obteniendo jugada del servidor...");	
			esperar();
			System.out.println(">Jugada del servidor obtenida");	
		}
	}

	@Override
	protected void enviarUltimaJugada() {
		System.out.println("*** enviar ultima ficha ****");
		obtenerJugadaLocal(); 
		System.out.println("*** enviar quit ****");
		remoto.enviarMensajeSalida();		
	}

}
