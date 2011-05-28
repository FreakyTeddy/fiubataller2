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
		
		ultimaPiedraLocal.add(FullMoonGo.getInstancia().getTablero().getUltimaJugada());
		finDeEspera();	//contesto el genmove
		System.out.println("__notifico__");
		
		
		esperar(); //espero que me llegue un play del cliente. warning! el cliente puede empezar antes que yo :S
		//ahora es el turno del tipo
	}

}
