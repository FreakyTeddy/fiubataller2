package Juego;

import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaComputadoraAtaqueCuidadosoMasInteligente extends EstrategiaComputadora{

	EstrategiaComputadoraAtacar atacar;
	EstrategiaComputadoraDefender defender;

	public EstrategiaComputadoraAtaqueCuidadosoMasInteligente(ColorPiedra color) {
		super(color);
		atacar = new EstrategiaComputadoraAtacar(color);
		defender = new EstrategiaComputadoraDefender(color);
	}

	protected Posicion generarJugada(Tablero tablero){
		ArrayList<Cadena> cadenasPropias = obtenerCadenasPropias(tablero);
		ArrayList<Cadena> cadenasOponente = obtenerCadenasOponente(tablero);

		
		Collections.sort(cadenasOponente, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());
		Collections.sort(cadenasPropias, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());

		if(cadenasOponente.size() > 0 && cadenasOponente.get(0).getGradosDeLibertad() == 1)
			return atacar.generarJugada(tablero);

		if(cadenasPropias.size()>0 && cadenasPropias.get(0).getGradosDeLibertad() <= 2)
			return defender.generarJugada(tablero);
		
		return atacar.generarJugada(tablero);
	}

}