package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.GTP;

public abstract class CadenaGTP {

	protected CadenaGTP cadenaSgte;
	protected GTP gtp;
	
	public CadenaGTP(GTP gtp) {
		this.gtp= gtp;
	}
	
	public abstract void enviarSgteCadena(String[] mensaje);
	
	public void agregarCadena(CadenaGTP cadenaSgte) {
		this.cadenaSgte= cadenaSgte;
	}
}
