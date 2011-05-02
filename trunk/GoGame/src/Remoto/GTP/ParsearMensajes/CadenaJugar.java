package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;
import Remoto.GTP.GTP;

public class CadenaJugar extends CadenaGTP {

	private static String MENSAJE_ERROR= "illegal move";
	
	public CadenaJugar(GTP gtp) {
		super(gtp);
	}

	@Override
	public void enviarSgteCadena(String[] mensaje) {
		if(!(mensaje[1].equals(Constantes.PLAY)))
			cadenaSgte.enviarSgteCadena(mensaje);
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
		}
	}
}
