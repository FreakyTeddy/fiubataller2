package Juego;

public class FullMoonGo {

	private Jugador jugadorBlanco;
	private Jugador jugadorNegro;
	private Tablero tablero;
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
	}
    
	public void nuevaPartida(){
		crearTablero();
		crearJugadores();
	}
	
	/**
	 * TODO ver como crear los jugadores
	 */
	public void crearJugadores() {
		jugadorNegro = new Jugador("Fiubense", ColorPiedra.NEGRO, tablero, new EstrategiaHumano());
		jugadorBlanco = new Jugador("Random", ColorPiedra.BLANCO, tablero, new EstrategiaRandom());
		
	}
	
	public void crearTablero(){
		tablero = new Tablero();
	}
	
	public Tablero getTablero(){
		return tablero;
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

