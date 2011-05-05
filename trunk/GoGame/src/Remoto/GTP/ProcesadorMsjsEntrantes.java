package Remoto.GTP;

import Remoto.Servidor;
import Remoto.GTP.ParsearMensajes.CadenaComandoSoportado;
import Remoto.GTP.ParsearMensajes.CadenaDefault;
import Remoto.GTP.ParsearMensajes.CadenaGtp;
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

public class ProcesadorMsjsEntrantes extends ProcesadorMsjs {

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
	private CadenaGtp cadenaDefault;
	
	public ProcesadorMsjsEntrantes(Servidor servidor) {
		iniciarCadenas(servidor);
		encadenarCadenas();
	}

	private void iniciarCadenas(Servidor servidor) {
		cadenaVersionProtocolo= new CadenaVersionProtocolo(servidor);
		cadenaNombre= new CadenaNombre(servidor);
		cadenaVersion= new CadenaVersion(servidor);
		cadenaComandoSoportado= new CadenaComandoSoportado(servidor);
		cadenaListarComandos= new CadenaListarComandos(servidor);
		cadenaTamanioTablero= new CadenaTamanioTablero(servidor);
		cadenaLimpiarTablero= new CadenaLimpiarTablero(servidor);
		cadenaKomi= new CadenaKomi(servidor);
		cadenaJugar= new CadenaJugar(servidor);
		cadenaGenMovimiento= new CadenaGenMovimiento(servidor);
		cadenaSalida= new CadenaSalida(servidor);
		cadenaDefault= new CadenaDefault(servidor);
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
		String[] palabras= mensaje.split(DELIMITADORES);
		return cadenaVersionProtocolo.enviarSgteCadena(palabras);
	}
}
