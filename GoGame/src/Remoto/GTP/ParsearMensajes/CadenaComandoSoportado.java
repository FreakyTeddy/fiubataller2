package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;
import Remoto.GTP.GTP;

public class CadenaComandoSoportado extends CadenaGTP {

	private static String KNOWN= "known";
	
	public CadenaComandoSoportado(GTP gtp) {
		super(gtp);
	}

	@Override
	public void enviarSgteCadena(String[] mensaje) {
		if(!(mensaje[1].equals(Constantes.KNOWN_COMMAND)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Comandos Soportados");
			//TODO: a quien se le pregunta?
			boolean soportado= true;
			String mensajeRta= Constantes.INICIO_MSJ_RTA + mensaje[0] + " " + KNOWN + " " + soportado + Constantes.FIN_MSJ_RTA;
			System.out.println("Respuesta " + mensajeRta);	
		}
	}

}
