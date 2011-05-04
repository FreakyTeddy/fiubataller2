package Remoto.GTP.ParsearMensajes;

import Remoto.Remoto;
import Remoto.GTP.Constantes;

/* Si llegamos a este eslabon de la cadena es porque es un comando desconocido.
 * */
public class CadenaDefault extends CadenaGTP {

	private static String UNKNOWN_COMMAND= "unknown command";
	
	public CadenaDefault(Remoto remoto) {
		super(remoto);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		String mensajeRta= Constantes.INICIO_MSJ_RTA_INVALIDA + " " + UNKNOWN_COMMAND + Constantes.FIN_MSJ_RTA;
		return mensajeRta;
	}

}
