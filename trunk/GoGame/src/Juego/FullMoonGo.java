package Juego;

import java.util.Observable;

import controlador.AdaptadorTablero;

import vista.VentanaAplicacionGo;
import static Juego.EstadoJuego.*;


/**
 * 
 * TODO: Habria que ver un poco el tema de como se seleccionan los jugadores
 *  yo diria que como esta ahora es bastante villero pero bueno aplique la idea 
 *  de primero lo hacemo' depue' lo refatorizamo'. Posible refactor un state. 
 *  Otra cosa que si habria que hacer es pasar esto a juego porque ahora es 
 *  observable yh tiene logica del juego digamos.
 * 
 * @author del comentario de arriba matias
 *
 */
public class FullMoonGo extends Observable implements Runnable {
	
	private EstadoJuego estadoJuego;
	private Jugador jugadorBlanco;
	private Jugador jugadorNegro;
	private Tablero tablero;
	private boolean jugarContraPersona;
	static private FullMoonGo instancia = null;
	
	static public FullMoonGo getInstancia(){
		if (instancia == null)
			instancia = new FullMoonGo();
		return instancia;
	}

	private FullMoonGo() {
		estadoJuego = NO_INICIADO;
		jugadorBlanco = null;
		jugadorNegro = null;
		tablero = null;
		crearTablero();
	}
    
	public void crearTablero(){
		tablero = new Tablero();
	}
	
	public void crearTablero(TamanioTablero tam) {
		tablero = new Tablero(tam);
	}
	
	public void run(){
//		if (estadoJuego == LISTO_PARA_INICIAR) {
//			vista.mostrarTablero(tablero);
//			jugar();
//		} else {
//			System.out.println("falta configurar algo"); //TODO
//		}
		jugar();
	}
	
	public Tablero getTablero(){
		return tablero;
	}
	
	public EstadoJuego getEstado(){
		return estadoJuego;
	}

	public void jugarContraPersona(boolean b) {
		jugarContraPersona = b;		
		run();
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
		return nuevoJugador;
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
			setChanged();
			notifyObservers();
			estadoJuego = TERMINADO;
			String nombreGanador="";	//esto despues tendria que ir a la vista :)
			if (e.getColorGanador() == ColorPiedra.NEGRO)
				nombreGanador = jugadorNegro.getNombre();
			if (e.getColorGanador() == ColorPiedra.BLANCO)
				nombreGanador = jugadorBlanco.getNombre();
			System.out.println("El ganador es: " + nombreGanador);
		}	
	}
			
}

