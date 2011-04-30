package Juego;

import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaComputadoraAtaqueCuidadoso extends EstrategiaComputadora{

	EstrategiaComputadoraAtacar atacar;
	EstrategiaComputadoraDefender defender;

	public EstrategiaComputadoraAtaqueCuidadoso(Tablero tablero, ColorPiedra color) {
		super(tablero, color);
		atacar = new EstrategiaComputadoraAtacar(tablero, color);
		defender = new EstrategiaComputadoraDefender(tablero, color);
	}

	protected Posicion generarJugada(){
		ArrayList<Cadena> cadenas = obtenerCadenasPropias();

		Collections.sort(cadenas, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());
		
		if(cadenas.size()>0 && cadenas.get(0).getGradosDeLibertad() <= 2)
			return defender.generarJugada();
		return atacar.generarJugada();
	}

}