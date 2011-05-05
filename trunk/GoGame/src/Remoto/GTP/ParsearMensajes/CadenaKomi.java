package Remoto.GTP.ParsearMensajes;

import Remoto.Servidor;
import Remoto.GTP.Constantes;

public class CadenaKomi extends CadenaGtp {

	public CadenaKomi(Servidor servidor) {
		super(servidor);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(mensaje.length <= 1)
			return cadenaSgte.enviarSgteCadena(mensaje);	
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
