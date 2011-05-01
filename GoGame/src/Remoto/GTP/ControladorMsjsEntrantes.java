package Remoto.GTP;

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
	
	public ControladorMsjsEntrantes() {
		iniciarCadenas();
		encadenarCadenas();
	}

	private void iniciarCadenas() {
		cadenaVersionProtocolo= new CadenaVersionProtocolo();
		cadenaNombre= new CadenaNombre();
		cadenaVersion= new CadenaVersion();
		cadenaComandoSoportado= new CadenaComandoSoportado();
		cadenaListarComandos= new CadenaListarComandos();
		cadenaTamanioTablero= new CadenaTamanioTablero();
		cadenaLimpiarTablero= new CadenaLimpiarTablero();
		cadenaKomi= new CadenaKomi();
		cadenaJugar= new CadenaJugar();
		cadenaGenMovimiento= new CadenaGenMovimiento();
		cadenaSalida= new CadenaSalida();
		cadenaDefault= new CadenaDefault();
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
	
	public void procesarMensaje(String mensaje) {
		String delimitadores = DELIMITADORES;
		String[] palabras= mensaje.split(delimitadores);
		cadenaVersionProtocolo.enviarSgteCadena(palabras);
	}
}
