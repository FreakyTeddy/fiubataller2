package Remoto.GTP.ParsearMensajes;

import Remoto.Servidor;
import Remoto.GTP.Constantes;

public class CadenaListarComandos extends CadenaGtp {

	private static String COMMANDS= "commands";
	private static String COMANDOS_SOPORTADOS= "\nprotocol_version\nname\nversion\nknown_command\nlist_commands\nquit\nboardsize\nclear_board\nkomi\nplay\ngenmove";
	
	public CadenaListarComandos(Servidor servidor) {
		super(servidor);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {		
		if(mensaje.length <= 1)
			return cadenaSgte.enviarSgteCadena(mensaje);	
		if(!(mensaje[1].equals(Constantes.LIST_COMMANDS)))
			return cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Listar Comandos");
			String mensajeRta= Constantes.INICIO_MSJ_RTA + mensaje[0] + " " + COMMANDS + COMANDOS_SOPORTADOS + Constantes.FIN_MSJ_RTA;
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}
}
