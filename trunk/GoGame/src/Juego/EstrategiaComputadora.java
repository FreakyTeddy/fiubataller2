package Juego;

import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaComputadora implements Estrategia {

	Tablero _tablero;
	
	
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


	private Posicion generarJugada(){

		ArrayList<Cadena> cadenas = filtrarCadenas(_tablero.obtenerCadenas(),ColorPiedra.NEGRO);

		Collections.sort(cadenas, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());

		Posicion posicion = new Posicion(0,0);

		if(cadenas.size() > 0)
			posicion = cadenas.get(0).getCasillerosLibresAdyacentes().get(0);

		return  posicion;
	}
	
}
