package Juego;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class EstrategiaComputadoraMiniMax extends EstrategiaComputadora {


	private double infinito = 99999;

	public EstrategiaComputadoraMiniMax(Tablero tablero, ColorPiedra color){
		super(tablero,color);
	}

	ColorPiedra getColorContrario(ColorPiedra color){
		return color==ColorPiedra.BLANCO?ColorPiedra.NEGRO:ColorPiedra.BLANCO;
	}

	class Jugada{
		Posicion posicion;
		double puntaje=0.0;
	}

	class OrdenadorJugada implements java.util.Comparator<Jugada> {

		public int compare(Jugada j1, Jugada j2) {
			if(j1.puntaje > j2.puntaje)
				return 1;
			if(j1.puntaje < j2.puntaje)
				return -1;
			return 0;
		}

	}

	double evaluar(ColorPiedra jugador, Tablero tablero){
		double puntaje=0;
		ArrayList<Cadena> cadenasPropias = obtenerCadenasPropias(tablero);
		ArrayList<Cadena> cadenasOponente = obtenerCadenasOponente(tablero);

		if(jugador == getColor()){
			Collections.sort(cadenasPropias, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());
			if(cadenasPropias.size()>0)
				if(cadenasPropias.get(0).getGradosDeLibertad() <= 1){
					//System.out.println("Pierdo en este turno.");
					return -infinito;
				}
		}
		else{
			Collections.sort(cadenasOponente, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());
			if(cadenasOponente.size()>0)
				if(cadenasOponente.get(0).getGradosDeLibertad() <= 1){
					//System.out.println("Puedo Ganar en este turno.");
					return infinito;
					
				}
		}

		if(cadenasPropias.size() > 0){
			//Uso de puntaje los grados de libertad de la cadena mas corta
			puntaje += cadenasPropias.get(0).getGradosDeLibertad();
		}
		if(cadenasOponente.size() > 0){
			//Uso de puntaje los grados de libertad de la cadena mas corta del oponente
			puntaje += cadenasOponente.get(0).getGradosDeLibertad();
		}
		
		return puntaje;
	}

	Jugada miniMax(ColorPiedra jugador, Tablero tablero, int profundidad){
		if(profundidad <= 0){
			Jugada j = new Jugada();
			j.puntaje=evaluar(getColorContrario(jugador), tablero);
			return j;
		}

		LinkedList<Jugada> jugadas = new LinkedList<Jugada>();

      		ArrayList<Posicion> disponibles = tablero.obtenerCasillerosLibres();

		for(Posicion posicion : disponibles) {
			Tablero proximoTablero = new Tablero(tablero);
			boolean valida=true;
			Jugada j=new Jugada();
			j.posicion = posicion;
			try {
				proximoTablero.agregarPiedra(posicion, jugador);
				j = miniMax(getColorContrario(jugador),proximoTablero, profundidad-1);
				j.posicion = posicion;
			}catch(JugadaInvalidaException e) {
				valida=false;
				j.puntaje=-infinito+1;
				System.out.println("Excepcion de invalidez de jugada");
			}
			catch(FinDelJuegoException e) {
				//System.out.println("Excepcion de fin del juego");
				if(jugador == this.getColor()){
					//Soy MAX y uno de los hijos evalúa a GANA, gano
					System.out.println("Despues de esta linea deberia ganar");
					j.puntaje=infinito;
					return j;
				}
				else{
					j.puntaje=-infinito;
				}
			}
			//System.out.println("Posicion: " + posicion.getX() + ","+ posicion.getY() + " : " + j.puntaje);
			if(valida) {
				jugadas.add(j);
				//System.out.println("Posicion: " + posicion.getX() + ","+ posicion.getY() + " : " + j.puntaje);
			}
		}
		
		if(jugador == this.getColor()){
			//Maximizar
			if(jugadas.size()==0){
				System.out.println("Pierdo haciendo cualquier cosa.");
				Jugada j = new Jugada();
				j.puntaje = -infinito;
				return j;
			}
			return Collections.max(jugadas, new OrdenadorJugada());
		}
		else{
			//Minimizar
			return Collections.min(jugadas, new OrdenadorJugada());
		}
	}

 
	protected Posicion generarJugada(){
		Jugada j = miniMax(getColor(), getTablero(), 3);
		System.out.println("Puntaje maximo: " + j.posicion.getX() + "," + j.posicion.getY() + " : " + j.puntaje);
		return j.posicion;
	}
}