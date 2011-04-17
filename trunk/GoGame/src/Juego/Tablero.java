package Juego;

import java.util.ArrayList;
import java.util.Observable;

import Juego.ColorPiedra;
import Juego.JugadaInvalidaException;

/**
 * Describe class <code>Tablero</code> here. El tablero es un observable.
 * 
 * @author <a href="mailto:lucas@lambda.slackware.org"></a>
 * @version 1.0
 */
public class Tablero extends Observable {

	ColorPiedra casilleros[][];
	ArrayList<Cadena> cadenas;
	int ancho;

	public Tablero() {
		ancho = 9;
		casilleros = new ColorPiedra[ancho][ancho];
		cadenas = new ArrayList<Cadena>();
		for (int i = 0; i < ancho; i++)
			for (int j = 0; j < ancho; j++)
				casilleros[i][j] = ColorPiedra.VACIO;

	}

	public int getAncho() {
		return ancho;
	}

	public ColorPiedra getCasillero(Posicion p) {
		if (p.getX() < 0 || p.getX() >= ancho)
			return ColorPiedra.VACIO;
		if (p.getY() < 0 || p.getY() >= ancho)
			return ColorPiedra.VACIO;

		return casilleros[p.getY()][p.getX()];
	}

	public void setCasillero(Posicion p, ColorPiedra color) {
		if (p.getX() < 0 || p.getX() >= ancho)
			return;
		if (p.getY() < 0 || p.getY() >= ancho)
			return;
		casilleros[p.getY()][p.getX()] = color;
	}

	boolean estaOcupado(Posicion posicion) {
		return getCasillero(posicion) != ColorPiedra.VACIO;

	}

	public void agregarPiedra(Posicion posicion, ColorPiedra color)
			throws JugadaInvalidaException {
		agregarPiedra(posicion.getX(), posicion.getY(), color);
	}

	public void agregarPiedra(int x, int y, ColorPiedra color)
			throws JugadaInvalidaException {
		intentarAgregarPiedra(x, y, color);

		// Lo agrego para la vista.
		setChanged();
		notifyObservers();
	}

	public ArrayList<Cadena> buscarCadenasAdyacentes(Posicion posicion,
			ColorPiedra color) {
		ArrayList<Cadena> adyacentes = new ArrayList<Cadena>();
		for (Cadena cadena : cadenas)
			if (cadena.getColor() == color && cadena.esAdyacente(posicion))
				adyacentes.add(cadena);

		return adyacentes;
	}

	/**
	 * Intenta agregar una piedra. Crea las nuevas cadenas que se formarían si
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
			throw new JugadaInvalidaException("El casillero ya esta ocpado");

		// Hago una copia de las cadenas actuales
		ArrayList<Cadena> todasLasCadenas = new ArrayList<Cadena>();
		for (Cadena c : cadenas) {
			Cadena nueva = new Cadena(c);
			todasLasCadenas.add(nueva);
		}

		ArrayList<Cadena> adyacentes = buscarCadenasAdyacentes(posicion, color);
		if (adyacentes.size() > 0) {
			System.out.println("Hay adyacentes: " + adyacentes.size());
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
	 * legal, actualiza el tablero y las cadenas. Si no deja todo como está. No
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
			// Es jugada válida
			valida = true;
		} else {
			for (Cadena eliminada : cadenasEliminadas) {
				if (eliminada.getColor() != color) {
					// Se elimina una cadena del otro color, la jugada es
					// válida
					valida = true;
					// La saco de la lista de cadenas definitivas
					cadenas.remove(eliminada);
				}
			}
		}

		if (valida) {
			// Se elimino una cadena del adversario
			this.cadenas = cadenas; // Actualizo las cadenas
		} else {
			// Jugada inválida. Dejo las cadenas como estan pero
			// revierto la jugada.
			setCasillero(posicionJugada, ColorPiedra.VACIO);
			throw new JugadaInvalidaException("No es válido suicidarse");
		}
	}

}