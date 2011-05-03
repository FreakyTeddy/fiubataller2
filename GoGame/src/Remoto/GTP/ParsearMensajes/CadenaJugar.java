package Remoto.GTP.ParsearMensajes;

import Remoto.Remoto;
import Remoto.GTP.Constantes;

public class CadenaJugar extends CadenaGTP {

	private static String MENSAJE_ERROR= "illegal move";
	
	public CadenaJugar(Remoto remoto) {
		super(remoto);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(!(mensaje[1].equals(Constantes.PLAY)))
			return cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Jugar");
			//TODO: como se q es valido?
			boolean resultado= true;
			String mensajeRta= "";
			if(resultado)
				mensajeRta= Constantes.INICIO_MSJ_RTA + mensaje[0] + Constantes.FIN_MSJ_RTA;
			else
				mensajeRta= Constantes.INICIO_MSJ_RTA_INVALIDA + mensaje[0] + MENSAJE_ERROR + Constantes.FIN_MSJ_RTA;
			System.out.println("Respuesta " + mensajeRta);
			return mensajeRta;
		}
	}
}
