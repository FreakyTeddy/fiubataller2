package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;
import Remoto.GTP.Gtp;

public class CadenaTamanioTablero extends CadenaGtp {

	public CadenaTamanioTablero(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {	
		if(!verificarTipoMensaje(Constantes.BOARDSIZE, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			System.out.println("Cadena Tamanio Tablero");
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], null);  
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}
}
