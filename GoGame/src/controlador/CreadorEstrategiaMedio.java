package controlador;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.EstrategiaComputadoraAtaqueCuidadosoMasInteligente;

public class CreadorEstrategiaMedio implements CreadorEstrategia {

	@Override
	public Estrategia crearEstrategia(ColorPiedra color) {
		return new EstrategiaComputadoraAtaqueCuidadosoMasInteligente(color);
	}
}
