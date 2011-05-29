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


import vista.VentanaAplicacionGo;

public class ControladorGeneral implements Observer {

	private VentanaAplicacionGo ventana;
	private FullMoonGo fullMoonGo;
	private ControladorMenuInicio controladorMenuInicio;
	private ControladorTablero controladorTablero;
	private LanzadorRemoto lanzadorRemoto;
	private EstrategiaRemoto arbitro;
	private boolean conexionCancelada;
	
	public ControladorGeneral() {
		arbitro = null;
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
				arbitro.getRemoto().terminarHilo();
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
	
	private void iniciarJuegoComoServidor() {
		if(lanzadorRemoto.getResultadoConexion()) {
			System.out.println("Entro a crear el juego");
			fullMoonGo.crearJugador("Cliente Remoto", ColorPiedra.NEGRO, arbitro);
			
			CreadorEstrategia e = controladorMenuInicio.getEstrategiaJugadorBlanco();
			fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorBlanco(), ColorPiedra.BLANCO, e.crearEstrategia(fullMoonGo.getTablero(), ColorPiedra.BLANCO));
			
			iniciarFullMoon();
		} else {
			ventana.getVentanaEmergente().mostrarVentanaErrorAlLevantarServidor();
			arbitro = null;
		}
	}
	
	public void levantarServidor(int puerto){  /* el remoto es negro y yo soy blanco */
		
		arbitro = new EstrategiaRemotoServidor(ColorPiedra.NEGRO,ColorPiedra.BLANCO);
		lanzadorRemoto= new LanzadorRemoto(this);	
		lanzadorRemoto.setDatosConexion(arbitro.getRemoto(), puerto, Constantes.IP);
		lanzadorRemoto.start();
		ventana.getVentanaEmergente().mostrarVentanaEsperandoOponente();
		
		try {
			lanzadorRemoto.join();
		} catch (InterruptedException e) {
			System.err.println("Error al terminar el hilo de esperando oponente");
		}
		if(!conexionCancelada) 
			iniciarJuegoComoServidor();
		
		conexionCancelada= false;
	}
	
	public void jugarEnRed(String ip, int puerto) { /* el remoto es blanco y yo soy negro */
		
		arbitro = new EstrategiaRemotoCliente(ColorPiedra.BLANCO, ColorPiedra.NEGRO);
		
		if(arbitro.iniciarConexion(Constantes.IP, puerto)) { //obtener la ip de la vista!!!
			
			arbitro.enviarTamanioTablero();
			
			fullMoonGo.crearJugador("Servidor Remoto", ColorPiedra.BLANCO, arbitro);
			CreadorEstrategia e = controladorMenuInicio.getEstrategiaJugadorNegro();
			fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorNegro(), ColorPiedra.NEGRO, e.crearEstrategia(fullMoonGo.getTablero(), ColorPiedra.NEGRO));
			iniciarFullMoon();
			
		} else {
			ventana.getVentanaEmergente().mostrarVentanaErrorAlConectarseAlServidor();
			arbitro = null;
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
			
			if(arbitro != null) {
				arbitro.finDePartida();
				arbitro = null;	
			}
			
			if(fullMoonGo.getGanador() != null)
				ventana.mostrarGanador(fullMoonGo.getGanador().getNombre(), fullMoonGo.getGanador().getColor());
			else
				ventana.mostrarEmpate();
			
			fullMoonGo.reiniciarEstado();
			ventana.mostrarMenu();
		}		
	}
	
	public void ocultarVentanaEsperandoOponente() {
		ventana.getVentanaEmergente().ocultarVentanaEsperandoOponente();
	}
}
