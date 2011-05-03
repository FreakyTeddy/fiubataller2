package Remoto.GTP.ParsearMensajes;

import Remoto.Remoto;
import Remoto.GTP.Constantes;

public class CadenaTamanioTablero extends CadenaGTP {

	public CadenaTamanioTablero(Remoto remoto) {
		super(remoto);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {	
		if(!(mensaje[1].equals(Constantes.BOARDSIZE)))
			return cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Tamanio Tablero");
			String mensajeRta= Constantes.INICIO_MSJ_RTA + mensaje[0] + Constantes.FIN_MSJ_RTA;
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}
}
