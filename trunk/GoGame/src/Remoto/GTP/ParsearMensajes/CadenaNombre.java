package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;
import Remoto.GTP.GTP;

public class CadenaNombre extends CadenaGTP {

	public CadenaNombre(GTP gtp) {
		super(gtp);
	}

	@Override
	public void enviarSgteCadena(String[] mensaje) {
		if(!(mensaje[1].equals(Constantes.NAME)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Nombre");
			String mensajeRta= Constantes.INICIO_MSJ_RTA + mensaje[0] + " " + Constantes.FIN_MSJ_RTA;
			System.out.println("Respuesta " + mensajeRta);	
		}
	}

}
