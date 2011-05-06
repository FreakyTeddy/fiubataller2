package Juego;

import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaComputadoraAtaqueCuidadosoMasInteligente extends EstrategiaComputadora{

	EstrategiaComputadoraAtacar atacar;
	EstrategiaComputadoraDefender defender;

	public EstrategiaComputadoraAtaqueCuidadosoMasInteligente(Tablero tablero, ColorPiedra color) {
		super(tablero, color);
		atacar = new EstrategiaComputadoraAtacar(tablero, color);
		defender = new EstrategiaComputadoraDefender(tablero, color);
	}

	protected Posicion generarJugada(){
		ArrayList<Cadena> cadenasPropias = obtenerCadenasPropias(getTablero());
		ArrayList<Cadena> cadenasOponente = obtenerCadenasOponente(getTablero());

		
		Collections.sort(cadenasOponente, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());
		Collections.sort(cadenasPropias, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());

		if(cadenasOponente.size() > 0 && cadenasOponente.get(0).getGradosDeLibertad() == 1)
			return atacar.generarJugada();

		if(cadenasPropias.size()>0 && cadenasPropias.get(0).getGradosDeLibertad() <= 2)
			return defender.generarJugada();
		
		return atacar.generarJugada();
	}

}