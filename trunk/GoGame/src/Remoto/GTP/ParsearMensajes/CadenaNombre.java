package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;

public class CadenaNombre extends CadenaGTP {

	@Override
	public void enviarSgteCadena(String[] mensaje) {
		if(!(mensaje[1].equals(Constantes.NAME)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else
			System.out.println("Cadena Nombre");
	}

}
