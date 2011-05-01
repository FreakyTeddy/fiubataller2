package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;

public class CadenaJugar extends CadenaGTP {

	@Override
	public void enviarSgteCadena(String[] mensaje) {
		if(!(mensaje[1].equals(Constantes.PLAY)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else
			System.out.println("Cadena Jugar");
	}
}
