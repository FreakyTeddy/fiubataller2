package Juego;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class EstrategiaComputadoraMiniMaxReducido extends EstrategiaComputadoraMiniMaxGeneral {


	public EstrategiaComputadoraMiniMaxReducido(ColorPiedra color){
		super(color);
	}

	Collection<Posicion> obtenerCasillerosCandidatos(Tablero tablero){
		HashSet<Posicion> disponibles = new HashSet<Posicion>();
		for(Cadena cadena : tablero.obtenerCadenas()) {
			disponibles.addAll(cadena.getCasillerosLibresAdyacentes());
		}
		if(disponibles.size() == 0){
			ArrayList<Posicion> pos = tablero.obtenerCasillerosLibres();
			Collections.shuffle(pos);
			return pos;
		}
				
		return disponibles;
	}
}