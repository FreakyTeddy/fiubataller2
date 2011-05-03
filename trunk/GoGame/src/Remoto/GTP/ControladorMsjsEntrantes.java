package Remoto.GTP;

import Remoto.Remoto;
import Remoto.GTP.ParsearMensajes.CadenaComandoSoportado;
import Remoto.GTP.ParsearMensajes.CadenaDefault;
import Remoto.GTP.ParsearMensajes.CadenaGTP;
import Remoto.GTP.ParsearMensajes.CadenaGenMovimiento;
import Remoto.GTP.ParsearMensajes.CadenaJugar;
import Remoto.GTP.ParsearMensajes.CadenaKomi;
import Remoto.GTP.ParsearMensajes.CadenaLimpiarTablero;
import Remoto.GTP.ParsearMensajes.CadenaListarComandos;
import Remoto.GTP.ParsearMensajes.CadenaNombre;
import Remoto.GTP.ParsearMensajes.CadenaSalida;
import Remoto.GTP.ParsearMensajes.CadenaTamanioTablero;
import Remoto.GTP.ParsearMensajes.CadenaVersion;
import Remoto.GTP.ParsearMensajes.CadenaVersionProtocolo;

public class ControladorMsjsEntrantes {

	private CadenaGTP cadenaVersionProtocolo;
	private CadenaGTP cadenaNombre;
	private CadenaGTP cadenaVersion;
	private CadenaGTP cadenaComandoSoportado;
	private CadenaGTP cadenaListarComandos;
	private CadenaGTP cadenaTamanioTablero;
	private CadenaGTP cadenaLimpiarTablero;
	private CadenaGTP cadenaKomi;
	private CadenaGTP cadenaJugar;
	private CadenaGTP cadenaGenMovimiento;
	private CadenaGTP cadenaSalida;
	private CadenaGTP cadenaDefault;
	
	private static String DELIMITADORES= "[ \n]";
	
	public ControladorMsjsEntrantes(Remoto remoto) {
		iniciarCadenas(remoto);
		encadenarCadenas();
	}

	private void iniciarCadenas(Remoto remoto) {
		cadenaVersionProtocolo= new CadenaVersionProtocolo(remoto);
		cadenaNombre= new CadenaNombre(remoto);
		cadenaVersion= new CadenaVersion(remoto);
		cadenaComandoSoportado= new CadenaComandoSoportado(remoto);
		cadenaListarComandos= new CadenaListarComandos(remoto);
		cadenaTamanioTablero= new CadenaTamanioTablero(remoto);
		cadenaLimpiarTablero= new CadenaLimpiarTablero(remoto);
		cadenaKomi= new CadenaKomi(remoto);
		cadenaJugar= new CadenaJugar(remoto);
		cadenaGenMovimiento= new CadenaGenMovimiento(remoto);
		cadenaSalida= new CadenaSalida(remoto);
		cadenaDefault= new CadenaDefault(remoto);
	}
	
	private void encadenarCadenas() {
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
		String delimitadores = DELIMITADORES;
		String[] palabras= mensaje.split(delimitadores);
		return cadenaVersionProtocolo.enviarSgteCadena(palabras);
	}
}
