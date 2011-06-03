package controlador;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.EstrategiaComputadoraMiniMaxReducido;

public class CreadorEstrategiaMinMaxReducido implements CreadorEstrategia {

	@Override
	public Estrategia crearEstrategia(ColorPiedra color) {
		return new EstrategiaComputadoraMiniMaxReducido(color);
	}

}
