package Remoto.GTP.ParsearMensajesEntrantes;

import java.util.ArrayList;

import Remoto.GTP.ConstantesGtp;
import Remoto.GTP.Gtp;

public class CadenaComandoSoportado extends CadenaGtp {
	
	public CadenaComandoSoportado(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(!verificarTipoMensaje(ConstantesGtp.KNOWN_COMMAND, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			boolean soportado= true;
			ArrayList<String> lista= new ArrayList<String>();
			lista.add(new Boolean(soportado).toString());
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], lista); 
			return mensajeRta;
		}
	}
}
