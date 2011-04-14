package Juego;

public abstract class Jugador {

	protected String nombre;
	protected ColorPiedra color;
	protected Tablero tablero;
	
	public String getNombre(){
		return nombre;
	}
	
	public ColorPiedra getColor(){
		return color;
	}
	
	//Algo asi tiene que hacer =P
	public abstract void jugar();
	
}
