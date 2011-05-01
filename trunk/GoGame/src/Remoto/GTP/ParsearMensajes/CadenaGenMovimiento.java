package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;

public class CadenaGenMovimiento extends CadenaGTP {

	@Override
	public void enviarSgteCadena(String[] mensaje) {
		if(!(mensaje[1].equals(Constantes.GENMOVE)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else
			System.out.println("Cadena Generar Movimiento");
	}
}
