package Remoto.GTP.ParsearMensajes;

import java.util.ArrayList;

import Remoto.GTP.Constantes;
import Remoto.GTP.Gtp;

public class CadenaListarComandos extends CadenaGtp {

	public CadenaListarComandos(Gtp gtp) {
		super(gtp);
	}

	//TODO!!! ARREGLAR
	private static String COMANDOS_SOPORTADOS= "protocol_version\nname\nversion\nknown_command\nlist_commands\nquit\nboardsize\nclear_board\nkomi\nplay\ngenmove";
	
	@Override
	public String enviarSgteCadena(String[] mensaje) {		
		if(mensaje.length <= 1)
			return cadenaSgte.enviarSgteCadena(mensaje);
		if(!(mensaje[1].equals(Constantes.LIST_COMMANDS)) || mensaje[0].startsWith(Constantes.INICIO_MSJ_RTA))
			return cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Listar Comandos");
			ArrayList<String> lista= new ArrayList<String>();
			lista.add(COMANDOS_SOPORTADOS);
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], lista); 
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}
}
