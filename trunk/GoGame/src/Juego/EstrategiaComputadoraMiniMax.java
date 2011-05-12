package Juego;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class EstrategiaComputadoraMiniMax extends EstrategiaComputadora {

	
	public EstrategiaComputadoraMiniMax(Tablero tablero, ColorPiedra color){
		super(tablero,color);
	}

	private class Jugada{
		Posicion posicion;
		boolean valida=false;
		boolean gana=false;
		boolean pierde=false;
		int cadenas=0;
		int gradosDeLibertad=0;
	}

	ColorPiedra getColorContrario(ColorPiedra color){
		return color==ColorPiedra.BLANCO?ColorPiedra.NEGRO:ColorPiedra.BLANCO;
	}

	Jugada evaluar(Tablero tablero){
		return new Jugada();
	}

	Jugada miniMax(ColorPiedra jugador, Tablero tablero, int profundidad){
		if(profundidad == 0){
			return evaluar(tablero);
		}
		Jugada jugada = new Jugada();

		LinkedList<Jugada> jugadas = new LinkedList<Jugada>();
		ArrayList<Posicion> disponibles = tablero.obtenerCasillerosLibres();

		for(Posicion posicion : disponibles) {
			Tablero proximoTablero = new Tablero(tablero);
			try{
				proximoTablero.agregarPiedra(posicion, jugador);
				jugada = miniMax(getColorContrario(jugador),proximoTablero, profundidad-1);
			}catch(JugadaInvalidaException e){
				jugada.valida = false;
			}
			catch(FinDelJuegoException e){
				jugada = new Jugada();
				jugada.gana = true;
			}
			jugadas.add(jugada);
		} 



		if(jugador == this.getColor()){
			//Maximizar
			
		}
		else{
			//Minimizar
		}
		return jugada;
		
	}

	protected Posicion generarJugada(){
		return this.estrategiaRandom();
	}
}