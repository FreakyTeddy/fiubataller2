package Remoto.GTP.ParsearMensajes;

import Remoto.Servidor;
import Remoto.GTP.Constantes;

public class CadenaComandoSoportado extends CadenaGtp {

	private static String KNOWN= "known";
	
	public CadenaComandoSoportado(Servidor servidor) {
		super(servidor);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(mensaje.length <= 1)
			return cadenaSgte.enviarSgteCadena(mensaje);	
		if(!(mensaje[1].equals(Constantes.KNOWN_COMMAND))) 
			return cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Comandos Soportados");
			//TODO: a quien se le pregunta?
			boolean soportado= true;
			String mensajeRta= Constantes.INICIO_MSJ_RTA + mensaje[0] + " " + KNOWN + " " + soportado + Constantes.FIN_MSJ_RTA;
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}

}
