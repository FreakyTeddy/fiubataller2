package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;
import Remoto.GTP.Gtp;

public class CadenaKomi extends CadenaGtp {

	public CadenaKomi(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(mensaje.length <= 1)
			return cadenaSgte.enviarSgteCadena(mensaje);	
		if(!(mensaje[1].equals(Constantes.KOMI)) || mensaje[0].startsWith(Constantes.INICIO_MSJ_RTA))
			return cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Komi");
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], null);  
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}
}
