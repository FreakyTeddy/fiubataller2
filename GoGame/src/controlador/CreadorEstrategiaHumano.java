package controlador;

import vista.VentanaAplicacionGo;
import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.Tablero;

public class CreadorEstrategiaHumano implements CreadorEstrategia {

	private static final AdaptadorTablero mouseListener = new AdaptadorTablero();
	
	public CreadorEstrategiaHumano(VentanaAplicacionGo vista) {
		vista.addMouseListener(mouseListener);
	}
	
	@Override
	public Estrategia crearEstrategia(Tablero tablero, ColorPiedra color) {
		return mouseListener;
	}

}
