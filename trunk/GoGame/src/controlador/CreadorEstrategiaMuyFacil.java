package controlador;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.EstrategiaComputadoraAtacar;
import Juego.Tablero;

public class CreadorEstrategiaMuyFacil implements CreadorEstrategia {

	@Override
	public Estrategia crearEstrategia(Tablero tablero, ColorPiedra color) {
		return new EstrategiaComputadoraAtacar(tablero, color);
	}
}
