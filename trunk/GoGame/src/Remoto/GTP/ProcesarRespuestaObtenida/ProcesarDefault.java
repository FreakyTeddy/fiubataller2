package Remoto.GTP.ProcesarRespuestaObtenida;

import Remoto.Remoto;

public class ProcesarDefault extends ProcesadorBase {

	public ProcesarDefault(Remoto remoto) {
		super(remoto);
	}

	@Override
	public void enviarSgteCadena(String mensaje) {
		remoto.mensajeProcesado();
	}
}
