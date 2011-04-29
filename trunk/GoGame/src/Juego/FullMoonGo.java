package Juego;
import vista.AppWindow;

public class FullMoonGo {

	private Jugador jugadorBlanco;
	private Jugador jugadorNegro;
	private Tablero tablero;
	
	public static void main(String[] args) {
		FullMoonGo juego = new FullMoonGo();
		juego.nuevaPartida();
	}

	public FullMoonGo() {
		jugadorBlanco = null;
		jugadorNegro = null;
		tablero = null;
	}
    
	public void nuevaPartida(){
		tablero = new Tablero();
		new AppWindow(tablero);
	}
	
	/**
	 * TODO ver como crear los jugadores
	 */
	public void crearJugadores() {
		jugadorNegro = new Jugador("Fiubense", ColorPiedra.NEGRO, tablero, new EstrategiaHumano());
		jugadorBlanco = new Jugador("Random", ColorPiedra.BLANCO, tablero, new EstrategiaRandom());
		
	}
	
	public void jugar() {
		try {
			while(true){
				jugadorNegro.jugar();
				jugadorBlanco.jugar();
			}
		}catch (FinDelJuegoException e){
			System.out.println(e);
		}
	}
	
}

