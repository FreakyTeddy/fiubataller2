package Remoto.GTP.ProcesarRespuestaObtenida;

import Remoto.Remoto;
import Remoto.GTP.ConstantesGtp;

public class ProcesadorSalida extends ProcesadorBase {

	public ProcesadorSalida(Remoto remoto) {
		super(remoto);
	}

	@Override
	public void enviarSgteCadena(String mensaje) {
		if(!(remoto.getTipoUltimoMensaje().equals(ConstantesGtp.QUIT)))
			procesadorSgte.enviarSgteCadena(mensaje);
		else {
			remoto.getArbitro().finalizarPartida();
		}
	}
}
