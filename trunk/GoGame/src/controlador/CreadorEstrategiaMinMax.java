package controlador;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.EstrategiaComputadoraMiniMax;
import Juego.Tablero;

public class CreadorEstrategiaMinMax implements CreadorEstrategia {

	@Override
	public Estrategia crearEstrategia(Tablero tablero, ColorPiedra color) {
		return new EstrategiaComputadoraMiniMax(tablero, color);
	}

}
