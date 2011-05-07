package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;
import Remoto.GTP.Gtp;

public class CadenaTamanioTablero extends CadenaGtp {

	public CadenaTamanioTablero(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {	
		if(mensaje.length <= 1)
			return cadenaSgte.enviarSgteCadena(mensaje);	
		if(!(mensaje[1].equals(Constantes.BOARDSIZE)) || mensaje[0].startsWith(Constantes.INICIO_MSJ_RTA))
			return cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Tamanio Tablero");
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], null);  
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}
}
