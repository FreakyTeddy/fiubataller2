package controlador;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.EstrategiaComputadoraMiniMax;

public class CreadorEstrategiaMinMax implements CreadorEstrategia {

	@Override
	public Estrategia crearEstrategia(ColorPiedra color) {
		return new EstrategiaComputadoraMiniMax(color);
	}

}
