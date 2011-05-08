package Remoto.GTP.ParsearMensajes;

import java.util.ArrayList;

import Remoto.GTP.Constantes;
import Remoto.GTP.Gtp;

public class CadenaComandoSoportado extends CadenaGtp {
	
	public CadenaComandoSoportado(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(!verificarTipoMensaje(Constantes.KNOWN_COMMAND, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			System.out.println("Cadena Comandos Soportados");
			//TODO: a quien se le pregunta?
			boolean soportado= true;
			ArrayList<String> lista= new ArrayList<String>();
			lista.add(new Boolean(soportado).toString());
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], lista); 
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}
}
