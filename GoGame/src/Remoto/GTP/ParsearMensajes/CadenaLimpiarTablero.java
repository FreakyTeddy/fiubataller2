package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;
import Remoto.GTP.Gtp;

public class CadenaLimpiarTablero extends CadenaGtp {

	public CadenaLimpiarTablero(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(!verificarTipoMensaje(Constantes.CLEAR_BOARD, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			System.out.println("Cadena Limpiar Tablero");
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], null);  
			System.out.println("Respuesta " + mensajeRta);		
			return mensajeRta;
		}
	}

}
