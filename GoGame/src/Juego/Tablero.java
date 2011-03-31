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

    boolean estaOcupado(Posicion posicion){
	return getCasillero(posicion.getX(),posicion.getY()) == ColorPiedra.VACIO;
	
    }

    public void agregarPiedra(int x, int y, ColorPiedra color){
	intentarAgregarPiedra(x,y,color);
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

    /**
     * Intenta agregar una piedra. Crea las nuevas cadenas que se
     * formarían si fuera legal la jugada y se las pasa aplicarReglas
     * para que verifique si la jugada es o no legal.
     *
     * @param x Coordenada x
     * @param y Coordenada y
     * @param color El color de la piedra
     */
    void intentarAgregarPiedra(int x, int y, ColorPiedra color){
	Posicion posicion = new Posicion(x,y);
	Cadena nuevaCadena = new Cadena(posicion, color, this);

	if (estaOcupado(posicion)) {
	    return;
	}

	//Hago una copia de las cadenas actuales
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

	aplicarReglas(posicion, todasLasCadenas, color);
    }

    

    /**
     * Aplica las reglas del juego al movimiento realizado. Si es un
     * movimiento legal, actualiza el tablero y las cadenas. Si no
     * deja todo como está. No verifica KO (por ahora).
     *
     * @param posicionJugada La posicion jugada.
     * @param cadenas El estado de las cadenas si se realizara esta jugada.
     * @param color El color del movimiento.
     */
    void aplicarReglas(Posicion posicionJugada, ArrayList<Cadena> cadenas, ColorPiedra color){
	ArrayList<Cadena> cadenasEliminadas = new ArrayList<Cadena>();

	//pongo la piedra
	setCasillero(posicionJugada.getX(), posicionJugada.getY(), color);

	//Busco las cadenas eliminadas
	for (int i = 0; i < cadenas.size(); i++)
	    if (cadenas.get(i).esLibre()) 
		cadenasEliminadas.add(cadenas.get(i));

	boolean valida=false;

	if (cadenasEliminadas.size()==0){
	    //Es jugada válida
	    valida = true;
	}
	else {
	    for (int i = 0; i < cadenasEliminadas.size(); i++) {
		Cadena eliminada = cadenasEliminadas.get(i);
		if (eliminada.getColor() != color){
		    //Se elimina una cadena del otro color, la jugada es
		    //válida
		    valida=true;
		    //La saco de la lista de cadenas definitivas
		    cadenas.remove(eliminada);
		}
	    }
	}

	if (valida) {
	    //Se elimino una cadena del adversario
	    this.cadenas = cadenas; //Actualizo las cadenas
	}
	else {
	    //Jugada inválida. Dejo las cadenas como estan pero
	    //revierto la jugada.
	    setCasillero(posicionJugada.getX(), posicionJugada.getY(), ColorPiedra.VACIO);
	}
    }
    
}
