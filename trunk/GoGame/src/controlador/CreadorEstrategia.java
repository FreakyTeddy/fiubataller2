package controlador;

import Juego.ColorPiedra;
import Juego.Estrategia;

public interface CreadorEstrategia {

	public Estrategia crearEstrategia(ColorPiedra color);
	
}
