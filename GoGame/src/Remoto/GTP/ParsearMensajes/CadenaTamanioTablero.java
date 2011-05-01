package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;

public class CadenaTamanioTablero extends CadenaGTP {

	@Override
	public void enviarSgteCadena(String[] mensaje) {	
		if(!(mensaje[1].equals(Constantes.BOARDSIZE)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else
			System.out.println("Cadena Tamanio Tablero");
	}
}
