package controlador;

import java.util.Observable;
import java.util.Observer;

import Juego.ColorPiedra;
import Juego.Constantes;
import Juego.EstadoJuego;
import Juego.EstrategiaRemoto;
import Juego.FullMoonGo;


import Remoto.EstrategiaRemotoCliente;
import Remoto.EstrategiaRemotoServidor;
import Remoto.Remoto;


import vista.VentanaAplicacionGo;

public class ControladorGeneral implements Observer {

	private VentanaAplicacionGo ventana;
	private FullMoonGo fullMoonGo;
	private ControladorMenuInicio controladorMenuInicio;
	private ControladorTablero controladorTablero;
	private LanzadorRemoto lanzadorRemoto;
	private Remoto conexion;
	
	public ControladorGeneral() {
		conexion = null;
		fullMoonGo = FullMoonGo.getInstancia();
		fullMoonGo.addObserver(this);
	    ventana = new VentanaAplicacionGo();
	    iniciarControladores();
	}
	
	private void iniciarControladores() {
		controladorMenuInicio = new ControladorMenuInicio(ventana, this);
		controladorTablero= new ControladorTablero(ventana);
		lanzadorRemoto= new LanzadorRemoto(this);
	}

	public void iniciarJuegoGo() {
		ventana.iniciar();
	}
	
	private void iniciarFullMoon() {
		Thread juego = new Thread(fullMoonGo);
		juego.start();
		controladorTablero.mostrarTablero(fullMoonGo.getTablero());
	}
	
	public void levantarServidor(int puerto){  /* el remoto es negro y yo soy blanco */
		EstrategiaRemoto remoto = new EstrategiaRemotoServidor(ColorPiedra.NEGRO,ColorPiedra.BLANCO);
		conexion = remoto.getRemoto();
			
		lanzadorRemoto.setDatosConexion(conexion, puerto, Constantes.IP);
		lanzadorRemoto.start();
		ventana.mostrarVentanaEsperandoOponente();
		try {
			lanzadorRemoto.join();
		} catch (InterruptedException e) {
			System.err.println("Error al terminar el hilo de esperando oponente");
		}
		
		if(lanzadorRemoto.getResultadoConexion()) {
			fullMoonGo.crearJugador("Cliente Remoto", ColorPiedra.NEGRO, remoto);
			CreadorEstrategia e = controladorMenuInicio.getEstrategiaJugadorBlanco();
			fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorBlanco(), ColorPiedra.BLANCO, e.crearEstrategia(fullMoonGo.getTablero(), ColorPiedra.BLANCO));
			iniciarFullMoon();
			
		} else {
			System.out.println("TODO: ACA MOSTRAR MENSAJE DE QUE NO SE PUDO CONECTAR");
			conexion = null;
		}
	}
	
	public void jugarEnRed(String ip, int puerto) { /* el remoto es blanco y yo soy negro */
		
		EstrategiaRemoto remoto = new EstrategiaRemotoCliente(ColorPiedra.BLANCO, ColorPiedra.NEGRO);
		conexion = remoto.getRemoto();
		
		//TODO: Aca mientras conecta tendria que aparecer una pantalla
		if(conexion.iniciar(Constantes.IP, puerto)) {
			
			conexion.enviarMensajeTamanioTablero(fullMoonGo.getTablero().getAncho());
			
			fullMoonGo.crearJugador("Servidor Remoto", ColorPiedra.BLANCO, remoto);
			CreadorEstrategia e = controladorMenuInicio.getEstrategiaJugadorNegro();
			fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorNegro(), ColorPiedra.NEGRO, e.crearEstrategia(fullMoonGo.getTablero(), ColorPiedra.NEGRO));
			iniciarFullMoon();
			
		} else {
			System.out.println("TODO: ACA MOSTRAR MENSAJE DE QUE NO SE PUDO CONECTAR");
			conexion = null;
		}
	}
	
	public void jugarLocal() {
		CreadorEstrategia eB= controladorMenuInicio.getEstrategiaJugadorBlanco();
		fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorBlanco(), ColorPiedra.BLANCO, eB.crearEstrategia(fullMoonGo.getTablero(), ColorPiedra.BLANCO));
		CreadorEstrategia eN= controladorMenuInicio.getEstrategiaJugadorNegro();
		fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorNegro(), ColorPiedra.NEGRO, eN.crearEstrategia(fullMoonGo.getTablero(), ColorPiedra.NEGRO));
		iniciarFullMoon();
	}
	
	public FullMoonGo getFullMoon() {
		return fullMoonGo;
	}

	@Override
	public void update(Observable o, Object arg) { 
		if(fullMoonGo.getEstado() == EstadoJuego.TERMINADO){
			if(fullMoonGo.getGanador() != null)
				ventana.mostrarGanador(fullMoonGo.getGanador().getNombre(), fullMoonGo.getGanador().getColor());
			else
				ventana.mostrarEmpate();
			
			fullMoonGo.reiniciarEstado();
			ventana.mostrarMenu();
			if(conexion != null) {
				conexion.enviarMensajeSalida();
				conexion.terminarConexion();
				conexion = null;	
			}
			
		}		
	}
	
	public void ocultarVentanaEsperandoOponente() {
		ventana.ocultarVentanaEsperandoOponente();
	}
}
