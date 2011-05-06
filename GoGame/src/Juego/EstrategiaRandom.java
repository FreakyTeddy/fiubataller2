package Juego;

public class EstrategiaRandom extends EstrategiaComputadora {

	public EstrategiaRandom(Tablero tablero, ColorPiedra color) {
		super(tablero, color);
	}

	@Override
	public Posicion getJugada() {  
		return estrategiaRandom();
	}

	@Override
	protected Posicion generarJugada() {
		return null;
	}

}
