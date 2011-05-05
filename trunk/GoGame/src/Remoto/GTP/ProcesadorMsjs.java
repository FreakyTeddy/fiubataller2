package Remoto.GTP;

/* Interface para el proceso de mensajes tipo gtp
 * */
public abstract class ProcesadorMsjs {
	protected static String DELIMITADORES= "[ \n]";
	
	public abstract String procesarMensaje(String mensaje);
}
