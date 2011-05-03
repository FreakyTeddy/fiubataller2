package Remoto.GTP.ParsearMensajes;

import Remoto.Remoto;
import Remoto.GTP.Constantes;

public class CadenaListarComandos extends CadenaGTP {

	private static String COMMANDS= "commands";
	private static String COMANDOS_SOPORTADOS= "\nprotocol_version\nname\nversion\nknown_command\nlist_commands\nquit\nboardsize\nclear_board\nkomi\nplay\ngenmove";
	
	public CadenaListarComandos(Remoto remoto) {
		super(remoto);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {		
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
