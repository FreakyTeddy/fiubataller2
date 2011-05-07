package Remoto.GTP.ParsearMensajes;

import java.util.ArrayList;

import Remoto.GTP.Constantes;
import Remoto.GTP.Gtp;

public class CadenaGenMovimiento extends CadenaGtp {

	public CadenaGenMovimiento(Gtp gtp) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(mensaje.length <= 1)
			return cadenaSgte.enviarSgteCadena(mensaje);	
		if(!(mensaje[1].equals(Constantes.GENMOVE)) || mensaje[0].startsWith(Constantes.INICIO_MSJ_RTA))
			return cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Generar Movimiento");
			//TODO! a quien le pido esto?
			String movGenerado= "C3";
			ArrayList<String> lista= new ArrayList<String>();
			lista.add(movGenerado);
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], lista);   
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}
}
