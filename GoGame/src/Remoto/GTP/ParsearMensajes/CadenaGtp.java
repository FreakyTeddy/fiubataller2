package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Gtp;

public abstract class CadenaGtp {

	protected Gtp gtp;
	protected CadenaGtp cadenaSgte;
	
	public CadenaGtp(Gtp gtp) {
		this.gtp= gtp;
	}

	public abstract String enviarSgteCadena(String[] mensaje);
	
	public void agregarCadena(CadenaGtp cadenaSgte) {
		this.cadenaSgte= cadenaSgte;
	}
}
