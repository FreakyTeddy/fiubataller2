package Remoto.GTP;

public class ProcesadorMsjsRtaServidor {

	private static String DELIMITADORES= "[ \n]";
	
	public void procesarMensaje(String mensaje) {
		String[] palabras= mensaje.split(DELIMITADORES);
		if(palabras[0].startsWith(Constantes.INICIO_MSJ_RTA))
			System.out.println(">Respuesta afirmativa servidor");
		else {
			System.out.println(">Respuesta de error servidor");
			System.out.println("Detalle: " + palabras[palabras.length-2] + " " + palabras[palabras.length-1]);
		}
	}	
}
