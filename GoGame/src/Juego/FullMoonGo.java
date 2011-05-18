package Juego;

import java.util.Observable;

import controlador.AdaptadorTablero;

import vista.VentanaAplicacion;
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
public class FullMoonGo extends Observable{

	private EstadoJuego estadoJuego;
	private Jugador jugadorBlanco;
	private Jugador jugadorNegro;
	private Tablero tablero;
	private VentanaAplicacion vista;
	private boolean jugarContraPersona;
	static private FullMoonGo instancia = null;
	
	static public FullMoonGo getInstancia(){
		if (instancia == null)
			instancia = new FullMoonGo();
		return instancia;
	}

	public FullMoonGo() {
		estadoJuego = NO_INICIADO;
		jugadorBlanco = null;
		jugadorNegro = null;
		tablero = null;
		vista = null;
		jugarContraPersona = false;
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
		if (jugarContraPersona)
			jugadorBlanco = new Jugador("Fiubense 2", ColorPiedra.BLANCO, tablero, mouseListener);
		else
			jugadorBlanco = new Jugador("Fiubense 2", ColorPiedra.BLANCO, tablero, new EstrategiaComputadoraAtaqueCuidadoso(tablero, ColorPiedra.BLANCO));
	}
	
	public void crearTablero(){
		tablero = new Tablero();
		vista = new VentanaAplicacion(this);
	}
	
	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
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
	
	public static void main(String[] args) {
		FullMoonGo.getInstancia().nuevaPartida();
		FullMoonGo.getInstancia().jugar();
	}


	public void jugarVsPersona(boolean bool) {
		jugarContraPersona = bool;
		estadoJuego = LISTO_PARA_INICIAR;
		setChanged();
		notifyObservers();
	}
	
	public EstadoJuego getEstado(){
		return estadoJuego;
	}

	
}

