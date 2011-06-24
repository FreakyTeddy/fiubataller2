package Juego;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class EstrategiaComputadoraAtaqueCuidadosoExperimental extends EstrategiaComputadora{

	public EstrategiaComputadoraAtaqueCuidadosoExperimental(ColorPiedra color) {
		super(color);
	}

	private class Jugada{
		Posicion posicion;
		boolean pierde=false;
		int cadenas=0;
		int gradosDeLibertad=0;
		boolean valida=false;
	}

	/**
	 * Comparador que ordena cadenas segun sus grados de libertad y
	 * longitud.  Las menores son aquellas que tienen menor grado de
	 * libertad. A igual grado de libertad, la menor es la mas grande.
	 */
	class ordenadorJugadaPorMenorCadenasMayorLibertadYNoPierde implements java.util.Comparator<Jugada> {

		public int compare(Jugada jugada1, Jugada jugada2){

			if(jugada1.pierde && jugada2.pierde)
				return 0;

			if(jugada1.pierde)
				return 1;
			if(jugada2.pierde)
				return -1;

			if(jugada1.gradosDeLibertad > jugada2.gradosDeLibertad)
				return -1;
			if(jugada1.gradosDeLibertad < jugada2.gradosDeLibertad)
				return 1;
			
			if(jugada1.cadenas < jugada2.cadenas)
			 	return -1;
			if(jugada1.cadenas > jugada2.cadenas)
			 	return 1;

			return 0;
		}
	 }


	protected Posicion generarJugada(Tablero tablero){

		//Primero verifico si puedo ganar
		ArrayList<Cadena> cadenasOponente = obtenerCadenasOponente(tablero);
		Collections.sort(cadenasOponente, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());

		//Ganar
		if(cadenasOponente.size() > 0 && cadenasOponente.get(0).getGradosDeLibertad() == 1)
			return new EstrategiaComputadoraAtacar(getColor()).generarJugada(tablero);

		
		//Busco todas las adyacencias
		HashSet<Posicion> todasLasAdyacendias = new HashSet<Posicion>();

		// for(Cadena cadena : obtenerCadenasPropias(tablero)) 
		// 	for(Posicion adyacente : cadena.getCasillerosLibresAdyacentes()) 
		// 		todasLasAdyacendias.add(adyacente);

		// for(Cadena cadena : obtenerCadenasOponente(tablero)) 
		// 	for(Posicion adyacente : cadena.getCasillerosLibresAdyacentes()) 
		// 		todasLasAdyacendias.add(adyacente);

		todasLasAdyacendias.addAll(tablero.obtenerCasillerosLibres());

		ArrayList<Jugada> candidatas = new ArrayList<Jugada>();
		for(Posicion adyacencia : todasLasAdyacendias) {

			Tablero copiaTablero = new Tablero(tablero);
			Jugada j = calcularPuntajeJugada(tablero,copiaTablero, adyacencia);			
			if(j.valida){
				candidatas.add(j);
			}
		} 
		
		Collections.sort(candidatas, new ordenadorJugadaPorMenorCadenasMayorLibertadYNoPierde());
		if(candidatas.size()>0)
			return candidatas.get(0).posicion;
		else{
			return estrategiaRandom(tablero);
		}
	}

	private Jugada calcularPuntajeJugada(Tablero tableroOriginal, Tablero tableroCopia, Posicion posicion){
		Jugada jugada = new Jugada();
		jugada.posicion = posicion;
		jugada.valida=false;
		try{
			tableroCopia.agregarPiedra(posicion, getColor());

			ArrayList<Cadena> cadenasPropias = obtenerCadenasPropias(tableroCopia);

			jugada.cadenas = cadenasPropias.size();

			//Busco todas las adyacencias
			HashSet<Posicion> todasLasAdyacendiasPropias = new HashSet<Posicion>();
			for(Cadena cadena : obtenerCadenasPropias(tableroOriginal)) 
				for(Posicion adyacente : cadena.getCasillerosLibresAdyacentes()) 
					todasLasAdyacendiasPropias.add(adyacente);

			jugada.gradosDeLibertad = todasLasAdyacendiasPropias.size();


			Collections.sort(cadenasPropias, new ordenadorCadenasPorMenorGradoDeLibertadYMayorLongitud());
			if(cadenasPropias.size() > 0 && cadenasPropias.get(0).getGradosDeLibertad() <= 2) //Si voy a perder
				jugada.pierde = true;
			else jugada.valida=true;
		}
		catch(JugadaInvalidaException e){}
		catch(FinDelJuegoException e){}
		return jugada;
	}

}


