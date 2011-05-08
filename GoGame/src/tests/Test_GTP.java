package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Remoto.Cliente;
import Remoto.Servidor;
import Remoto.GTP.Constantes;
import Remoto.GTP.Gtp;
import Remoto.GTP.ProcesadorMsjsEntrantes;

public class Test_GTP {
	
	private Gtp gtp;
	private Cliente cliente;
	private Servidor servidor;
	private ProcesadorMsjsEntrantes procesador;
	
	@Before
	public void setUp() throws Exception {
		gtp= new Gtp();
		cliente= new Cliente();
		servidor= new Servidor(2222);
		procesador= new ProcesadorMsjsEntrantes();
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
		assertEquals(msjVersionProtocolo, "1 " + Constantes.PROTOCOL_VERSION + Constantes.FIN_MSJ);
		String msjSalir= gtp.mensajeSalir();
		assertEquals(msjSalir, "2 " + Constantes.QUIT + Constantes.FIN_MSJ);
	}

	@Test
	public void testMensajesIdentificacionPrograma(){
		String msjNombre= gtp.mensajeNombre();
		assertEquals(msjNombre, "1 " + Constantes.NAME + Constantes.FIN_MSJ);
		String msjVersion= gtp.mensajeVersion();
		assertEquals(msjVersion, "2 " + Constantes.VERSION + Constantes.FIN_MSJ);
	}

	@Test
	public void testMensajesComandos(){
		String msjComandoSoportado= gtp.mensajeComandoSoportado(Constantes.QUIT);
		assertEquals(msjComandoSoportado, "1 " + Constantes.KNOWN_COMMAND + " quit" + Constantes.FIN_MSJ);
		String msjListarComandos= gtp.mensajeListarCommandos();
		assertEquals(msjListarComandos, "2 " + Constantes.LIST_COMMANDS + Constantes.FIN_MSJ);
	}
	
	@Test
	public void testMensajesSetup(){
		String msjTamanioTablero= gtp.mensajeTamanioTablero(19);
		assertEquals(msjTamanioTablero, "1 " + Constantes.BOARDSIZE + " 19" + Constantes.FIN_MSJ);
		String msjLimpiarTablero= gtp.mensajeLimpiarTablero();
		assertEquals(msjLimpiarTablero, "2 " + Constantes.CLEAR_BOARD + Constantes.FIN_MSJ);
		String msjKomi= gtp.mensajeKomi(5.5);
		assertEquals(msjKomi, "3 " + Constantes.KOMI + " 5.5" + Constantes.FIN_MSJ);
	}
	
	@Test
	public void testMensajesJuego(){
		String msjJugar= gtp.mensajeJugar("black", "C5");
		assertEquals(msjJugar, "1 " + Constantes.PLAY + " black C5" + Constantes.FIN_MSJ);
		String msjGenMovimiento= gtp.mensajeGenMovimiento("white");
		assertEquals(msjGenMovimiento, "2 " + Constantes.GENMOVE + " white" + Constantes.FIN_MSJ);
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
		mensaje= gtp.mensajeComandoSoportado(Constantes.QUIT);
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
		mensaje= Constantes.PROTOCOL_VERSION + Constantes.FIN_MSJ;
		procesador.procesarMensaje(mensaje);
	}
	
	/* Probar contra el gnugo 
	 * gnugo --mode gtp --gtp-listen 1111
	 */
	@Test
	public void testConectarGnuGoModoListen() {
		System.out.println("=== TEST MODO CONNECT ===");
		boolean salida= cliente.iniciar("localhost", 1111);
		System.out.println("Salida: " + salida);
		cliente.enviarMensaje(gtp.mensajeVersion());
		cliente.enviarMensaje(gtp.mensajeVersionProtocolo());
		cliente.enviarMensaje("version #esto es un comentario");
		cliente.enviarMensaje("    ");
		cliente.enviarMensaje("boardsize\r");
		cliente.enviarMensaje(gtp.mensajeKomi(5.5));
		cliente.enviarMensaje(gtp.mensajeTamanioTablero(7));
		cliente.enviarMensaje(gtp.mensajeLimpiarTablero());
		cliente.enviarMensaje(gtp.mensajeNombre());
		cliente.enviarMensaje(gtp.mensajeListarCommandos());
		cliente.enviarMensaje(gtp.mensajeComandoSoportado("boardsize"));
		cliente.enviarMensaje(gtp.mensajeComandoSoportado("holaGNUGO"));     
		cliente.enviarMensaje(gtp.mensajeJugar("black", "D5"));
		cliente.enviarMensaje(gtp.mensajeGenMovimiento("white"));
		cliente.enviarMensaje(gtp.mensajeJugar("black", "C3"));
		cliente.enviarMensaje(gtp.mensajeJugar("black", "E3"));
		cliente.enviarMensaje("Cualquier cosa");
		cliente.enviarMensaje(gtp.mensajeSalir());
		cliente.terminarTest();
		System.out.println("=== FIN: MODO CONNECT ===");
	}
	
	/* Probar contra el gnugo 
	 * gnugo --mode gtp --gtp-listen 2222
	 */
/*	@Test
	public void testConectarGnuGoModoConnect() {
		System.out.println("=== TEST MODO LISTEN ===");
		servidor.iniciar();
		while(!servidor.estaClienteConectado()) {
			System.out.println("Esperando conexion...");			
		}
		System.out.println(">Enviando mensajes...");
		servidor.enviarMensaje(gtp.mensajeTamanioTablero(7));
		servidor.enviarMensaje(gtp.mensajeLimpiarTablero());
		servidor.enviarMensaje(gtp.mensajeComandoSoportado("boardsize"));
		servidor.enviarMensaje(gtp.mensajeJugar("black", "D5"));
		servidor.enviarMensaje(gtp.mensajeGenMovimiento("white"));
		servidor.enviarMensaje(gtp.mensajeJugar("black", "C3"));
		servidor.enviarMensaje(gtp.mensajeJugar("black", "E3"));
		servidor.enviarMensaje("Cualquier cosa");
		servidor.enviarMensaje(gtp.mensajeSalir());
		servidor.terminarTest();
		System.out.println("=== FIN: MODO LISTEN ===");
	}*/
}
