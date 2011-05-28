package Remoto.GTP.ParsearMensajesEntrantes;

import java.util.ArrayList;

import Remoto.Remoto;
import Remoto.GTP.ConstantesGtp;
import Remoto.GTP.Gtp;

public class CadenaGenMovimiento extends CadenaGtp {

	public CadenaGenMovimiento(Gtp gtp, Remoto remoto) {
		super(gtp);
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(!verificarTipoMensaje(ConstantesGtp.GENMOVE, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			System.out.println("Cadena Generar Movimiento");
			//TODO! a quien le pido esto? --> al tablero? al controlador? cri cri
			String movGenerado= "C3";
			ArrayList<String> lista= new ArrayList<String>();
			lista.add(movGenerado);
			String mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], lista);   
			System.out.println("Respuesta " + mensajeRta);	
			return mensajeRta;
		}
	}
}
