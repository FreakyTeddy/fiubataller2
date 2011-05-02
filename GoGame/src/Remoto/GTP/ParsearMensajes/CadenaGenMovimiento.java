package Remoto.GTP.ParsearMensajes;

import Remoto.GTP.Constantes;
import Remoto.GTP.GTP;

public class CadenaGenMovimiento extends CadenaGTP {

	public CadenaGenMovimiento(GTP gtp) {
		super(gtp);
	}

	@Override
	public void enviarSgteCadena(String[] mensaje) {
		if(!(mensaje[1].equals(Constantes.GENMOVE)))
			cadenaSgte.enviarSgteCadena(mensaje);
		else {
			System.out.println("Cadena Generar Movimiento");
			//TODO! a quien le pido esto?
			String movGenerado= "C3";
			String mensajeRta= Constantes.INICIO_MSJ_RTA + mensaje[0] + " " + movGenerado + Constantes.FIN_MSJ_RTA;
			System.out.println("Respuesta " + mensajeRta);	
		}
	}
}
