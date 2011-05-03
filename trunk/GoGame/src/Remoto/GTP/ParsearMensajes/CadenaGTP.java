package Remoto.GTP.ParsearMensajes;

import Remoto.Cliente;

public abstract class CadenaGTP {

	protected CadenaGTP cadenaSgte;
	protected Cliente cliente;
	
	public CadenaGTP(Cliente cliente) {
		this.cliente= cliente;
	}
	
	public abstract String enviarSgteCadena(String[] mensaje);
	
	public void agregarCadena(CadenaGTP cadenaSgte) {
		this.cadenaSgte= cadenaSgte;
	}
}
