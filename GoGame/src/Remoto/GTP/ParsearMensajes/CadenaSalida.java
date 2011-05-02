package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;
import Remoto.GTP.GTP;

public class CadenaSalida extends CadenaGTP {

	public CadenaSalida(GTP gtp) {
		super(gtp);
	}

	@Override
	public void enviarSgteCadena(String[] mensaje) {	
		if(!(mensaje[1].equals(Constantes.QUIT)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Salida");
			String mensajeRta= Constantes.INICIO_MSJ_RTA + mensaje[0] + Constantes.FIN_MSJ_RTA;
			System.out.println("Respuesta " + mensajeRta);	
		}
	}
}
