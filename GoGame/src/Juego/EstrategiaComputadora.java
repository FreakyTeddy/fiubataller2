package Juego;

public class EstrategiaComputadora implements Estrategia {

	Tablero _tablero;
	
	
	public EstrategiaComputadora(Tablero tablero){
		_tablero = tablero;
	}
	
	private Posicion generarJugada(){
		return new Posicion(0,0); //TODO 
	}
	
	@Override
	public Posicion getJugada() {
		
		return generarJugada();
	}

	@Override
	public void informarJugadaInvalida() {}
	
}
