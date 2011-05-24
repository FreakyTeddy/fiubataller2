package controlador;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.EstrategiaComputadoraDefender;
import Juego.Tablero;

public class CreadorEstrategiaMuyFacil implements CreadorEstrategia {

	@Override
	public Estrategia crearEstrategia(Tablero tablero, ColorPiedra color) {
		return new EstrategiaComputadoraDefender(tablero, color);
	}
}
