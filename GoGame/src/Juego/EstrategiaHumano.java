package Juego;

public class EstrategiaHumano implements Estrategia{

	Posicion _proximaJugada;
	
	
	public void setProximaJugada(Posicion pos){
		_proximaJugada = pos;
	}
	
	@Override
	public Posicion getJugada() {
		return _proximaJugada;
	}

	@Override
	public void informarJugadaInvalida() {
		// TODO Auto-generated method stub
		
	}

}
