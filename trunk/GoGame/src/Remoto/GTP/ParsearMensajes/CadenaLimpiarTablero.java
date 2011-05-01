package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;

public class CadenaLimpiarTablero extends CadenaGTP {

	@Override
	public void enviarSgteCadena(String[] mensaje) {
		if(!(mensaje[1].equals(Constantes.CLEAR_BOARD)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else
			System.out.println("Cadena Limpiar Tablero");
	}

}
