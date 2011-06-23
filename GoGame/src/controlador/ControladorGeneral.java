package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import Juego.ColorPiedra;
import Juego.Constantes;
import Juego.EstadoJuego;
import Juego.Estrategia;
import Juego.FullMoonGo;

import Remoto.EstrategiaRemotoCliente;
import Remoto.EstrategiaRemotoServidor;
import Remoto.Arbitro;

import vista.SoundEffect;
import vista.VentanaAplicacionGo;

public class ControladorGeneral implements Observer {

	private VentanaAplicacionGo ventana;
	private FullMoonGo fullMoonGo;
	private ControladorMenuInicio controladorMenuInicio;
	private ControladorTablero controladorTablero;
	private LanzadorRemoto lanzadorRemoto;
	private Arbitro arbitro;
	private boolean conexionCancelada;
	
	public ControladorGeneral() {
		arbitro = null;
		lanzadorRemoto = null;
		
		fullMoonGo = FullMoonGo.getInstancia();
		fullMoonGo.addObserver(this);
		
		ventana = new VentanaAplicacionGo();
		conexionCancelada= false;
		
		iniciarCallbacks();
		iniciarControladores();
		
		SoundEffect.LOOP.loop();  
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
		
		ventana.getCheckBoxSonido().setSelected(true);
		ventana.getCheckBoxSonido().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.DESELECTED){
					SoundEffect.volume = SoundEffect.Volume.MUTE;
					SoundEffect.LOOP.stop();
				}else{
					SoundEffect.volume = SoundEffect.Volume.NOT_MUTE;
					SoundEffect.LOOP.loop();
				}
			}
		});
	}
	
	void ocultarVentanaEsperandoOponente() {
		ventana.getVentanaEmergente().ocultarVentanaEsperandoOponente();
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
	
	private void crearJuegoComoServidor() {
		if(lanzadorRemoto.getResultadoConexion()) {
			
			Estrategia estrategia = controladorMenuInicio.getEstrategiaJugadorBlanco().crearEstrategia(ColorPiedra.BLANCO);
			fullMoonGo.crearJugador("Cliente Remoto", ColorPiedra.NEGRO, arbitro.getEstrategia());
			fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorBlanco(), ColorPiedra.BLANCO, estrategia);
			
		} else {
			ventana.getVentanaEmergente().mostrarVentanaErrorAlLevantarServidor();
			arbitro = null;
		}
	}
	
	void levantarServidor(int puerto){  /* el remoto es negro y yo soy blanco */
		
		arbitro = new EstrategiaRemotoServidor(ColorPiedra.NEGRO,ColorPiedra.BLANCO);
		lanzadorRemoto= new LanzadorRemoto(this);	
		lanzadorRemoto.setDatosConexion(arbitro, puerto, Constantes.IP);
		lanzadorRemoto.start();
		ventana.getVentanaEmergente().mostrarVentanaEsperandoOponente();
		
		try {
			lanzadorRemoto.join();
		} catch (InterruptedException e) {
			System.err.println("Error al terminar el hilo de esperando oponente");
		}
		if(!conexionCancelada) 
			crearJuegoComoServidor();
		
		conexionCancelada= false;
	}
	
	void jugarEnRed(String ip, int puerto) { /* el remoto es blanco y yo soy negro */
		
		arbitro = new EstrategiaRemotoCliente(ColorPiedra.BLANCO, ColorPiedra.NEGRO);
		
		if(arbitro.iniciarConexion(ip, puerto)) { 
			
			fullMoonGo.crearTablero(controladorMenuInicio.getTamanioTablero());
			arbitro.enviarTamanioTablero();
			
			Estrategia estrategia = controladorMenuInicio.getEstrategiaJugadorNegro().crearEstrategia(ColorPiedra.NEGRO);

			fullMoonGo.crearJugador("Servidor Remoto", ColorPiedra.BLANCO, arbitro.getEstrategia());
			fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorNegro(), ColorPiedra.NEGRO, estrategia);
			
		} else {
			ventana.getVentanaEmergente().mostrarVentanaErrorAlConectarseAlServidor();
			arbitro = null;
		}
	}
	
	void jugarLocal() {
		fullMoonGo.crearTablero(controladorMenuInicio.getTamanioTablero());
		
		Estrategia eB= controladorMenuInicio.getEstrategiaJugadorBlanco().crearEstrategia(ColorPiedra.BLANCO);
		Estrategia eN= controladorMenuInicio.getEstrategiaJugadorNegro().crearEstrategia(ColorPiedra.NEGRO);
		
		fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorBlanco(), ColorPiedra.BLANCO, eB);
		fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorNegro(), ColorPiedra.NEGRO, eN);

	}
	
	FullMoonGo getFullMoon() {
		return fullMoonGo;
	}

	@Override
	public void update(Observable o, Object arg) { 
		
		if(fullMoonGo.getEstado() == EstadoJuego.LISTO_PARA_INICIAR) {
			iniciarFullMoon();
		}
		
		if(fullMoonGo.getEstado() == EstadoJuego.INICIADO) {
			mostrarTurnoDeJugador();
		}
		
		if(fullMoonGo.getEstado() == EstadoJuego.TERMINADO){
			finalizarPartidaFullMoon();	
		}		
	}
	
	private void mostrarTurnoDeJugador() {
		System.out.println("Turno " + fullMoonGo.getJugadorDeTurno().getNombre());
	}
	
	private void finalizarPartidaFullMoon() {
		
		
		if(arbitro != null) 
			arbitro.finalizarPartida();
		
		arbitro = null;	
		lanzadorRemoto = null;
		
				
		if(fullMoonGo.getGanador() != null) {
			System.out.println("Fin de la Partida. Ganador: " + fullMoonGo.getGanador().getNombre());
			ventana.mostrarGanador(fullMoonGo.getGanador().getNombre(), fullMoonGo.getGanador().getColor());
		}
		else {
			System.out.println("Fin de la Partida. " + FullMoonGo.getInstancia().getMsjFinDeJuego());
			ventana.mostrarEmpate(FullMoonGo.getInstancia().getMsjFinDeJuego());
		}
		
		fullMoonGo.reiniciarEstado();
		ventana.mostrarMenu();
		
	}
	
}
