package Juego;

import java.util.ArrayList;
import java.util.Collections;


/**
 * 
 * Determina la posicion en la que jugar a traves de un algoritmo
 *
 */
public abstract class EstrategiaComputadora implements Estrategia {

	ColorPiedra miColor = ColorPiedra.NEGRO;
	
	public EstrategiaComputadora(ColorPiedra color){
		setColor(color);
	}

	@Override
	public Posicion getJugada() {
		Tablero tablero = FullMoonGo.getInstancia().getTablero();
		Posicion p = generarJugada(tablero);
		if(esJugadaValida(p, tablero))
			return p;
		return this.primeraJugadaValida(tablero);
	}

	public void setColor(ColorPiedra color) {
		miColor = color;
	}

	public ColorPiedra getColor(){
		return miColor;
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

	 ArrayList<Cadena> obtenerCadenasOponente(Tablero tablero){
		 return filtrarCadenas(tablero.obtenerCadenas(),miColor);
	 }

	ArrayList<Cadena> obtenerCadenasPropias(Tablero tablero){

		return filtrarCadenas(tablero.obtenerCadenas(),miColor==ColorPiedra.BLANCO?ColorPiedra.NEGRO:ColorPiedra.BLANCO);
	}

	Posicion estrategiaRandom(Tablero tablero){
		ArrayList<Posicion> libres = tablero.obtenerCasillerosLibres();
		Collections.shuffle(libres);
		return libres.get(1);
	}

	boolean esJugadaValida(Posicion posicion, Tablero tablero){
		Tablero tableroPrueba = new Tablero(tablero);
		try{
			tableroPrueba.agregarPiedra(posicion, getColor());
			return true;
		}
		catch(JugadaInvalidaException e){
			return false;
		}
		catch(FinDelJuegoException e){
			return true;
		}
	}

	
	/**
	 * Busca en el tablero la primer posición donde es valido poner una ficha.
	 * @return Una posicion donde es valido jugar. Null si no hay mas posiciones validas.
	 */
	Posicion primeraJugadaValida(Tablero tablero){
		Tablero tableroPrueba = new Tablero(tablero);
		ArrayList<Posicion> posiciones = tableroPrueba.obtenerCasillerosLibres();

		for(Posicion posicion : posiciones) {
			try{
				tableroPrueba.agregarPiedra(posicion, getColor());
				return posicion;
			}catch(JugadaInvalidaException e){

			}catch(FinDelJuegoException e){
				return posicion;
			}

		} 
		return null;
	}

	protected abstract Posicion generarJugada(Tablero tablero);
}
