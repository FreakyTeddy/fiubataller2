package Remoto.GTP.ParsearMensajes;

import Remoto.Remoto;

public class CadenaDefault extends CadenaGTP {

	public CadenaDefault(Remoto remoto) {
		super(remoto);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		return "";
	}

}
