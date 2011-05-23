package Remoto.GTP.ProcesarRespuestaObtenida;

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
			System.out.println("Mensaje: " + mensaje);
			System.out.println("Procesador Generar Jugada");
			remoto.setPosicionObtenida(mensaje);
		}
	}
}
