package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;

public class CadenaKomi extends CadenaGTP {

	@Override
	public void enviarSgteCadena(String[] mensaje) {
		if(!(mensaje[1].equals(Constantes.KOMI)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else
			System.out.println("Cadena Komi");	
	}
}
