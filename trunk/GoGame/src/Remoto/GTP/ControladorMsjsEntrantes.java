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
	
	public ControladorMsjsEntrantes(GTP gtp) {
		iniciarCadenas(gtp);
		encadenarCadenas();
	}

	private void iniciarCadenas(GTP gtp) {
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
		cadenaDefault= new CadenaDefault(gtp);
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
