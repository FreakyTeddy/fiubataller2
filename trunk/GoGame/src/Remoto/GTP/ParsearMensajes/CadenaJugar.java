package Remoto.GTP.ParsearMensajes;

import java.util.ArrayList;

import Remoto.GTP.Constantes;
import Remoto.GTP.Gtp;

public class CadenaJugar extends CadenaGtp {

	public CadenaJugar(Gtp gtp) {
		super(gtp);
	}

	private static String MENSAJE_ERROR= "illegal move";
	
	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(mensaje.length <= 1)
			return cadenaSgte.enviarSgteCadena(mensaje);	
		if(!(mensaje[1].equals(Constantes.PLAY)) || mensaje[0].startsWith(Constantes.INICIO_MSJ_RTA))
			return cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Jugar");
			//TODO: como se q es valido?
			boolean resultado= true;
			String mensajeRta= "";
			if(resultado)
				mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], null);
			else {
				ArrayList<String> lista= new ArrayList<String>();
				lista.add(MENSAJE_ERROR);
				mensajeRta= gtp.mensajeRespuestaError(mensaje[0], lista);
			}
			System.out.println("Respuesta " + mensajeRta);
			return mensajeRta;
		}
	}
}
