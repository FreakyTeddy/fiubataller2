package Remoto.GTP.ParsearMensajes;

import Remoto.Cliente;

public abstract class CadenaGTP {

	protected CadenaGTP cadenaSgte;
	protected Cliente remoto;
	
	public CadenaGTP(Cliente remoto) {
		this.remoto= remoto;
	}
	
	public abstract String enviarSgteCadena(String[] mensaje);
	
	public void agregarCadena(CadenaGTP cadenaSgte) {
		this.cadenaSgte= cadenaSgte;
	}
}
