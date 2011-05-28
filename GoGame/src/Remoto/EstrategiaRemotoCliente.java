package Remoto;

import Juego.ColorPiedra;
import Juego.EstrategiaRemoto;
import Juego.FullMoonGo;

public class EstrategiaRemotoCliente extends EstrategiaRemoto {

	public EstrategiaRemotoCliente(ColorPiedra colorRemoto, ColorPiedra colorLocal) {
		super(colorRemoto, colorLocal);
	}

	@Override
	protected Remoto crearRemoto() {
		return new Cliente(this);
	}

	/**
	 * El cliente envia un play con la jugada local y un genmove al servidor
	 * 
	 * TODO asegurarme de los msj que llegan me corresponden
	 * */
	@Override
	protected void intercambiarJugadas() {
		if(remoto.hayRemoto()) {
			System.out.println(">Envio la jugada local");
			remoto.enviarMensajeJugar(colorLocal, FullMoonGo.getInstancia().getTablero().getUltimaJugada().toString());			
			remoto.enviarMensajeGenerarMovimiento(colorRemoto);
			if(ultimaPiedraRemoto.isEmpty()) {
				esperar();
				System.out.println(">Obteniendo jugada de remoto...");	
			}
		}
		
	}

}
