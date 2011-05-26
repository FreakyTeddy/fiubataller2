package Juego;

public class Jugador {

	private String nombre;
	private ColorPiedra color;
	private Tablero tablero;
	private Estrategia estrategia;
	private boolean pasarTurno;
	
	public Jugador(String nombre, ColorPiedra color,Tablero tablero, Estrategia estrategia) {
		this.nombre = nombre;
		this.color = color;
		this.tablero = tablero;
		this.estrategia = estrategia;
		this.pasarTurno = false;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public ColorPiedra getColor() {
		return color;
	}
	
	public boolean pasoElTurno() {
		return pasarTurno;
	}
		
	private void pasarElTurno() {
		pasarTurno = true;
		System.out.println(nombre + " pasó el turno. Ya no juega");
	}
	
	/**
	 * Juega una piedra siguiendo su estrategia.
	 * 
	 */
	public void jugar() throws FinDelJuegoException {
		
		boolean jugadaValida = false;
		while (!pasarTurno && !jugadaValida){
			try {
				Posicion posicion = estrategia.getJugada();
				if (posicion == null)
					pasarElTurno();
				else {
					tablero.agregarPiedra(posicion, color);
					jugadaValida = true;
				}				
			}catch (JugadaInvalidaException ex){
				System.out.println(nombre + ": " + ex);
			}
		}
	}
	
}
