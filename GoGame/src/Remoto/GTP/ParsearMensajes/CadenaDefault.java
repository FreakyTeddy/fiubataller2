package Remoto.GTP.ParsearMensajes;

import java.util.ArrayList;

import Remoto.GTP.Gtp;

/* Si llegamos a este eslabon de la cadena es porque es un comando desconocido.
 * */
public class CadenaDefault extends CadenaGtp {

	private static String UNKNOWN_COMMAND= "unknown command";
	
	public CadenaDefault(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		ArrayList<String> lista= new ArrayList<String>();
		lista.add(UNKNOWN_COMMAND);
		String mensajeRta= gtp.mensajeRespuestaError(mensaje[0], lista);
		System.out.println("Respuesta " + mensajeRta);
		return mensajeRta;
	}
}
