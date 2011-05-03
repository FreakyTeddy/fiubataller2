package Remoto.GTP.ParsearMensajes;

import Remoto.Cliente;
import Remoto.GTP.Constantes;

public class CadenaSalida extends CadenaGTP {

	public CadenaSalida(Cliente cliente) {
		super(cliente);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {	
		if(mensaje.length <= 1)
			return cadenaSgte.enviarSgteCadena(mensaje);	
		if(!(mensaje[1].equals(Constantes.QUIT)))
			return cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Salida");
			String mensajeRta= Constantes.INICIO_MSJ_RTA + mensaje[0] + Constantes.FIN_MSJ_RTA;
			System.out.println("Respuesta " + mensajeRta);	
			cliente.mensajeSalida();
			return mensajeRta;
		}
	}
}
