package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;
import Remoto.GTP.Gtp;

public class CadenaRtaMsjsServidor extends CadenaGtp {
	
	private boolean comandosSolicitados;
	
	public CadenaRtaMsjsServidor(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(mensaje[0].startsWith(Constantes.INICIO_MSJ_RTA)) {
//			System.out.println(">Respuesta afirmativa servidor");
			return "";
		} else if(mensaje[0].startsWith(Constantes.INICIO_MSJ_RTA_ERROR)) {
//			String mensajeError= mensaje[mensaje.length-2] + " " + mensaje[mensaje.length-1];	
//			System.out.println(">Respuesta de error servidor");
//			System.out.println("Detalle: " + mensajeError);
			return "";
		} else if(mensaje.length == 1) {
			System.out.println(mensaje[0]);
			return "";
		}
			return cadenaSgte.enviarSgteCadena(mensaje);	
	}
}
