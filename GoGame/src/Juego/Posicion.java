package Juego;

import java.util.ArrayList;

/**
 * Desgraciada clase para describir una posicion (x,y)
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
	
	public Posicion(String posicion) {
		this.x= ((int)posicion.charAt(0))-65;
		this.y= (Integer.parseInt(posicion.substring(1)))-1;
		if(x >= 9)
			x--; //correccion por la fucking I
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

	/**
	 * 
	 * @param i posicion horizontal
	 * @param j posicion vertical
	 * @return posicion en formato "A1" "T19"
	 */
	public static String toString(int i, int j){
		
		if(i>=8){ //no se por que no existe la I en el gnugo :S :S
			System.out.println("_________fucking I_________" + i);
			i++;
		}
		
		String posicion = "";
		posicion += (char)(i+65);
		posicion += (j+1);
		return posicion;
	}

	public String toString(){
		return toString(x,y);
	}

}
