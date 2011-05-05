package Remoto.GTP.ParsearMensajes;

import Remoto.Servidor;
import Remoto.GTP.Constantes;

/* Si llegamos a este eslabon de la cadena es porque es un comando desconocido.
 * */
public class CadenaDefault extends CadenaGtp {

	private static String UNKNOWN_COMMAND= "unknown command";
	
	public CadenaDefault(Servidor servidor) {
		super(servidor);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		String mensajeRta= Constantes.INICIO_MSJ_RTA_INVALIDA + " " + UNKNOWN_COMMAND + Constantes.FIN_MSJ_RTA;
		return mensajeRta;
	}

}
