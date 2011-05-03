package Juego;

public class EstrategiaRandom implements Estrategia {

	@Override
	public Posicion getJugada() {  //TODO! be careful! falla mucho cuando el tablero esta casi lleno
		return new Posicion((int)(Math.random()*19),(int) (Math.random()*19));
	}

}
