package Juego;

public class Jugador {

	private String _nombre;
	private ColorPiedra _color;
	private Tablero _tablero;
	private Estrategia _estrategia;
	
	public Jugador(String nombre, ColorPiedra color, Tablero tablero){
		_nombre = nombre;
		_color = color;
		_tablero = tablero;
	}
	
	public Jugador(String nombre, ColorPiedra color,Tablero tablero, Estrategia estrategia){
		_nombre = nombre;
		_color = color;
		_tablero = tablero;
		_estrategia = estrategia;
	}
	
	public String getNombre(){
		return _nombre;
	}
	
	public ColorPiedra getColor(){
		return _color;
	}
	
	/**
	 * Juega una piedra en la posicion indicada
	 * 
	 * @param posicion: ficha a jugar
	 * @throws JugadaInvalidaException si la posicion es invalida
	 * 
	 */
	public void jugar(Posicion posicion) throws JugadaInvalidaException {
		
		_tablero.agregarPiedra(posicion, _color);
	}
	
	/**
	 * Juega una piedra siguiendo su estrategia.
	 * 
	 */
	public void jugar() {
		
		boolean valida = false;
		while (!valida){
			try {
				Posicion posicion = _estrategia.getJugada();
				_tablero.agregarPiedra(posicion.getX(), posicion.getY(), _color);
				valida = true;
			}catch (JugadaInvalidaException ex){
				_estrategia.informarJugadaInvalida();
				System.out.println(ex);
			}
		}
	}
	
}
