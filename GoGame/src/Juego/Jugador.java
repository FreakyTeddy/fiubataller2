package Juego;

public class Jugador {

	private String nombre;
	private ColorPiedra color;
	private Estrategia estrategia;
	private boolean pasarTurno;
	
	public Jugador(String nombre, ColorPiedra color, Estrategia estrategia) {
		this.nombre = nombre;
		this.color = color;
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
	}
	
	/**
	 * Juega una piedra siguiendo su estrategia.
	 * 
	 */
	public void jugar() throws FinDelJuegoException {
		
		boolean jugadaValida = false;
		while (!jugadaValida){
			try {
				pasarTurno = false;
				Posicion posicion = estrategia.getJugada();
				if (posicion == null)
					pasarElTurno();
				
				FullMoonGo.getInstancia().getTablero().agregarPiedra(posicion, color);
				
				jugadaValida = true;
			}catch (JugadaInvalidaException ex){
				System.out.println(ex.getMessage());
			}
		}
	}
	
}
