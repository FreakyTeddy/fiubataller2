package Juego;

import java.util.ArrayList;
import java.util.Observable;

import Juego.ColorPiedra;
import Juego.JugadaInvalidaException;
import Juego.FinDelJuegoException;

/**
 * Describe class <code>Tablero</code> here. El tablero es un observable.
 * 
 * @author <a href="mailto:lucas@lambda.slackware.org"></a>
 * @version 1.0
 */
public class Tablero extends Observable {

	private ColorPiedra casilleros[][];
	ArrayList<Cadena> cadenas;
	private int ancho;
	private boolean finDelJuego=false;
	
	private Posicion ultimaJugada=null;
	
	private void crearCasilleros() {
		casilleros = new ColorPiedra[ancho][ancho];
		cadenas = new ArrayList<Cadena>();
		for (int i = 0; i < ancho; i++)
			for (int j = 0; j < ancho; j++)
				casilleros[i][j] = ColorPiedra.VACIO;
	}

	public Tablero() {
		ancho = 9;
		crearCasilleros();
	}
	
	public Tablero(int tamanio) {
		ancho = tamanio;
		crearCasilleros();
	}
	
	public Tablero(Tablero otro){
		ancho = otro.ancho;
		casilleros = new ColorPiedra[ancho][ancho];
		for (int i = 0; i < ancho; i++)
			for (int j = 0; j < ancho; j++)
				casilleros[i][j] = otro.casilleros[i][j];

		cadenas = copiarCadenas(otro.cadenas, this);
	}
	
	private ArrayList<Cadena> copiarCadenas(ArrayList<Cadena> otrasCadenas, Tablero otroTablero){
		ArrayList<Cadena> nuevas = new ArrayList<Cadena>();
		
		for(Cadena cadena : otrasCadenas) {
			nuevas.add(new Cadena(cadena, otroTablero));
		} 

		return nuevas;
	}

	public int getAncho() {
		return ancho;
	}
	
	public Posicion getUltimaJugada(){
		return ultimaJugada;
	}

	private ColorPiedra getCasillero(int x, int y){
		return casilleros[y][x];
	}

	public ColorPiedra getCasillero(Posicion p) {
		if (p.getX() < 0 || p.getX() >= ancho)
			return ColorPiedra.INVALIDO;
		if (p.getY() < 0 || p.getY() >= ancho)
			return ColorPiedra.INVALIDO;

		return getCasillero(p.getX(), p.getY());
	}

	public void setCasillero(Posicion p, ColorPiedra color) {
		if (p.getX() < 0 || p.getX() >= ancho)
			return;
		if (p.getY() < 0 || p.getY() >= ancho)
			return;
		casilleros[p.getY()][p.getX()] = color;
	}

	private boolean estaOcupado(int x, int y){
		return getCasillero(x,y) != ColorPiedra.VACIO;
	}

	public boolean estaOcupado(Posicion posicion) {
		return getCasillero(posicion) != ColorPiedra.VACIO;
	}

	public void agregarPiedra(Posicion posicion, ColorPiedra color)
		throws JugadaInvalidaException,FinDelJuegoException {

		ultimaJugada = posicion;
		if(posicion != null)
			agregarPiedra(posicion.getX(), posicion.getY(), color);
		
		if(finDelJuego)
			throw new FinDelJuegoException(color);
	}

	private void agregarPiedra(int x, int y, ColorPiedra color)
		throws JugadaInvalidaException {
		intentarAgregarPiedra(x, y, color);

		// Lo agrego para la vista.
		setChanged();
		notifyObservers();

	}

	public ArrayList<Cadena> buscarCadenasAdyacentes(ArrayList<Cadena> cadenas, Posicion posicion,
			ColorPiedra color) {
		ArrayList<Cadena> adyacentes = new ArrayList<Cadena>();
		for (Cadena cadena : cadenas)
			if (cadena.getColor() == color && cadena.esAdyacente(posicion))
				adyacentes.add(cadena);

		return adyacentes;
	}

	/**
	 * Intenta agregar una piedra. Crea las nuevas cadenas que se formarian si
	 * fuera legal la jugada y se las pasa aplicarReglas para que verifique si
	 * la jugada es o no legal.
	 * 
	 * @param x
	 *            Coordenada x
	 * @param y
	 *            Coordenada y
	 * @param color
	 *            El color de la piedra
	 */
	void intentarAgregarPiedra(int x, int y, ColorPiedra color)
		throws JugadaInvalidaException {

		Posicion posicion = new Posicion(x, y);
		Cadena nuevaCadena = new Cadena(posicion, color, this);

		if (estaOcupado(posicion))
			throw new JugadaInvalidaException("El casillero ya esta ocupado");

		// Hago una copia de las cadenas actuales
		ArrayList<Cadena> todasLasCadenas = new ArrayList<Cadena>();
		for (Cadena c : cadenas) {
			Cadena nueva = new Cadena(c);
			todasLasCadenas.add(nueva);
		}

		ArrayList<Cadena> adyacentes = buscarCadenasAdyacentes(todasLasCadenas, posicion, color);

		if (adyacentes.size() > 0) {
			// Hay cadenas adyacentes las saco de la lista de cadenas
			for (Cadena adyacente : adyacentes)
				todasLasCadenas.remove(adyacente);

			// las uno
			adyacentes.add(nuevaCadena);
			Cadena cadenaResultante = Cadena.unirCadenas(adyacentes);

			// La cadena que se agrega en este turno es la union de
			// todas las adyacentes a la posicion
			nuevaCadena = cadenaResultante;

		}

		// Agrego la cadena creada en este turno
		todasLasCadenas.add(nuevaCadena);

		aplicarReglas(posicion, todasLasCadenas, color);
	}

	/**
	 * Aplica las reglas del juego al movimiento realizado. Si es un movimiento
	 * legal, actualiza el tablero y las cadenas. Si no deja todo como esta. No
	 * verifica KO (por ahora).
	 * 
	 * @param posicionJugada
	 *            La posicion jugada.
	 * @param cadenas
	 *            El estado de las cadenas si se realizara esta jugada.
	 * @param color
	 *            El color del movimiento.
	 */
	void aplicarReglas(Posicion posicionJugada, ArrayList<Cadena> cadenas,
			   ColorPiedra color) throws JugadaInvalidaException {
		
		ArrayList<Cadena> cadenasEliminadas = new ArrayList<Cadena>();

		// pongo la piedra
		setCasillero(posicionJugada, color);

		// Busco las cadenas eliminadas
		for (Cadena cadena : cadenas)
			if (!cadena.esLibre())
				cadenasEliminadas.add(cadena);

		boolean valida = false;


		if (cadenasEliminadas.size() == 0) {
			// Es jugada valida
			valida = true;
		} else {
			for (Cadena eliminada : cadenasEliminadas) {
				if (eliminada.getColor() != color) {
					finDelJuego = true;
					// Se elimina una cadena del otro color, la jugada es
					// valida
					valida = true;
					// La saco de la lista de cadenas definitivas
					cadenas.remove(eliminada);
					for(Posicion aBorrar : eliminada.getPosiciones())
						this.setCasillero(aBorrar, ColorPiedra.VACIO);
				}
			}
		}

		if (valida) {
			// Se elimino una cadena del adversario
			this.cadenas = cadenas; // Actualizo las cadenas
		} else {
			// Jugada invalida. Dejo las cadenas como estan pero
			// revierto la jugada.
			setCasillero(posicionJugada, ColorPiedra.VACIO);
			throw new JugadaInvalidaException("No es valido suicidarse");
		}
	}

	public ArrayList<Cadena> obtenerCadenas(){
		return cadenas;
	}

	public ArrayList<Posicion> obtenerCasillerosLibres(){
		ArrayList<Posicion> libres = new ArrayList<Posicion>();
		
		for(int j=0; j<ancho; j++)
			for(int i=0; i<ancho; i++)
				if(!estaOcupado(i,j))
					libres.add(new Posicion(i,j));
		return libres;
	}
}