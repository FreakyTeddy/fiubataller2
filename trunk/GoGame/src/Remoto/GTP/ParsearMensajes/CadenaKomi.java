package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;
import Remoto.GTP.GTP;

public class CadenaKomi extends CadenaGTP {

	public CadenaKomi(GTP gtp) {
		super(gtp);
	}

	@Override
	public void enviarSgteCadena(String[] mensaje) {
		if(!(mensaje[1].equals(Constantes.KOMI)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Komi");
			String mensajeRta= Constantes.INICIO_MSJ_RTA + mensaje[0] + " " + Constantes.FIN_MSJ_RTA;
			System.out.println("Respuesta " + mensajeRta);	
		}
	}
}
