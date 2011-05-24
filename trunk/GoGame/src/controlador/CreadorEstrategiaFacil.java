package controlador;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.EstrategiaComputadoraAtaqueCuidadoso;
import Juego.Tablero;

public class CreadorEstrategiaFacil implements CreadorEstrategia {

	@Override
	public Estrategia crearEstrategia(Tablero tablero, ColorPiedra color) {
		return new EstrategiaComputadoraAtaqueCuidadoso(tablero, color);
	}
	
}
