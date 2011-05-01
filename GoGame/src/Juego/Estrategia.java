package Juego;


/**
 * Define la estrategia de la jugada. 
 * 
 * Las estrategias pueden ser, por ej: Remoto, Computadora, Humano.
 * 
 * @author luuu
 *
 */

public interface Estrategia {

	/**
	 * 
	 * @return Posicion elegida para jugar la proxima piedra.
	 */
	public Posicion getJugada();
	
}
