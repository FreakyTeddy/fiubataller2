package Juego;

import java.util.ArrayList;
import java.util.Collections;

public abstract class EstrategiaComputadora implements Estrategia {

	Tablero _tablero;
	
	ColorPiedra miColor = ColorPiedra.NEGRO;
	
	public EstrategiaComputadora(Tablero tablero, ColorPiedra color){
		_tablero = tablero;
		setColor(color);
	}

	@Override
	public Posicion getJugada() {
		return generarJugada();
	}

	@Override
	public void informarJugadaInvalida() {}


	public void setColor(ColorPiedra color) {
		miColor = color;
	}

	/**
	 * Comparador que ordena cadenas segun sus grados de libertad y
	 * longitud.  Las menores son aquellas que tienen menor grado de
	 * libertad. A igual grado de libertad, la menor es la mas grande.
	 */
	class ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud implements java.util.Comparator<Cadena> {

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

	 ArrayList<Cadena> filtrarCadenas(ArrayList<Cadena> cadenas, ColorPiedra color){
		 ArrayList<Cadena> cadenasResultantes = new ArrayList<Cadena>();

		 for(Cadena cadena : cadenas) 
			 if(cadena.getColor() != color)
				 cadenasResultantes.add(cadena);
		 return cadenasResultantes;
	 }

	 ArrayList<Cadena> obtenerCadenasOponente(){

		 return filtrarCadenas(_tablero.obtenerCadenas(),miColor);
	 }

	ArrayList<Cadena> obtenerCadenasPropias(){

		return filtrarCadenas(_tablero.obtenerCadenas(),miColor==ColorPiedra.BLANCO?ColorPiedra.NEGRO:ColorPiedra.BLANCO);
	}

	Posicion estrategiaRandom(){
		ArrayList<Posicion> libres = _tablero.obtenerCasillerosLibres();
		Collections.shuffle(libres);
		return libres.get(1);
	}

	protected abstract Posicion generarJugada();
}
