package Remoto.GTP.ParsearMensajesEntrantes;

import java.util.ArrayList;

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
			System.out.println("Cadena Jugar");
			//TODO: como se q es valido? --> preguntarle al arbitro :S
			boolean resultado= true;
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
}
