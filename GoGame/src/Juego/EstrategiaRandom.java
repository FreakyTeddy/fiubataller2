package Juego;

public class EstrategiaRandom implements Estrategia {

	@Override
	public Posicion getJugada() {
		return new Posicion((int)(Math.random()*19),(int) (Math.random()*19));
	}

	@Override
	public void informarJugadaInvalida() {}

}
