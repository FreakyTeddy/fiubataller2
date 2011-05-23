package Remoto.GTP.ParsearMensajesEntrantes;

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
	
	public boolean verificarTipoMensaje(String tipo, String[] mensaje) {
		if(mensaje[0].equals(tipo) && mensaje.length == 1)
			return true;
		if(mensaje.length > 1)
			if(mensaje[1].equals(tipo)) {
				return true;
		}
		return false;			
	}
}
