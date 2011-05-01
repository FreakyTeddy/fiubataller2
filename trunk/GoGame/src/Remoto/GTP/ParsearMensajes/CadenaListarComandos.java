package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;

public class CadenaListarComandos extends CadenaGTP {

	@Override
	public void enviarSgteCadena(String[] mensaje) {		
		if(!(mensaje[1].equals(Constantes.LIST_COMMANDS)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else
			System.out.println("Cadena Listar Comandos");
	}

}
