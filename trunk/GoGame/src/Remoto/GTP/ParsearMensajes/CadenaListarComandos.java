package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;
import Remoto.GTP.GTP;

public class CadenaListarComandos extends CadenaGTP {

	private static String COMMANDS= "commands";
	private static String COMANDOS_SOPORTADOS= "\nprotocol_version\nname\nversion\nknown_command\nlist_commands\nquit\nboardsize\nclear_board\nkomi\nplay\ngenmove";
	
	public CadenaListarComandos(GTP gtp) {
		super(gtp);
	}

	@Override
	public void enviarSgteCadena(String[] mensaje) {		
		if(!(mensaje[1].equals(Constantes.LIST_COMMANDS)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Listar Comandos");
			String mensajeRta= Constantes.INICIO_MSJ_RTA + mensaje[0] + " " + COMMANDS + COMANDOS_SOPORTADOS + Constantes.FIN_MSJ_RTA;
			System.out.println("Respuesta " + mensajeRta);	
		}
	}
}
