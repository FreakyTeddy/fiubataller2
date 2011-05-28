package Juego;

import java.util.Observable;

import static Juego.EstadoJuego.*;


/**
 * 
 * TODO: Posible refactor un state. 
 * 
 * @author del comentario de arriba matias
 *
 */
public class FullMoonGo extends Observable implements Runnable {
	
	private EstadoJuego estadoJuego;
	private Jugador jugadorBlanco;
	private Jugador jugadorNegro;
	private Jugador jugadorGanador;
	private Tablero tablero;
	private static final FullMoonGo instancia = new FullMoonGo();
	
	private FullMoonGo() {
		reiniciarEstado();
	}
    
	public static FullMoonGo getInstancia() {
		return instancia;
	}
	
	public void crearTablero(){
		tablero = new Tablero();
	}
	
	public void crearTablero(TamanioTablero tam) {
		tablero = new Tablero(tam);
	}
	
	public void run(){
		if (estadoJuego == LISTO_PARA_INICIAR) 
			jugar();
		 else 
			System.out.println("falta configurar algo"); //TODO
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
	
	public void reiniciarEstado() {
		estadoJuego = NO_INICIADO;
		jugadorBlanco = null;
		jugadorNegro = null;
		jugadorGanador = null;
		tablero = null;
		crearTablero();
	}

	public Jugador crearJugador(String nombre, ColorPiedra color, Estrategia estrategia) {
		Jugador nuevoJugador = new Jugador(nombre, color, tablero, estrategia);
		if (color == ColorPiedra.NEGRO) {
			jugadorNegro = nuevoJugador;
		}
		if (color == ColorPiedra.BLANCO) {
			jugadorBlanco = nuevoJugador;
		}
		if (color == ColorPiedra.VACIO) {
			nuevoJugador = null;
		}
		if((jugadorBlanco != null) && (jugadorNegro != null))
			estadoJuego = EstadoJuego.LISTO_PARA_INICIAR;	
		return nuevoJugador;
	}
	
	public void jugar() {
		
		estadoJuego = EstadoJuego.INICIADO;
		try {
			do {

				System.out.println("Turno " + jugadorNegro.getNombre());
				jugadorNegro.jugar();

				System.out.println("Turno " + jugadorBlanco.getNombre());
				jugadorBlanco.jugar();

			} while (!jugadorBlanco.pasoElTurno() || !jugadorNegro.pasoElTurno());
			
		}catch (FinDelJuegoException e){
			if (e.getColorGanador() == ColorPiedra.NEGRO) 
				jugadorGanador = jugadorNegro;
			if (e.getColorGanador() == ColorPiedra.BLANCO)
				jugadorGanador = jugadorBlanco;	
		}finally{
			System.out.println("*****************+fin***********");
			estadoJuego = TERMINADO;
			setChanged();
			notifyObservers();		
		}	
	}
			
}
