package Remoto.GTP.ProcesarRespuestaObtenida;

import Remoto.Remoto;
import Remoto.GTP.ConstantesGtp;

public class ProcesadorJugar extends ProcesadorBase {

	public ProcesadorJugar(Remoto remoto) {
		super(remoto);
	}

	@Override
	public void enviarSgteCadena(String mensaje) {
		if(!(remoto.getTipoUltimoMensaje().equals(ConstantesGtp.PLAY)))
			procesadorSgte.enviarSgteCadena(mensaje);
		else {
			remoto.mensajeProcesado();
			System.out.println("Procesador Jugar");
		}
	}
}
