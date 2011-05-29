package Remoto.GTP.ParsearMensajesEntrantes;

import Remoto.Remoto;
import Remoto.GTP.ConstantesGtp;
import Remoto.GTP.Gtp;

public class CadenaSalida extends CadenaGtp {

	private Remoto remoto;
	
	public CadenaSalida(Gtp gtp, Remoto remoto) {
		super(gtp);
		this.remoto = remoto;
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {	
		if(!verificarTipoMensaje(ConstantesGtp.QUIT, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			System.out.println("Cadena Salida");
			remoto.getArbitro().finalizarPartida();
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], null); 
			System.out.println("Respuesta " + mensajeRta);
			return mensajeRta;
		}
	}
}
