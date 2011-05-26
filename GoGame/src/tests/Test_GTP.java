package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Remoto.Cliente;
import Remoto.Remoto;
import Remoto.Servidor;
import Remoto.GTP.ConstantesGtp;
import Remoto.GTP.Gtp;
import Remoto.GTP.ProcesadorMsjsEntrantes;

public class Test_GTP {
	
	private Gtp gtp;
	private Remoto cliente;
	private Remoto servidor;
	private ProcesadorMsjsEntrantes procesador;
	
	@Before
	public void setUp() throws Exception {
		gtp= new Gtp();
		servidor= new Servidor();
		cliente= new Cliente();
		procesador= new ProcesadorMsjsEntrantes(cliente);
	}

	@After
	public void tearDown() throws Exception {
		gtp= null;
		cliente= null;
		servidor= null;
		procesador= null;
	}
	
	/* Test: Creacion de mensajes */
	@Test
	public void testMensajesAdministrativos(){
		String msjVersionProtocolo= gtp.mensajeVersionProtocolo();
		assertEquals(msjVersionProtocolo, "1 " + ConstantesGtp.PROTOCOL_VERSION + ConstantesGtp.FIN_MSJ);
		String msjSalir= gtp.mensajeSalir();
		assertEquals(msjSalir, "2 " + ConstantesGtp.QUIT + ConstantesGtp.FIN_MSJ);
	}

	@Test
	public void testMensajesIdentificacionPrograma(){
		String msjNombre= gtp.mensajeNombre();
		assertEquals(msjNombre, "1 " + ConstantesGtp.NAME + ConstantesGtp.FIN_MSJ);
		String msjVersion= gtp.mensajeVersion();
		assertEquals(msjVersion, "2 " + ConstantesGtp.VERSION + ConstantesGtp.FIN_MSJ);
	}

	@Test
	public void testMensajesComandos(){
		String msjComandoSoportado= gtp.mensajeComandoSoportado(ConstantesGtp.QUIT);
		assertEquals(msjComandoSoportado, "1 " + ConstantesGtp.KNOWN_COMMAND + " quit" + ConstantesGtp.FIN_MSJ);
		String msjListarComandos= gtp.mensajeListarCommandos();
		assertEquals(msjListarComandos, "2 " + ConstantesGtp.LIST_COMMANDS + ConstantesGtp.FIN_MSJ);
	}
	
	@Test
	public void testMensajesSetup(){
		String msjTamanioTablero= gtp.mensajeTamanioTablero(19);
		assertEquals(msjTamanioTablero, "1 " + ConstantesGtp.BOARDSIZE + " 19" + ConstantesGtp.FIN_MSJ);
		String msjLimpiarTablero= gtp.mensajeLimpiarTablero();
		assertEquals(msjLimpiarTablero, "2 " + ConstantesGtp.CLEAR_BOARD + ConstantesGtp.FIN_MSJ);
		String msjKomi= gtp.mensajeKomi(5.5);
		assertEquals(msjKomi, "3 " + ConstantesGtp.KOMI + " 5.5" + ConstantesGtp.FIN_MSJ);
	}
	
	@Test
	public void testMensajesJuego(){
		String msjJugar= gtp.mensajeJugar("black", "C5");
		assertEquals(msjJugar, "1 " + ConstantesGtp.PLAY + " black C5" + ConstantesGtp.FIN_MSJ);
		String msjGenMovimiento= gtp.mensajeGenMovimiento("white");
		assertEquals(msjGenMovimiento, "2 " + ConstantesGtp.GENMOVE + " white" + ConstantesGtp.FIN_MSJ);
	}
	
	@Test
	public void testCadena() {
		String mensaje= gtp.mensajeVersionProtocolo();
		procesador.procesarMensaje(mensaje);
		mensaje= gtp.mensajeNombre();
		procesador.procesarMensaje(mensaje);
		mensaje= gtp.mensajeVersion();
		procesador.procesarMensaje(mensaje);
		mensaje= gtp.mensajeListarCommandos();
		procesador.procesarMensaje(mensaje);
		mensaje= gtp.mensajeComandoSoportado(ConstantesGtp.QUIT);
		procesador.procesarMensaje(mensaje);
		mensaje= gtp.mensajeTamanioTablero(5);
		procesador.procesarMensaje(mensaje);
		mensaje= gtp.mensajeLimpiarTablero();
		procesador.procesarMensaje(mensaje);
		mensaje= gtp.mensajeJugar("black", "C5");
		procesador.procesarMensaje(mensaje);
		mensaje= gtp.mensajeGenMovimiento("white");
		procesador.procesarMensaje(mensaje);
		mensaje= gtp.mensajeKomi(5.5);
		procesador.procesarMensaje(mensaje);
		mensaje= "11 Mensaje cualquiera";
		procesador.procesarMensaje(mensaje);
		mensaje= gtp.mensajeSalir();
		procesador.procesarMensaje(mensaje);	
		mensaje= ConstantesGtp.PROTOCOL_VERSION + ConstantesGtp.FIN_MSJ;
		procesador.procesarMensaje(mensaje);
	}
	
	/* Probar contra el gnugo 
	 * gnugo --mode gtp --gtp-listen 1111
	 */
	@Test
	public void testConectarGnuGoModoListen() {
		System.out.println("=== TEST MODO CONNECT ===");
		if(cliente.iniciar("localhost", 1111)) {
			cliente.enviarMensajeJugar("black", "D5");
			cliente.enviarMensajeGenerarMovimiento("white");
			cliente.enviarMensajeSalida();
			cliente.terminarTest();
		}
		System.out.println("=== FIN: MODO CONNECT ===");
	}
	
	/* Probar contra el gnugo 
	 * gnugo --mode gtp --gtp-connect 1111
	 */
	@Test
	public void testConectarGnuGoModoConnect() {
		System.out.println("=== TEST MODO LISTEN ===");
		if(servidor.iniciar("localhost", 1111)) {
		System.out.println(">Enviando mensajes...");
	//	servidor.enviarMensaje(gtp.mensajeTamanioTablero(7));
	//	servidor.enviarMensaje(gtp.mensajeLimpiarTablero());
	//	servidor.enviarMensaje(gtp.mensajeComandoSoportado("boardsize"));
		servidor.enviarMensajeGenerarMovimiento("black");
	//	servidor.enviarMensaje(gtp.mensajeJugar("black", "C3"));
	//	servidor.enviarMensaje(gtp.mensajeJugar("black", "E3"));
	//	servidor.enviarMensajeSalida();
		servidor.terminarTest();
		}
		System.out.println("=== FIN: MODO LISTEN ===");
	}
}
