package Remoto.GTP.ParsearMensajes;

import Remoto.Cliente;

public class CadenaDefault extends CadenaGTP {

	public CadenaDefault(Cliente remoto) {
		super(remoto);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		System.out.println("Cadena Default");
		return "Nadie";
	}

}
