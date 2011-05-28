package Remoto.GTP.ProcesarRespuestaObtenida;

import Juego.Posicion;
import Remoto.Remoto;
import Remoto.GTP.ConstantesGtp;

public class ProcesadorGenerarJugada extends ProcesadorBase {

	public ProcesadorGenerarJugada(Remoto remoto) {
		super(remoto);
	}

	@Override
	public void enviarSgteCadena(String mensaje) {
		if(!(remoto.getTipoUltimoMensaje().equals(ConstantesGtp.GENMOVE)))
				procesadorSgte.enviarSgteCadena(mensaje);
		else {
			remoto.mensajeProcesado();
			if (mensaje.equalsIgnoreCase("pass"))
				remoto.getArbitro().setPosicionObtenida(null);
			else
				remoto.getArbitro().setPosicionObtenida(new Posicion(mensaje));
		}
	}
}
