package Juego;

import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaComputadoraDefender extends EstrategiaComputadora{
	
	public EstrategiaComputadoraDefender(ColorPiedra color) {
		super(color);
	}
	
	/**
	 * 
	 * Intenta ocupar casilleros adycentes a las cadenas con menor grado
	 * de libertad propias, intentando evitar que sean capturadas.
	 * @return La posicion donde jugar
	 */
	private Posicion estrategiaDefender(Tablero tablero){

		ArrayList<Cadena> cadenas = obtenerCadenasPropias(tablero);

		Collections.sort(cadenas, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());

		Posicion posicion = estrategiaRandom(tablero);

		if(cadenas.size() > 0)
			posicion = cadenas.get(0).getCasillerosLibresAdyacentes().get(0);

		return posicion;
	}

	protected Posicion generarJugada(Tablero tablero){
		return estrategiaDefender(tablero);
	}
}