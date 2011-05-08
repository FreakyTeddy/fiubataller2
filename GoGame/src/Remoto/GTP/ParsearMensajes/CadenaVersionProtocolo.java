package Remoto.GTP.ParsearMensajes;

import java.util.ArrayList;

import Remoto.GTP.Constantes;
import Remoto.GTP.Gtp;

public class CadenaVersionProtocolo extends CadenaGtp {

	private static String VERSION_PROTOCOLO= "1";
	
	public CadenaVersionProtocolo(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {		
		if(!verificarTipoMensaje(Constantes.PROTOCOL_VERSION, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			System.out.println("Cadena Version Protocolo");
			ArrayList<String> lista= new ArrayList<String>();
			lista.add(VERSION_PROTOCOLO);
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], lista);  
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}
}
