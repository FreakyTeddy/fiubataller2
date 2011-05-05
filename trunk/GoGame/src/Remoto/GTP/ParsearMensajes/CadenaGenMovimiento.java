package Remoto.GTP.ParsearMensajes;

import Remoto.Servidor;
import Remoto.GTP.Constantes;

public class CadenaGenMovimiento extends CadenaGtp {

	public CadenaGenMovimiento(Servidor servidor) {
		super(servidor);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(mensaje.length <= 1)
			return cadenaSgte.enviarSgteCadena(mensaje);	
		if(!(mensaje[1].equals(Constantes.GENMOVE)))
			return cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Generar Movimiento");
			//TODO! a quien le pido esto?
			String movGenerado= "C3";
			String mensajeRta= Constantes.INICIO_MSJ_RTA + mensaje[0] + " " + movGenerado + Constantes.FIN_MSJ_RTA;
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}
}
