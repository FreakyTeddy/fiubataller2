package Juego;

import java.util.ArrayList;
import Juego.Posicion;
import Juego.ColorPiedra;
import Juego.Tablero;

/**
 * Describe class Cadena here.
 *
 * @author <a href="mailto:lucas@lambda.slackware.org"></a>
 * @version 1.0
 */
public class Cadena {

    ArrayList<Posicion> fichas; /** fichas integrantes */
    
    ColorPiedra color; /** El color de la cadena */
    Tablero tablero;   /** El tablero al cual pertenece la cadena */

    Cadena(){
	fichas = new ArrayList<Posicion>();
	setColor(ColorPiedra.VACIO);
    }

    public Cadena(Posicion posicion, ColorPiedra color, Tablero tablero){
	fichas = new ArrayList<Posicion>();
	setColor(color);
	agregarPosicion(posicion);
    }

    public void setColor(ColorPiedra color){
	this.color = color;
    }
    
    /**
     * Agrega una </code>Posicion</code> a la </code>Cadena</code>.
     *
     * @param posicion La <code>Posicion</code> a agregar.
     */
    void agregarPosicion(Posicion posicion){
	fichas.add(posicion);
    }
    
    /**
     * Indica si un casillero es adyacente a la cadena.
     *
     * @param posicion una <code>Posicion</code> del tablero.
     * @return a <code>boolean</code> value
     */
    public boolean esAdyacente(Posicion posicion){
	for (int i = 0; i < fichas.size(); i++)
	    if (posicion.esAdyacente(fichas.get(i)))
		return true;

	return false;
    }

    public ColorPiedra getColor(){
	return color;
    }

    public final ArrayList<Posicion> getPosiciones(){
	return fichas;
    }

    /**
     * Se fija si la cadena es libre.
     *
     * @return a <code>boolean</code> value
     */
    public boolean esLibre(){
	ArrayList<Posicion> posiciones = getPosiciones();
	for (int i = 0; i < posiciones.size() ; i++) {
	 
	    ArrayList<Posicion> posicionesAdyacentes = posiciones.get(i).obtenerPosicionesAdyacentes();
	    //por cada una de las posiciones adyacentes
	    for (int j = 0; j < posicionesAdyacentes.size(); j ++)
		//me fijo si esta libre el casillero
		if (! tablero.estaOcupado(posicionesAdyacentes.get(i)))
		    //Si al menos 1 no esta ocupado, la cadena es libre
		    return true;
	}

	return false;
    }

    static public Cadena unirCadenas(ArrayList<Cadena> cadenas){
	Cadena nueva = new Cadena();
	for (int i = 0; i < cadenas.size(); i++) {
	    nueva.setColor(cadenas.get(i).getColor());
	    ArrayList<Posicion> posiciones = cadenas.get(i).getPosiciones();
	    for (int j = 0; j < posiciones.size(); j++) 
		nueva.agregarPosicion(posiciones.get(j));
	}
	return nueva;
    }
}
