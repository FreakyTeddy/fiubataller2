package Remoto.GTP.ParsearMensajesEntrantes;

import java.util.ArrayList;

import Juego.Posicion;
import Remoto.Remoto;
import Remoto.GTP.ConstantesGtp;
import Remoto.GTP.Gtp;

public class CadenaJugar extends CadenaGtp {

	private static final String MENSAJE_ERROR= "illegal move";
	private Remoto remoto;
	
	public CadenaJugar(Gtp gtp, Remoto remoto) {
		super(gtp);
		this.remoto = remoto;
	}
	
	@Override
	public String enviarSgteCadena(String[] mensaje) {
		if(!verificarTipoMensaje(ConstantesGtp.PLAY, mensaje))
			return cadenaSgte.enviarSgteCadena(mensaje);	
		else {
			System.out.println("Cadena Jugar : " + mensaje[3] + mensaje[2]);
			
			boolean resultado= informarAlArbitro(mensaje[3], mensaje[2]);

			String mensajeRta= "";
			if(resultado)
				mensajeRta= gtp.mensajeRespuestaOk(mensaje[0], null);
			else {
				ArrayList<String> lista= new ArrayList<String>();
				lista.add(MENSAJE_ERROR);
				mensajeRta= gtp.mensajeRespuestaError(mensaje[0], lista);
			}
			System.out.println("Respuesta " + mensajeRta);
			return mensajeRta;
		}
	}
	
	private boolean informarAlArbitro(String mensaje, String color) {
		boolean resultado;
		if (mensaje.equalsIgnoreCase(ConstantesGtp.PASAR_TURNO))
			resultado = remoto.getArbitro().setPosicionObtenida(null, color);
		else
			resultado = remoto.getArbitro().setPosicionObtenida(new Posicion(mensaje), color);
		return resultado;
	}	
	
}
