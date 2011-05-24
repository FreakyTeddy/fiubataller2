package Juego;

import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaComputadoraAtacar extends EstrategiaComputadora{
	
	public EstrategiaComputadoraAtacar(Tablero tablero, ColorPiedra color) {
		super(tablero,color);
	}

	/**
	 * Intenta ocupar casilleros adyacentes de las cadenas con menor grado
	 * de libertad del oponente, intentando capturarlas.
	 * 
	 * @return La posicion donde jugar
	 */
	private Posicion estrategiaAtacar(){
		
		ArrayList<Cadena> cadenas = obtenerCadenasOponente(getTablero());

		Collections.sort(cadenas, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());

		Posicion posicion = estrategiaRandom();

		if(cadenas.size() > 0)
			posicion = cadenas.get(0).getCasillerosLibresAdyacentes().get(0);
		return posicion;
	}

	protected Posicion generarJugada(){
		return estrategiaAtacar();
	}

}