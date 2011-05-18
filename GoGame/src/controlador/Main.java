package controlador;

import vista.VentanaAplicacionGo;
import Juego.FullMoonGo;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		FullMoonGo fullMoonGo = FullMoonGo.getInstancia();
		fullMoonGo.nuevaPartida();
	
         /** TODO: Tambien para refactor.
          * VentanaAplicacionGo Ventana = new VentanaAplicacionGo(fullMoonGo);
          */
		
		



	}

}
