package controlador;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.EstrategiaComputadoraAtaqueCuidadoso;

public class CreadorEstrategiaFacil implements CreadorEstrategia {

	@Override
	public Estrategia crearEstrategia(ColorPiedra color) {
		return new EstrategiaComputadoraAtaqueCuidadoso(color);
	}
	
}
