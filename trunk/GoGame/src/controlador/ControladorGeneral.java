package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

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
	private boolean conexionCancelada;
	
	public ControladorGeneral() {
		conexion = null;
		fullMoonGo = FullMoonGo.getInstancia();
		fullMoonGo.addObserver(this);
	  ventana = new VentanaAplicacionGo();
	  conexionCancelada= false;
	  iniciarCallbacks();
	  iniciarControladores();
	}
	
	private void iniciarCallbacks() {
		JButton cancelar= ventana.getVentanaEmergente().getBotonCancelarEsperandoOponente();
		cancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ocultarVentanaEsperandoOponente();
				conexion.terminarHilo();
				conexionCancelada= true;
			}
		});
	}
	
	private void iniciarControladores() {
		controladorMenuInicio = new ControladorMenuInicio(ventana, this);
		controladorTablero= new ControladorTablero(ventana);
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
		lanzadorRemoto= new LanzadorRemoto(this);	
		lanzadorRemoto.setDatosConexion(conexion, puerto, Constantes.IP);
		lanzadorRemoto.start();
		ventana.getVentanaEmergente().mostrarVentanaEsperandoOponente();
		try {
			lanzadorRemoto.join();
		} catch (InterruptedException e) {
			System.err.println("Error al terminar el hilo de esperando oponente");
		}
		if(!conexionCancelada) {
			if(lanzadorRemoto.getResultadoConexion()) {
				System.out.println("Entro a crear el juego");
				fullMoonGo.crearJugador("Cliente Remoto", ColorPiedra.NEGRO, remoto);
				CreadorEstrategia e = controladorMenuInicio.getEstrategiaJugadorBlanco();
				fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorBlanco(), ColorPiedra.BLANCO, e.crearEstrategia(fullMoonGo.getTablero(), ColorPiedra.BLANCO));
				iniciarFullMoon();
			} else {
				ventana.getVentanaEmergente().mostrarVentanaErrorAlLevantarServidor();
				conexion = null;
			}
		}
		conexionCancelada= false;
	}
	
	public void jugarEnRed(String ip, int puerto) { /* el remoto es blanco y yo soy negro */
		
		EstrategiaRemoto remoto = new EstrategiaRemotoCliente(ColorPiedra.BLANCO, ColorPiedra.NEGRO);
		conexion = remoto.getRemoto();
		
		if(conexion.iniciar(Constantes.IP, puerto)) {
			
			conexion.enviarMensajeTamanioTablero(fullMoonGo.getTablero().getAncho());
			
			fullMoonGo.crearJugador("Servidor Remoto", ColorPiedra.BLANCO, remoto);
			CreadorEstrategia e = controladorMenuInicio.getEstrategiaJugadorNegro();
			fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorNegro(), ColorPiedra.NEGRO, e.crearEstrategia(fullMoonGo.getTablero(), ColorPiedra.NEGRO));
			iniciarFullMoon();
			
		} else {
			ventana.getVentanaEmergente().mostrarVentanaErrorAlConectarseAlServidor();
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
		ventana.getVentanaEmergente().ocultarVentanaEsperandoOponente();
	}
}
