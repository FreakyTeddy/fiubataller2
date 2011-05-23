package Remoto.GTP.ProcesarRespuestaObtenida;

import Remoto.Remoto;

public abstract class ProcesadorBase {
	
	protected ProcesadorBase procesadorSgte;
	protected Remoto remoto;
	
	public ProcesadorBase(Remoto remoto) {
		this.remoto= remoto;
	}

	public abstract void enviarSgteCadena(String mensaje);
	
	public void agregarCadena(ProcesadorBase procesadorSgte) {
		this.procesadorSgte= procesadorSgte;
	}
}
