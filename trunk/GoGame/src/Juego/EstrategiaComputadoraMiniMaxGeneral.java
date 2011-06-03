package Juego;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public abstract class EstrategiaComputadoraMiniMaxGeneral extends EstrategiaComputadora {


	private double infinito = 99999;

	public EstrategiaComputadoraMiniMaxGeneral(ColorPiedra color){
		super(color);
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

	abstract Collection<Posicion> obtenerCasillerosCandidatos(Tablero tablero);

	double evaluar(ColorPiedra jugador, Tablero tablero){
		double puntaje=0;
		ArrayList<Cadena> cadenasPropias = obtenerCadenasPropias(tablero);
		ArrayList<Cadena> cadenasOponente = obtenerCadenasOponente(tablero);

		if(jugador == getColor()){
			Collections.sort(cadenasPropias, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());
			if(cadenasPropias.size()>0)
				if(cadenasPropias.get(0).getGradosDeLibertad() <= 1){
					return -infinito;
				}
		}
		else{
			Collections.sort(cadenasOponente, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());
			if(cadenasOponente.size()>0)
				if(cadenasOponente.get(0).getGradosDeLibertad() <= 1){
					return infinito;
				}
		}

		if(cadenasPropias.size() > 0){
			HashSet<Posicion> gradosDeLibertad = new HashSet<Posicion>();
			int ojos=0;
			for(Cadena cadena : cadenasPropias) {
				for(Posicion posicion : cadena.getCasillerosLibresAdyacentes()) {
					gradosDeLibertad.add(posicion);
				}
				ojos += cadena.getOjos().size();
			}

			//Uso de puntaje los grados de libertad de la cadena mas corta
			puntaje += cadenasPropias.get(0).getGradosDeLibertad()*4;
			puntaje += gradosDeLibertad.size();
			puntaje += ojos * 3;
			
		}
		if(cadenasOponente.size() > 0){
			HashSet<Posicion> gradosDeLibertad = new HashSet<Posicion>();
			int ojos=0;
			for(Cadena cadena : cadenasOponente) {
				for(Posicion posicion : cadena.getCasillerosLibresAdyacentes()) {
					gradosDeLibertad.add(posicion);
				}
				ojos += cadena.getOjos().size();
			}
			//Uso de puntaje los grados de libertad de la cadena mas corta del oponente
			puntaje += cadenasOponente.get(0).getGradosDeLibertad();
			//Resto los grados de libertad de la cadena mas larga del oponente
			puntaje -= cadenasOponente.get(cadenasOponente.size()-1).getGradosDeLibertad();
			//Resto el total de los grados de libertad del oponente
			puntaje -= gradosDeLibertad.size()*1.5;

			puntaje -= ojos * 2;
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


		Collection<Posicion> disponibles = obtenerCasillerosCandidatos(tablero);

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
			}
			catch(FinDelJuegoException e) {
				if(jugador == this.getColor()){
					//Soy MAX y uno de los hijos evalúa a GANA, gano
					j.puntaje=infinito;
					return j;
				}
				else{
					j.puntaje=-infinito;
				}
			}
			if(valida) {
				jugadas.add(j);
			}
		}
		
		if(jugador == this.getColor()){
			//Maximizar
			if(jugadas.size()==0){
				System.out.println("Pierdo haciendo cualquier cosa.");
				Jugada j = new Jugada();
				j.puntaje = -infinito;
				j.posicion = null;
				return j;
			}
			return Collections.max(jugadas, new OrdenadorJugada());
		}
		else{
			//Minimizar
			return Collections.min(jugadas, new OrdenadorJugada());
		}
	}

 
	protected Posicion generarJugada(Tablero tablero){
		int profundidad = 3; //TODO puede ser variable no??
		System.out.println("Uso profundidad: " + profundidad);
		Jugada j = miniMax(getColor(), tablero, profundidad);
		System.out.println("Puntaje maximo: " + j.posicion.getX() + "," + j.posicion.getY() + " : " + j.puntaje);
		return j.posicion;
	}
}
