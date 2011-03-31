package Juego;

import java.util.ArrayList;
import Juego.ColorPiedra;

/**
 * Describe class <code>Tablero</code> here.
 *
 * @author <a href="mailto:lucas@lambda.slackware.org"></a>
 * @version 1.0
 */
public class Tablero {

    ColorPiedra casilleros[][];
    ArrayList<Cadena> cadenas;
    int ancho;

    public Tablero() {
	int ancho = 13;
	casilleros = new ColorPiedra[ancho][ancho];
	cadenas = new ArrayList<Cadena>();
    }

    public int getAncho(){
	return ancho;
    }

    ColorPiedra getCasillero(int x, int y) {
	return casilleros[y][x];
    }

    void setCasillero(int x, int y, ColorPiedra color) {
	casilleros[y][x] = color;
    }

    boolean estaOcupado(int x, int y){
	return getCasillero(x,y) == ColorPiedra.VACIO;
	
    }

    public void agregarPiedra(int x, int y, ColorPiedra color){
	intentarAgregarPiedra(x,y,color);
	setCasillero(x, y, color);
    }
    
    public ArrayList<Cadena> buscarCadenasAdyacentes(Posicion posicion, ColorPiedra color){
	ArrayList<Cadena> adyacentes = new ArrayList<Cadena>();
	for (int i = 0; i < cadenas.size() ; i++) {
	    Cadena cadena = cadenas.get(i);
	    if (cadena.getColor()==color && cadena.esAdyacente(posicion))
		adyacentes.add(cadena);
	}
	return adyacentes;
    }

    void intentarAgregarPiedra(int x, int y, ColorPiedra color){
	if (estaOcupado(x,y)) {
	    return;
	}

	Posicion posicion = new Posicion(x,y);
	Cadena nuevaCadena = new Cadena(posicion, color);

	ArrayList<Cadena> todasLasCadenas = new ArrayList<Cadena>();
	todasLasCadenas = cadenas;
	
	ArrayList<Cadena> adyacentes = buscarCadenasAdyacentes(posicion, color);
	if (adyacentes.size()>0){
	    //Hay cadenas adyacentes las saco de la lista de cadenas
	    for (int i = 0; i < adyacentes.size() ; i++)
		todasLasCadenas.remove(adyacentes.get(i));

	    //las uno
	    adyacentes.add(nuevaCadena);
	    Cadena cadenaResultante = Cadena.unirCadenas(adyacentes);

	    //La cadena que se agrega en este turno es la union de
	    //todas las adyacentes a la posicion
	    nuevaCadena = cadenaResultante;
	}

	//Agrego la cadena creada en este turno
	todasLasCadenas.add(nuevaCadena);

	//comprobarReglas(todasLasCadenas, color);
    }
    
}
