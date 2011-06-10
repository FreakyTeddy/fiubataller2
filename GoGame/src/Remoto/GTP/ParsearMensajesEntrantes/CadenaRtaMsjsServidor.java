package Remoto.GTP.ParsearMensajesEntrantes;

import Remoto.Remoto;
import Remoto.GTP.ConstantesGtp;
import Remoto.GTP.Gtp;

public class CadenaRtaMsjsServidor extends CadenaGtp {
	
	private boolean leyendoComandos;
	private Remoto remoto;
	
	public CadenaRtaMsjsServidor(Gtp gtp, Remoto remoto) {
		super(gtp);
		leyendoComandos= false;
		this.remoto= remoto;
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(leyendoComandos && (mensaje[0].startsWith(ConstantesGtp.INICIO_MSJ_RTA) || mensaje.length > 1))
			leyendoComandos= false;
		if(mensaje[0].startsWith(ConstantesGtp.INICIO_MSJ_RTA) || leyendoComandos) {
			leyendoComandos= true;
			String msj= "";
			for(int i= 1; i < mensaje.length; i++)
				msj+= mensaje[i];
			remoto.procesarRespuestaObtenida(msj);
			return "";
		} else if(mensaje[0].startsWith(ConstantesGtp.INICIO_MSJ_RTA_ERROR)) {
			return "";
		}
			return cadenaSgte.enviarSgteCadena(mensaje);	
	}
}
