package Juego;

import java.util.Observable;

import static Juego.EstadoJuego.*;



public class FullMoonGo extends Observable implements Runnable {
	
	private EstadoJuego estadoJuego;
	
	private Jugador jugadorBlanco;
	private Jugador jugadorNegro;
	private Jugador jugadorGanador;
	private Jugador jugadorDeTurno;
	
	private Tablero tablero;
	private String msjFinDeJuego;
	
	private static final FullMoonGo instancia = new FullMoonGo();
	
	private FullMoonGo() {
		reiniciarEstado();
	}
	
	private void checkearEstado() {
		if((jugadorBlanco != null) && (jugadorNegro != null) && (tablero != null)) {
			estadoJuego = EstadoJuego.LISTO_PARA_INICIAR;
			setChanged();
			notifyObservers();
		}	
	}
    
	public static FullMoonGo getInstancia() {
		return instancia;
	}
	
	public void crearTablero(int tamanio) {
		tablero = new Tablero(tamanio);
		checkearEstado();
	}
	
	public Tablero getTablero(){
		return tablero;
	}
	
	public EstadoJuego getEstado(){
		return estadoJuego;
	}
	
	public Jugador getGanador() {
		return jugadorGanador;
	}
	
	public Jugador getJugadorDeTurno() {
		return jugadorDeTurno;
	}
	
	public String getNombreBlanco() {
		return jugadorBlanco.getNombre();
	}
	
	public String getNombreNegro() {
		return jugadorNegro.getNombre();
	}
	
	public String getMsjFinDeJuego() {
		return msjFinDeJuego;
	}
	
	public void reiniciarEstado() {
		estadoJuego = NO_INICIADO;
		jugadorBlanco = null;
		jugadorNegro = null;
		jugadorGanador = null;
		tablero = null;
		msjFinDeJuego = "";
	}

	public Jugador crearJugador(String nombre, ColorPiedra color, Estrategia estrategia) {
		Jugador nuevoJugador = new Jugador(nombre, color, estrategia);
		if (color == ColorPiedra.NEGRO) {
			jugadorNegro = nuevoJugador;
		}
		if (color == ColorPiedra.BLANCO) {
			jugadorBlanco = nuevoJugador;
		}
		if (color == ColorPiedra.VACIO) {
			nuevoJugador = null;
		}
		checkearEstado();
		return nuevoJugador;
	}
	/**
	 * Inicia una partida si esta todo configurado como para empezar a jugar 
	 */
	public void run(){
		if (estadoJuego == LISTO_PARA_INICIAR) 
			jugar();
		 else 
			notifyObservers();
	}			
	
	/**
	 * Inicia una partida. 
	 * La partida finaliza si alguno de los dos jugadores gana, abandona
	 * o si ambos jugadores pasan el turno consecutivamente.
	 */
	public void jugar() {
		
		jugadorDeTurno = jugadorNegro;
		Jugador jugadorAnterior = jugadorBlanco;
		Jugador temp;
		estadoJuego = EstadoJuego.INICIADO;
		
		try {
			do{
				setChanged();
				notifyObservers();
				
				jugadorDeTurno.jugar();
				
				temp = jugadorDeTurno;
				jugadorDeTurno = jugadorAnterior;
				jugadorAnterior = temp;
				
			}while( !jugadorDeTurno.pasoElTurno() || !jugadorAnterior.pasoElTurno());
			
			msjFinDeJuego = "Ambos jugadores pasaron el turno";
			
		}catch (FinDelJuegoException e){
			msjFinDeJuego = e.getMessage();
			setGanador(e.getColorGanador());	
		}
		setFinDeJuego();
	}
	
	private void setGanador(ColorPiedra color) {
		if (color == ColorPiedra.NEGRO) 
			jugadorGanador = jugadorNegro;
		if (color == ColorPiedra.BLANCO)
			jugadorGanador = jugadorBlanco;	
	}
	
	private void setFinDeJuego() {
		estadoJuego = TERMINADO;
		setChanged();
		notifyObservers();	
	}
			
}
