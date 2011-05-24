package controlador;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.EstrategiaComputadoraAtaqueCuidadosoMasInteligente;
import Juego.Tablero;

public class CreadorEstrategiaMedio implements CreadorEstrategia {

	@Override
	public Estrategia crearEstrategia(Tablero tablero, ColorPiedra color) {
		return new EstrategiaComputadoraAtaqueCuidadosoMasInteligente(tablero, color);
	}
}
