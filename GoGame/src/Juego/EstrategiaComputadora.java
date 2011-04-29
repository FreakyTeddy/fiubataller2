package Juego;

import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaComputadora implements Estrategia {

	Tablero _tablero;
	
	ColorPiedra miColor = ColorPiedra.NEGRO;
	
	public EstrategiaComputadora(Tablero tablero){
		_tablero = tablero;
	}
	
	
	@Override
	public Posicion getJugada() {
		return generarJugada();
	}

	@Override
	public void informarJugadaInvalida() {}

	/**
	 * Comparador que ordena cadenas segun sus grados de libertad y
	 * longitud.  Las menores son aquellas que tienen menor grado de
	 * libertad. A igual grado de libertad, la menor es la mas grande.
	 */
	private class ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud implements java.util.Comparator<Cadena> {

		public int compare(Cadena cadena1, Cadena cadena2) {

			int gradosCadena1 = cadena1.getGradosDeLibertad();
			int gradosCadena2 = cadena2.getGradosDeLibertad();

			if(gradosCadena1 < gradosCadena2)
				return -1;
			if(gradosCadena1 > gradosCadena2)
				return 1;
			
			if(cadena1.getTamanio() >= cadena2.getTamanio())
				return -1;
			return 1;
		}
	}

	private ArrayList<Cadena> filtrarCadenas(ArrayList<Cadena> cadenas, ColorPiedra color){
		ArrayList<Cadena> cadenasResultantes = new ArrayList<Cadena>();

		for(Cadena cadena : cadenas) 
			if(cadena.getColor() != color)
				cadenasResultantes.add(cadena);
		return cadenasResultantes;
	}

	private ArrayList<Cadena> obtenerCadenasOponente(){

		return filtrarCadenas(_tablero.obtenerCadenas(),miColor);
	}

	private ArrayList<Cadena> obtenerCadenasPropias(){

		return filtrarCadenas(_tablero.obtenerCadenas(),miColor==ColorPiedra.BLANCO?ColorPiedra.NEGRO:ColorPiedra.BLANCO);
	}

	//TODO: Despues hay que separar las estrategias y nombrarlas como corresponde
	/**
	 * Intenta ocupar casilleros adyacentes de las cadenas con menor grado
	 * de libertad del oponente, intentando capturarlas.
	 * 
	 * @return La posicion donde jugar
	 */
	private Posicion estrategia1(){
		
		ArrayList<Cadena> cadenas = obtenerCadenasOponente();

		Collections.sort(cadenas, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());

		Posicion posicion = new Posicion(0,0);

		if(cadenas.size() > 0)
			posicion = cadenas.get(0).getCasillerosLibresAdyacentes().get(0);
		return posicion;
	}

	/**
	 * 
	 * Intenta ocupar casilleros adycentes a las cadenas con menor grado
	 * de libertad propias, intentando evitar que sean capturadas.
	 * @return La posicion donde jugar
	 */
	private Posicion estrategia2(){
		
		ArrayList<Cadena> cadenas = obtenerCadenasPropias();

		Collections.sort(cadenas, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());

		Posicion posicion = new Posicion(0,0);

		if(cadenas.size() > 0)
			posicion = cadenas.get(0).getCasillerosLibresAdyacentes().get(0);

		return posicion;
	}

	private Posicion generarJugada(){

		return  estrategia1();
	}
	
}
