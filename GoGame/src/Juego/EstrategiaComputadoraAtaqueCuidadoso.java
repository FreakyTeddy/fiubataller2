package Juego;

import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaComputadoraAtaqueCuidadoso extends EstrategiaComputadora{

	private EstrategiaComputadoraAtacar atacar;
	private EstrategiaComputadoraDefender defender;

	public EstrategiaComputadoraAtaqueCuidadoso(ColorPiedra color) {
		super(color);
		atacar = new EstrategiaComputadoraAtacar(color);
		defender = new EstrategiaComputadoraDefender(color);
	}

	protected Posicion generarJugada(Tablero tablero){
		ArrayList<Cadena> cadenas = obtenerCadenasPropias(tablero);

		Collections.sort(cadenas, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());
		
		if(cadenas.size()>0 && cadenas.get(0).getGradosDeLibertad() <= 2)
			return defender.generarJugada(tablero);
		return atacar.generarJugada(tablero);
	}

}