package controlador;

import Juego.ColorPiedra;
import Juego.FinDelJuegoException;
import Juego.Jugador;
import Juego.Tablero;

import vista.AppWindow;

public class FullMoonGo {

	private Jugador jugadorBlanco;
	private Jugador jugadorNegro;
	private Tablero tablero;
	private AppWindow vista;
	static private FullMoonGo instancia = null;
	
	static public FullMoonGo getInstancia(){
		if (instancia == null)
			instancia = new FullMoonGo();
		return instancia;
	}

	public FullMoonGo() {
		jugadorBlanco = null;
		jugadorNegro = null;
		tablero = null;
		vista = null;
	}
    
	public void nuevaPartida(){
		crearTablero();
		crearJugadores();
	}
	
	/**
	 * TODO ver desde donde crear los jugadores. Cambiar aca y poner la estrategia que se quiere
	 */
	public void crearJugadores() {
		AdaptadorTablero mouseListener = new AdaptadorTablero(vista.getVistaTablero());
		vista.getVistaTablero().addMouseListener(mouseListener);
		jugadorNegro = new Jugador("Fiubense 1", ColorPiedra.NEGRO, tablero, mouseListener);
		jugadorBlanco = new Jugador("Fiubense 2", ColorPiedra.BLANCO, tablero, mouseListener);
		
	}
	
	public void crearTablero(){
		tablero = new Tablero();
		vista = new AppWindow(tablero);
	}
	
	public Tablero getTablero(){
		return tablero;
	}
	
	public void jugar() {
		try {
			while(true){
				System.out.println("Turno " + jugadorNegro.getNombre());
				jugadorNegro.jugar();
				System.out.println("Turno " + jugadorBlanco.getNombre());
				jugadorBlanco.jugar();
			}
		}catch (FinDelJuegoException e){
			String nombreGanador="";	//esto despues tendria que ir a la vista :)
			if (e.getColorGanador() == ColorPiedra.NEGRO)
				nombreGanador = jugadorNegro.getNombre();
			if (e.getColorGanador() == ColorPiedra.BLANCO)
				nombreGanador = jugadorBlanco.getNombre();
			System.out.println("El ganador es: " + nombreGanador);
		}
	}
	
	public static void main(String[] args) {
		FullMoonGo.getInstancia().nuevaPartida();
		FullMoonGo.getInstancia().jugar();
	}

	
}

