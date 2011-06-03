package Juego;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class EstrategiaComputadoraMiniMax extends EstrategiaComputadoraMiniMaxGeneral {

	public EstrategiaComputadoraMiniMax(ColorPiedra color){
		super(color);
	}

	Collection<Posicion> obtenerCasillerosCandidatos(Tablero tablero){
		ArrayList<Posicion> disponibles = tablero.obtenerCasillerosLibres();
		Collections.shuffle(disponibles);
		return disponibles;
	}
}
