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
			if (mensaje.equalsIgnoreCase(ConstantesGtp.PASAR_TURNO))
				remoto.getArbitro().setPosicionObtenida(null);	//no tengo forma de conocer el color????
			else
				remoto.getArbitro().setPosicionObtenida(new Posicion(mensaje));
			remoto.mensajeProcesado();
		}
	}
}
