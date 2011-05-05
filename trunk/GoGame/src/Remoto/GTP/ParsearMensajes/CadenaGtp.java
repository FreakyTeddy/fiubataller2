package Remoto.GTP.ParsearMensajes;

import Remoto.Servidor;

public abstract class CadenaGtp {

	protected CadenaGtp cadenaSgte;
	protected Servidor servidor;
	
	public CadenaGtp(Servidor servidor) {
		this.servidor= servidor;
	}
	
	public abstract String enviarSgteCadena(String[] mensaje);
	
	public void agregarCadena(CadenaGtp cadenaSgte) {
		this.cadenaSgte= cadenaSgte;
	}
}
