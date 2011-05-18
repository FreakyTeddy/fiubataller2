package controlador;

import Juego.FullMoonGo;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
			FullMoonGo.getInstancia().nuevaPartida();
			FullMoonGo.getInstancia().jugar();


	}

}
