package Remoto.GTP.ParsearMensajesEntrantes;

import Remoto.GTP.ConstantesGtp;
import Remoto.GTP.Gtp;

public class CadenaLimpiarTablero extends CadenaGtp {

	public CadenaLimpiarTablero(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(!verificarTipoMensaje(ConstantesGtp.CLEAR_BOARD, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], null);  	
			return mensajeRta;
		}
	}
}
