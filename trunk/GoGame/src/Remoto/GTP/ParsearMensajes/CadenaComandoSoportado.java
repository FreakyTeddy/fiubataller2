package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;

public class CadenaComandoSoportado extends CadenaGTP {

	@Override
	public void enviarSgteCadena(String[] mensaje) {
		if(!(mensaje[1].equals(Constantes.KNOWN_COMMAND)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else
			System.out.println("Cadena Comandos Soportados");
	}

}
