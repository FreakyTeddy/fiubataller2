package Remoto.GTP.ParsearMensajesEntrantes;

import java.util.ArrayList;

import Juego.Constantes;
import Remoto.GTP.ConstantesGtp;
import Remoto.GTP.Gtp;

public class CadenaNombre extends CadenaGtp {

	public CadenaNombre(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(!verificarTipoMensaje(ConstantesGtp.NAME, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			System.out.println("Cadena Nombre");
			ArrayList<String> lista= new ArrayList<String>();
			lista.add(Constantes.NOMBRE_PROGRAMA);
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], lista);  
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}
}
