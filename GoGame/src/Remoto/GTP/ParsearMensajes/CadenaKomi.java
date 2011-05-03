package Remoto.GTP.ParsearMensajes;

import Remoto.Remoto;
import Remoto.GTP.Constantes;

public class CadenaKomi extends CadenaGTP {

	public CadenaKomi(Remoto remoto) {
		super(remoto);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(!(mensaje[1].equals(Constantes.KOMI)))
			return cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Komi");
			String mensajeRta= Constantes.INICIO_MSJ_RTA + mensaje[0] + " " + Constantes.FIN_MSJ_RTA;
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}
}
