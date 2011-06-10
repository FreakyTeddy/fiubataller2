package Juego;

import java.util.ArrayList;

/**
 * Clase para describir una posicion (x,y)
 *
 * @author <a href="mailto:lucas@lambda.slackware.org"></a>
 * @version 1.0
 */

public class Posicion {

	int x,y;
	
	public Posicion(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	
	/**
	 * Crea una posicion en base a una posicion en modo texto
	 * @param posicion posicion a crear
	 */
	public Posicion(String posicion) {
		this.x= ((int)posicion.charAt(0))-65;
		this.y= (Integer.parseInt(posicion.substring(1)))-1;
		if(x >= 9)
			x--; //correccion por la I que no existe en el gnugo
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}


	/**
	 * Indica si una posición es adyacente.
	 *
	 * @param posicion la <code>Posicion</code> en custion.
	 * @return TRUE si es adyacente.
	 */
	public boolean esAdyacente(Posicion posicion){
		if (posicion.getX() == x)
			return (posicion.getY() == (y-1)) || (posicion.getY() == (y+1));
		if (posicion.getY() == y)
			return (posicion.getX() == (x-1)) || (posicion.getX() == (x+1));
		return false;
	}

	public ArrayList<Posicion> obtenerPosicionesAdyacentes(){
		ArrayList<Posicion> posiciones = new ArrayList<Posicion>();
		posiciones.add(new Posicion(x-1 , y));
		posiciones.add(new Posicion(x+1 , y));
		posiciones.add(new Posicion(x , y-1));
		posiciones.add(new Posicion(x , y+1));
		return posiciones;
	}

	public boolean equals(Object obj){
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (obj.getClass() != getClass())
			return false;

		Posicion otra = (Posicion) obj;
		return getX() == otra.getX() && getY() == otra.getY();
	}

	/**
	 * Transforma una posicion en su formato string 
	 * segun la convencion del GNUgo
	 * 
	 * @param i posicion horizontal
	 * @param j posicion vertical
	 * @return posicion en formato "A1" "T19"
	 */
	public static String toString(int i, int j){
		
		if(i>=8){ //correccion por que no existe la I en el gnugo :S :S
			i++;
		}
		
		String posicion = "";
		posicion += (char)(i+65);
		posicion += (j+1);
		return posicion;
	}

	/**
	 * Transforma una posicion en su formato string 
	 * segun la convencion del GNUgo
	 */
	public String toString(){
		return toString(x,y);
	}

}
