package Juego;

import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaComputadoraAtacar extends EstrategiaComputadora{
	
	public EstrategiaComputadoraAtacar(ColorPiedra color) {
		super(color);
	}

	/**
	 * Intenta ocupar casilleros adyacentes de las cadenas con menor grado
	 * de libertad del oponente, intentando capturarlas.
	 * 
	 * @return La posicion donde jugar
	 */
	private Posicion estrategiaAtacar(Tablero tablero){
		ArrayList<Cadena> cadenas = obtenerCadenasOponente(tablero);

		Collections.sort(cadenas, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());

		Posicion posicion = estrategiaRandom(tablero);

		if(cadenas.size() > 0)
			posicion = cadenas.get(0).getCasillerosLibresAdyacentes().get(0);
		return posicion;
	}

	protected Posicion generarJugada(Tablero tablero){
		return estrategiaAtacar(tablero);
	}

}