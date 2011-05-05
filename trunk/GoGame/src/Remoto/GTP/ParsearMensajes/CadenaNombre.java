package Remoto.GTP.ParsearMensajes;

import Remoto.Servidor;
import Remoto.GTP.Constantes;

public class CadenaNombre extends CadenaGtp {

	public CadenaNombre(Servidor servidor) {
		super(servidor);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(mensaje.length <= 1)
			return cadenaSgte.enviarSgteCadena(mensaje);	
		if(!(mensaje[1].equals(Constantes.NAME)))
			return cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Nombre");
			String mensajeRta= Constantes.INICIO_MSJ_RTA + mensaje[0] + " " + Constantes.FIN_MSJ_RTA;
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}

}
