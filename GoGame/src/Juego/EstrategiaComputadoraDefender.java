package Juego;

import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaComputadoraDefender extends EstrategiaComputadora{
	
	public EstrategiaComputadoraDefender(Tablero tablero, ColorPiedra color) {
		super(tablero, color);
	}
	
	/**
	 * 
	 * Intenta ocupar casilleros adycentes a las cadenas con menor grado
	 * de libertad propias, intentando evitar que sean capturadas.
	 * @return La posicion donde jugar
	 */
	private Posicion estrategiaDefender(){

		ArrayList<Cadena> cadenas = obtenerCadenasPropias();

		Collections.sort(cadenas, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());

		Posicion posicion = estrategiaRandom();

		if(cadenas.size() > 0)
			posicion = cadenas.get(0).getCasillerosLibresAdyacentes().get(0);

		return posicion;
	}

	protected Posicion generarJugada(){
		return estrategiaDefender();
	}
}