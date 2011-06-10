package Remoto.GTP.ParsearMensajesEntrantes;

import java.util.ArrayList;

import Juego.Constantes;
import Remoto.Remoto;
import Remoto.GTP.ConstantesGtp;
import Remoto.GTP.Gtp;

public class CadenaTamanioTablero extends CadenaGtp {

	private static final String ERROR_TAMANIO_TABLERO = "unacceptable boardsize";
	private Remoto remoto;
	
	public CadenaTamanioTablero(Gtp gtp, Remoto remoto) {
		super(gtp);
		this.remoto = remoto;
	}

	@Override
	public String enviarSgteCadena(String[] mensaje) {	
		if(!verificarTipoMensaje(ConstantesGtp.BOARDSIZE, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			int boardsize;
			if(mensaje.length == 3)
				boardsize= Integer.parseInt(mensaje[2]);
			else
				boardsize= Integer.parseInt(mensaje[1]);
			String mensajeRta;
			if(boardsize < Constantes.MIN_TABLERO || boardsize > Constantes.MAX_TABLERO) {
				ArrayList<String> lista= new ArrayList<String>();
				lista.add(ERROR_TAMANIO_TABLERO);
				mensajeRta= gtp.mensajeRespuestaError(mensaje[0], lista);
			} else {
				mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], null);
				remoto.getArbitro().setTamanioTablero(boardsize);				
			}
			return mensajeRta;
		}
	}
}
