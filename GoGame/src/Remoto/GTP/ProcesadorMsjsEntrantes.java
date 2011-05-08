package Remoto.GTP;

import Remoto.GTP.ParsearMensajes.CadenaComandoSoportado;
import Remoto.GTP.ParsearMensajes.CadenaDefault;
import Remoto.GTP.ParsearMensajes.CadenaGtp;
import Remoto.GTP.ParsearMensajes.CadenaGenMovimiento;
import Remoto.GTP.ParsearMensajes.CadenaJugar;
import Remoto.GTP.ParsearMensajes.CadenaKomi;
import Remoto.GTP.ParsearMensajes.CadenaLimpiarTablero;
import Remoto.GTP.ParsearMensajes.CadenaListarComandos;
import Remoto.GTP.ParsearMensajes.CadenaNombre;
import Remoto.GTP.ParsearMensajes.CadenaRtaMsjsServidor;
import Remoto.GTP.ParsearMensajes.CadenaSalida;
import Remoto.GTP.ParsearMensajes.CadenaTamanioTablero;
import Remoto.GTP.ParsearMensajes.CadenaVersion;
import Remoto.GTP.ParsearMensajes.CadenaVersionProtocolo;

public class ProcesadorMsjsEntrantes {

	private static String CARACTERES_CONTROL= "[\r]";
	private static String COMENTARIOS= "#.*$";
	private static String TAB_HORIZONTAL= "[\t]";
	private static String CADENA_VACIA= "^ *$";
	private static String DELIMITADORES= "[ \n]";
	
	private CadenaGtp cadenaVersionProtocolo;
	private CadenaGtp cadenaNombre;
	private CadenaGtp cadenaVersion;
	private CadenaGtp cadenaComandoSoportado;
	private CadenaGtp cadenaListarComandos;
	private CadenaGtp cadenaTamanioTablero;
	private CadenaGtp cadenaLimpiarTablero;
	private CadenaGtp cadenaKomi;
	private CadenaGtp cadenaJugar;
	private CadenaGtp cadenaGenMovimiento;
	private CadenaGtp cadenaSalida;
	private CadenaGtp cadenaRtaMsjsServidor;
	private CadenaGtp cadenaDefault;
	
	public ProcesadorMsjsEntrantes() {
		iniciarCadenas(new Gtp());
		encadenarCadenas();
	}

	private void iniciarCadenas(Gtp gtp) {
		cadenaVersionProtocolo= new CadenaVersionProtocolo(gtp);
		cadenaNombre= new CadenaNombre(gtp);
		cadenaVersion= new CadenaVersion(gtp);
		cadenaComandoSoportado= new CadenaComandoSoportado(gtp);
		cadenaListarComandos= new CadenaListarComandos(gtp);
		cadenaTamanioTablero= new CadenaTamanioTablero(gtp);
		cadenaLimpiarTablero= new CadenaLimpiarTablero(gtp);
		cadenaKomi= new CadenaKomi(gtp);
		cadenaJugar= new CadenaJugar(gtp);
		cadenaGenMovimiento= new CadenaGenMovimiento(gtp);
		cadenaSalida= new CadenaSalida(gtp);
		cadenaRtaMsjsServidor = new CadenaRtaMsjsServidor(gtp);
		cadenaDefault= new CadenaDefault(gtp);
	}
	
	private void encadenarCadenas() {
		cadenaRtaMsjsServidor.agregarCadena(cadenaVersionProtocolo);
		cadenaVersionProtocolo.agregarCadena(cadenaNombre);
		cadenaNombre.agregarCadena(cadenaVersion);
		cadenaVersion.agregarCadena(cadenaComandoSoportado);
		cadenaComandoSoportado.agregarCadena(cadenaListarComandos);
		cadenaListarComandos.agregarCadena(cadenaTamanioTablero);
		cadenaTamanioTablero.agregarCadena(cadenaLimpiarTablero);
		cadenaLimpiarTablero.agregarCadena(cadenaKomi);
		cadenaKomi.agregarCadena(cadenaJugar);
		cadenaJugar.agregarCadena(cadenaGenMovimiento);
		cadenaGenMovimiento.agregarCadena(cadenaSalida);
		cadenaSalida.agregarCadena(cadenaDefault);
	}
	
	public String procesarMensaje(String mensaje) {
		//1. Eliminar caracteres de control (Ej. CR)
		mensaje= mensaje.replaceAll(CARACTERES_CONTROL, ""); 
		//2. Eliminar los comentarios
		mensaje= mensaje.replaceFirst(COMENTARIOS, ""); 
		//3. Convertir HT por espacios
		mensaje= mensaje.replaceAll(TAB_HORIZONTAL, " "); 
		//4. Descartar lineas vacias
		mensaje= mensaje.replaceFirst(CADENA_VACIA, "");
		if(mensaje.equals("")) {
			System.out.println("Cadena Vacia");
			return "";
		}
		String[] palabras= mensaje.split(DELIMITADORES);
		return cadenaRtaMsjsServidor.enviarSgteCadena(palabras);
	}
}
