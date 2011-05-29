package Remoto;

import Juego.ColorPiedra;
import Juego.EstrategiaRemoto;
import Juego.FinDelJuegoException;
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
	protected void obtenerJugadaLocal() {
		if(remoto.hayRemoto()) {
			remoto.enviarMensajeJugar(colorLocal, FullMoonGo.getInstancia().getTablero().getUltimaJugada().toString());
		}else{
			System.out.println("______-no hay conexion ___________");
			throw new FinDelJuegoException(ColorPiedra.VACIO);
		}
	}
	
	@Override
	protected void obtenerJugadaRemota() {
		if(remoto.hayRemoto()) {
			remoto.enviarMensajeGenerarMovimiento(colorRemoto);
			if(ultimaPiedraRemoto.isEmpty()) {
				esperar();
				System.out.println(">Obteniendo jugada de remoto...");	
			}
		}else{
			System.out.println("______-no hay conexion ___________");
			throw new FinDelJuegoException(ColorPiedra.VACIO);
		}
	}

}
