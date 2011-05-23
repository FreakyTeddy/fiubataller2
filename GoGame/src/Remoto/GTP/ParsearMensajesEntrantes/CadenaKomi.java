package Remoto.GTP.ParsearMensajesEntrantes;

import java.util.ArrayList;

import Remoto.GTP.ConstantesGtp;
import Remoto.GTP.Gtp;

public class CadenaKomi extends CadenaGtp {

	private static final String ERROR_KOMI = "komi not a float";

	public CadenaKomi(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(!verificarTipoMensaje(ConstantesGtp.KOMI, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			System.out.println("Cadena Komi");
			double komi;
			System.out.println(mensaje[0]);
			System.out.println(mensaje[1]);
			System.out.println(mensaje[2]);
			
			if(mensaje.length == 3)
				komi= Double.parseDouble(mensaje[2]);
			else
				komi= Double.parseDouble(mensaje[1]);
			String mensajeRta;
			if(komi < 1) {
				ArrayList<String> lista= new ArrayList<String>();
				lista.add(ERROR_KOMI);
				mensajeRta= gtp.mensajeRespuestaError(mensaje[0], lista);
			} else
				mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], null);  
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}
}
