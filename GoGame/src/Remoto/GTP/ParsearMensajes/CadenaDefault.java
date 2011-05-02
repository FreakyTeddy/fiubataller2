package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.GTP;

public class CadenaDefault extends CadenaGTP {

	public CadenaDefault(GTP gtp) {
		super(gtp);
	}

	@Override
	public void enviarSgteCadena(String[] mensaje) {
		System.out.println("Cadena Default");
	}

}
