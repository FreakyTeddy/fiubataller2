package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Remoto.Remoto;
import Remoto.GTP.Constantes;
import Remoto.GTP.GTP;

public class Test_GTP {
	
	private GTP gtp;
	private Remoto remoto;
	
	@Before
	public void setUp() throws Exception {
		remoto= new Remoto();
		gtp= new GTP(remoto);
	}

	@After
	public void tearDown() throws Exception {
		remoto= null;
		gtp= null;
	}
	
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
	/*
	@Test
	public void testCadena() {
		String mensaje= gtp.mensajeVersionProtocolo();
		gtp.procesarMensajeEntrante(mensaje);
		mensaje= gtp.mensajeNombre();
		gtp.procesarMensajeEntrante(mensaje);
		mensaje= gtp.mensajeVersion();
		gtp.procesarMensajeEntrante(mensaje);
		mensaje= gtp.mensajeListarCommandos();
		gtp.procesarMensajeEntrante(mensaje);
		mensaje= gtp.mensajeComandoSoportado(Constantes.QUIT);
		gtp.procesarMensajeEntrante(mensaje);
		mensaje= gtp.mensajeTamanioTablero(5);
		gtp.procesarMensajeEntrante(mensaje);
		mensaje= gtp.mensajeLimpiarTablero();
		gtp.procesarMensajeEntrante(mensaje);
		mensaje= gtp.mensajeJugar("black", "C5");
		gtp.procesarMensajeEntrante(mensaje);
		mensaje= gtp.mensajeGenMovimiento("white");
		gtp.procesarMensajeEntrante(mensaje);
		mensaje= gtp.mensajeKomi(5.5);
		gtp.procesarMensajeEntrante(mensaje);
		mensaje= "11 Mensaje cualquiera";
		gtp.procesarMensajeEntrante(mensaje);
		mensaje= gtp.mensajeLimpiarTablero();
		gtp.procesarMensajeEntrante(mensaje);
		mensaje= gtp.mensajeSalir();
		gtp.procesarMensajeEntrante(mensaje);
	}*/
	
	@Test
	public void testConexionAServidor() {
		remoto.iniciar();
	}
}
