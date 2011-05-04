package Remoto.GTP.ParsearMensajes;

import Remoto.Remoto;

public abstract class CadenaGTP {

	protected CadenaGTP cadenaSgte;
	protected Remoto remoto;
	
	public CadenaGTP(Remoto remoto) {
		this.remoto= remoto;
	}
	
	public abstract String enviarSgteCadena(String[] mensaje);
	
	public void agregarCadena(CadenaGTP cadenaSgte) {
		this.cadenaSgte= cadenaSgte;
	}
}
