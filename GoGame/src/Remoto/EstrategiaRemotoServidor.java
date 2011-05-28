package Remoto;

import Juego.ColorPiedra;
import Juego.EstrategiaRemoto;
import Juego.FullMoonGo;

public class EstrategiaRemotoServidor extends EstrategiaRemoto {

	public EstrategiaRemotoServidor(ColorPiedra colorRemoto,ColorPiedra colorLocal) {
		super(colorRemoto, colorLocal);
	}

	@Override
	protected Remoto crearRemoto() {
		return new Servidor(this);
	}

	@Override
	protected void intercambiarJugadas() {
		ultimaPiedraLocal = FullMoonGo.getInstancia().getTablero().getUltimaJugada();
		notificarRespuesta();	//contesto el genmove
		esperarRespuesta(); //espero que me llegue un play del cliente	
	}

}
