package tests;

import static org.junit.Assert.*;

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
		servidor= new Servidor(1234);
		procesador= new ProcesadorMsjsEntrantes(servidor);
	}

	@After
	public void tearDown() throws Exception {
		cliente= null;
		gtp= null;
	}
	
	/* Test: mensajes provenientes del servidor */
	
	/* Test: mensajes provenientes del cliente */
	@Test
	public void testMensajesAdministrativos(){
		String msjVersionProtocolo= gtp.mensajeVersionProtocolo();
		assertEquals(msjVersionProtocolo, "1 " + Constantes.PROTOCOL_VERSION + " 1\n");
		String msjSalir= gtp.mensajeSalir();
		assertEquals(msjSalir, "2 " + Constantes.QUIT + "\n");
	}

	@Test
	public void testMensajesIdentificacionPrograma(){
		String msjNombre= gtp.mensajeNombre();
		assertEquals(msjNombre, "1 " + Constantes.NAME + " GNU Go\n");
		String msjVersion= gtp.mensajeVersion();
		assertEquals(msjVersion, "2 " + Constantes.VERSION + " 1\n");
	}

	@Test
	public void testMensajesComandos(){
		String msjComandoSoportado= gtp.mensajeComandoSoportado(Constantes.QUIT);
		assertEquals(msjComandoSoportado, "1 " + Constantes.KNOWN_COMMAND + " quit\n");
		String msjListarComandos= gtp.mensajeListarCommandos();
		assertEquals(msjListarComandos, "2 "+ Constantes.LIST_COMMANDS + "\n");
	}
	
	@Test
	public void testMensajesSetup(){
		String msjTamanioTablero= gtp.mensajeTamanioTablero(19);
		assertEquals(msjTamanioTablero, "1 " + Constantes.BOARDSIZE + " 19\n");
		String msjLimpiarTablero= gtp.mensajeLimpiarTablero();
		assertEquals(msjLimpiarTablero, "2 " + Constantes.CLEAR_BOARD + "\n");
		String msjKomi= gtp.mensajeKomi(5.5);
		assertEquals(msjKomi, "3 " + Constantes.KOMI + " 5.5\n");
	}
	
	@Test
	public void testMensajesJuego(){
		String msjJugar= gtp.mensajeJugar("black", "C5");
		assertEquals(msjJugar, "1 " + Constantes.PLAY + " black C5\n");
		String msjGenMovimiento= gtp.mensajeGenMovimiento("white");
		assertEquals(msjGenMovimiento, "2 " + Constantes.GENMOVE + " white\n");
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
		mensaje= gtp.mensajeLimpiarTablero();
		procesador.procesarMensaje(mensaje);
		mensaje= gtp.mensajeSalir();
		procesador.procesarMensaje(mensaje);	
		mensaje= "Hola";
		procesador.procesarMensaje(mensaje);
	}
	
	@Test
	public void testConexionAServidor() {
		boolean salida= cliente.iniciar("localhost", 1234);
		System.out.println("Salida: " + salida);
        cliente.enviarMensaje(gtp.mensajeTamanioTablero(7));
        cliente.enviarMensaje(gtp.mensajeLimpiarTablero());
        cliente.enviarMensaje(gtp.mensajeJugar("black", "D5"));
        cliente.enviarMensaje(gtp.mensajeGenMovimiento("white"));
        cliente.enviarMensaje(gtp.mensajeJugar("black", "C3"));
        cliente.enviarMensaje(gtp.mensajeJugar("black", "E3"));
        cliente.enviarMensaje("Cualquier cosa");
        cliente.enviarMensaje(gtp.mensajeSalir());
        cliente.seEnvioMensajeSalida();
		cliente.terminar2();
	}
}
