package Remoto.GTP.ParsearMensajes;

import java.util.ArrayList;

import Remoto.GTP.Constantes;
import Remoto.GTP.Gtp;

public class CadenaVersion extends CadenaGtp {

	private static String VERSION_PROGRAMA= "1.0";
	
	public CadenaVersion(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(!verificarTipoMensaje(Constantes.VERSION, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			System.out.println("Cadena Version");
			ArrayList<String> lista= new ArrayList<String>();
			lista.add(VERSION_PROGRAMA);
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], lista);  
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}

}
