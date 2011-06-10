package Juego;

@SuppressWarnings("serial")
public class FinDelJuegoException extends RuntimeException {

	private ColorPiedra ganador = ColorPiedra.VACIO;
	
	public FinDelJuegoException(ColorPiedra colorGanador) {
		super("");
		ganador = colorGanador;
	}
	
	public FinDelJuegoException(ColorPiedra colorGanador, String msj) {
		super(msj);
		ganador = colorGanador;
	}
	
	public ColorPiedra getColorGanador(){
		return ganador;
	}
}
