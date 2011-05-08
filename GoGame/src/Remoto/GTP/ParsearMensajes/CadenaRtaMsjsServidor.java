package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;
import Remoto.GTP.Gtp;

public class CadenaRtaMsjsServidor extends CadenaGtp {
	
	private boolean leyendoComandos;
	
	public CadenaRtaMsjsServidor(Gtp gtp) {
		super(gtp);
		leyendoComandos= false;
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if((leyendoComandos && mensaje[0].startsWith(Constantes.INICIO_MSJ_RTA)) || mensaje.length > 1)
			leyendoComandos= false;
		if(mensaje[0].startsWith(Constantes.INICIO_MSJ_RTA) || leyendoComandos) {
			leyendoComandos= true;
			System.out.println(">Respuesta afirmativa servidor");
			return "";
		} else if(mensaje[0].startsWith(Constantes.INICIO_MSJ_RTA_ERROR)) {
			System.out.println(">Respuesta de error servidor");
			return "";
		}
			return cadenaSgte.enviarSgteCadena(mensaje);	
	}
}
