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
    
    public Cadena(Cadena otra){
	tablero = otra.tablero;
	color = otra.color;
	fichas = new ArrayList<Posicion>();
	for(Posicion p : otra.fichas)
	    fichas.add(p);

    }
    
    public Cadena(Posicion posicion, ColorPiedra color, Tablero tablero){
	fichas = new ArrayList<Posicion>();
	setColor(color);
	agregarPosicion(posicion);
	this.tablero = tablero;
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
	for (Posicion p : fichas) 
	    if (posicion.esAdyacente(p))
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
	for (Posicion posicion : posiciones) {
	    ArrayList<Posicion> posicionesAdyacentes = posicion.obtenerPosicionesAdyacentes();

	    //por cada una de las posiciones adyacentes
	    for (Posicion adyacencia : posicionesAdyacentes)
		//me fijo si esta libre el casillero
		if (! tablero.estaOcupado(adyacencia))
		    //Si al menos 1 no esta ocupado, la cadena es libre
		    return true;
	}
	return false;
    }

    
    /**
     * Devuelve el tamation de la cadena (la cantidad de piedras que
     * la conforman.
     *
     */
    public int getTamanio(){
	return fichas.size();
    }

    
    /**
     * Indica si dos cadenas contienen las mismas piedras (son
     * iguales).
     *
     * @param otra Otra <code>Cadena</code>
     */
    public boolean esIgual(Cadena otra){
	if (otra.getTamanio() != getTamanio())
	    return false;

	for (Posicion p: fichas)
	    if (!otra.contiene(p))
		return false;

	return true;
    }

    
    /**
     * Indica si esta cadena contiene una posicion.
     *
     * @param posicion La <code>Posicion</code> a verificar.
     */
    public boolean contiene(Posicion posicion){
	for (Posicion p: fichas)
	    if (p.equals(posicion))
		return true;
	return false;
    }

    
    /**
     * Crea una nueva cadena con la union de las cadenas dadas.  No
     * verifica que la union de las cadenas sea conexa ni que sean
     * todas del mismo color. Se toma el color de la primer cadena de
     * la lista y se asume que todas las demas son del mismo color.
     */
    static public Cadena unirCadenas(ArrayList<Cadena> cadenas){
	Cadena nueva = new Cadena();
	for (Cadena cadena : cadenas) {
	    nueva.setColor(cadena.getColor());
	    ArrayList<Posicion> posiciones = cadena.getPosiciones();

	    for (Posicion posicion : posiciones) 
		nueva.agregarPosicion(posicion);
	}
	return nueva;
    }
}