package Remoto.GTP.ParsearMensajes;

import Remoto.Cliente;
import Remoto.GTP.Constantes;

public class CadenaVersionProtocolo extends CadenaGTP {

	public CadenaVersionProtocolo(Cliente remoto) {
		super(remoto);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {		
		if(mensaje.length <= 1)
			return cadenaSgte.enviarSgteCadena(mensaje);		
		if(!(mensaje[1].equals(Constantes.PROTOCOL_VERSION)))
			return cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Version Protocolo");
			String mensajeRta= Constantes.INICIO_MSJ_RTA + mensaje[0] + Constantes.FIN_MSJ_RTA;
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}
}
