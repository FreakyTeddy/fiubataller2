package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;
import Remoto.GTP.GTP;

public class CadenaVersionProtocolo extends CadenaGTP {

	public CadenaVersionProtocolo(GTP gtp) {
		super(gtp);
	}

	@Override
	public void enviarSgteCadena(String[] mensaje) {		
		if(!(mensaje[1].equals(Constantes.PROTOCOL_VERSION)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Version Protocolo");
			String mensajeRta= Constantes.INICIO_MSJ_RTA + mensaje[0] + Constantes.FIN_MSJ_RTA;
			System.out.println("Respuesta " + mensajeRta);	
		}	
	}
}
