package Remoto.GTP;


public class ProcesadorMsjsRtaServidor extends ProcesadorMsjs {

	public String procesarMensaje(String mensaje) {
		String[] palabras= mensaje.split(DELIMITADORES);
		if(palabras[0].startsWith(Constantes.INICIO_MSJ_RTA)) {
			return "";
		} else {
			String mensajeError= palabras[palabras.length-2] + " " + palabras[palabras.length-1];
			return mensajeError;
		}
	}	
}
