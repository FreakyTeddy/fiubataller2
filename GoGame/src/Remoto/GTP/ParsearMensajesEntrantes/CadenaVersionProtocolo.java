package Remoto.GTP.ParsearMensajesEntrantes;

import java.util.ArrayList;

import Remoto.GTP.ConstantesGtp;
import Remoto.GTP.Gtp;

public class CadenaVersionProtocolo extends CadenaGtp {
	
	public CadenaVersionProtocolo(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {		
		if(!verificarTipoMensaje(ConstantesGtp.PROTOCOL_VERSION, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			System.out.println("Cadena Version Protocolo");
			ArrayList<String> lista= new ArrayList<String>();
			lista.add(ConstantesGtp.VERSION_PROTOCOLO);
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], lista);  
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}
}
