package Juego;

public class EstrategiaRandom extends EstrategiaComputadora {

	public EstrategiaRandom(ColorPiedra color) {
		super(color);
	}

	@Override
	protected Posicion generarJugada(Tablero tablero) {
		return estrategiaRandom(tablero);
	}

}
