package Remoto.GTP.ParsearMensajes;

import java.util.ArrayList;

import Remoto.GTP.Constantes;
import Remoto.GTP.Gtp;

public class CadenaNombre extends CadenaGtp {

	private static String NOMBRE_PROGRAMA="GNU Go";
	
	public CadenaNombre(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(!verificarTipoMensaje(Constantes.NAME, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			System.out.println("Cadena Nombre");
			ArrayList<String> lista= new ArrayList<String>();
			lista.add(NOMBRE_PROGRAMA);
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], lista);  
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}

}
