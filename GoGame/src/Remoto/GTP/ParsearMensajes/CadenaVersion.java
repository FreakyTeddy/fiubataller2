package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;

public class CadenaVersion extends CadenaGTP {

	@Override
	public void enviarSgteCadena(String[] mensaje) {
		if(!(mensaje[1].equals(Constantes.VERSION)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else
			System.out.println("Cadena Version");
	}

}
