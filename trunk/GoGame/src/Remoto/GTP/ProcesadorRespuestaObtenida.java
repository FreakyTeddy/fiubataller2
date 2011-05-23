package Remoto.GTP;

import Remoto.Remoto;
import Remoto.GTP.ProcesarRespuestaObtenida.ProcesadorBase;
import Remoto.GTP.ProcesarRespuestaObtenida.ProcesadorGenerarJugada;
import Remoto.GTP.ProcesarRespuestaObtenida.ProcesadorJugar;
import Remoto.GTP.ProcesarRespuestaObtenida.ProcesadorSalida;
import Remoto.GTP.ProcesarRespuestaObtenida.ProcesarDefault;

public class ProcesadorRespuestaObtenida {

	private ProcesadorBase procesadorGenerarJugada;
	private ProcesadorBase procesadorJugar;
	private ProcesadorBase procesadorSalida;
	private ProcesadorBase procesadorDefault;
	
	public ProcesadorRespuestaObtenida(Remoto remoto) {
		iniciarCadenas(remoto);
		encadenarCadenas();
	}

	private void iniciarCadenas(Remoto remoto) {
		procesadorGenerarJugada= new ProcesadorGenerarJugada(remoto);
		procesadorJugar= new ProcesadorJugar(remoto);
		procesadorDefault= new ProcesarDefault(remoto);
		procesadorSalida= new ProcesadorSalida(remoto);
	}
	
	private void encadenarCadenas() {
		procesadorGenerarJugada.agregarCadena(procesadorJugar);
		procesadorJugar.agregarCadena(procesadorSalida);
		procesadorSalida.agregarCadena(procesadorDefault);
	}
	
	public void procesarRespuestaObtenida(String mensaje) {
		procesadorGenerarJugada.enviarSgteCadena(mensaje);
	}
}
