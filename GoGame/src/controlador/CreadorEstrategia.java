package controlador;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.Tablero;

public interface CreadorEstrategia {

	public Estrategia crearEstrategia(Tablero tablero, ColorPiedra color);
	
}
