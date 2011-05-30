package controlador;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.EstrategiaComputadoraAtacar;

public class CreadorEstrategiaMuyFacil implements CreadorEstrategia {

	@Override
	public Estrategia crearEstrategia( ColorPiedra color) {
		return new EstrategiaComputadoraAtacar(color);
	}
}
