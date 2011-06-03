package controlador;

import vista.VentanaAplicacionGo;
import Juego.ColorPiedra;
import Juego.Estrategia;

public class CreadorEstrategiaHumano implements CreadorEstrategia {

	private static final EstrategiaHumano mouseListener = new EstrategiaHumano();
	
	public CreadorEstrategiaHumano(VentanaAplicacionGo vista) {
		vista.addMouseListener(mouseListener);
	}
	
	@Override
	public Estrategia crearEstrategia(ColorPiedra color) {
		return mouseListener;
	}

}
