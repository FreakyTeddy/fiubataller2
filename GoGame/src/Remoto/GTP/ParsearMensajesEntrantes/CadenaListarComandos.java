package Remoto.GTP.ParsearMensajesEntrantes;

import java.util.ArrayList;

import Remoto.GTP.ConstantesGtp;
import Remoto.GTP.Gtp;

public class CadenaListarComandos extends CadenaGtp {

	//TODO!!! ARREGLAR
	private static final String COMANDOS_SOPORTADOS= "protocol_version\nname\nversion\nknown_command\nlist_commands\nquit\nboardsize\nclear_board\nkomi\nplay\ngenmove";

	public CadenaListarComandos(Gtp gtp) {
		super(gtp);
	}
	
	@Override
	public String enviarSgteCadena(String[] mensaje) {		
		if(!verificarTipoMensaje(ConstantesGtp.LIST_COMMANDS, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			ArrayList<String> lista= new ArrayList<String>();
			lista.add(COMANDOS_SOPORTADOS);
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], lista); 
			return mensajeRta;
		}
	}
}
