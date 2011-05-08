package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;
import Remoto.GTP.Gtp;

public class CadenaSalida extends CadenaGtp {

	public CadenaSalida(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {	
		if(!verificarTipoMensaje(Constantes.QUIT, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			System.out.println("Cadena Salida");
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], null); 
			System.out.println("Respuesta " + mensajeRta);
			return mensajeRta;
		}
	}
}
