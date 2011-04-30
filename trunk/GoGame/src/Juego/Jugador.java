package Juego;

public class Jugador {

	private String _nombre;
	private ColorPiedra _color;
	private Tablero _tablero;
	private Estrategia _estrategia;
	
	public Jugador(String nombre, ColorPiedra color,Tablero tablero, Estrategia estrategia) {
		_nombre = nombre;
		_color = color;
		_tablero = tablero;
		_estrategia = estrategia;
	}
	
	public String getNombre() {
		return _nombre;
	}
	
	public ColorPiedra getColor() {
		return _color;
	}
	
	public Estrategia getEstrategia() {
		return _estrategia;
	}
	
	/**
	 * Juega una piedra siguiendo su estrategia.
	 * 
	 */
	public void jugar() throws FinDelJuegoException {
		
		boolean valida = false;
		while (!valida){
			try {
				Posicion posicion = _estrategia.getJugada();
				_tablero.agregarPiedra(posicion, _color);
				valida = true;
			}catch (JugadaInvalidaException ex){
				_estrategia.informarJugadaInvalida();
				System.out.println(ex);
			}
		}
	}
	
}
