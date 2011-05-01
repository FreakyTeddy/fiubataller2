package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;

public class CadenaSalida extends CadenaGTP {

	@Override
	public void enviarSgteCadena(String[] mensaje) {	
		if(!(mensaje[1].equals(Constantes.QUIT)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else
			System.out.println("Cadena Salida");
	}
}
