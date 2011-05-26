package controlador;

import java.util.Observable;
import java.util.Observer;

import Juego.ColorPiedra;
import Juego.Constantes;
import Juego.EstadoJuego;
import Juego.EstrategiaRemoto;
import Juego.FullMoonGo;

import Remoto.Cliente;
import Remoto.Remoto;
import Remoto.Servidor;

import vista.VentanaAplicacionGo;

public class ControladorGeneral implements Observer {

	private VentanaAplicacionGo ventana;
	private FullMoonGo fullMoonGo;
	private ControladorMenuInicio controladorMenuInicio;
	private Remoto conexion;
	
	public ControladorGeneral() {
		conexion = null;
		fullMoonGo = FullMoonGo.getInstancia();
		fullMoonGo.addObserver(this);
	    ventana = new VentanaAplicacionGo();
	    iniciarControladorMenuInicio();
	}
	
	private void iniciarControladorMenuInicio() {
		controladorMenuInicio = new ControladorMenuInicio(ventana, this);
		controladorMenuInicio.iniciarCallbacks();
	}

	public void iniciarJuegoGo() {
		ventana.iniciar();
	}
	
	private void iniciarFullMoon() {
		Thread juego = new Thread(fullMoonGo);
		juego.start();
		ventana.mostrarTablero(fullMoonGo.getTablero());
	}
	
	public void levantarServidor(int puerto){ /* yo soy negro y el remoto blanco */
		conexion = new Servidor();
			
		//TODO: Aca mientras conecta tendria que aparecer una pantalla
		if( conexion.iniciar(Constantes.IP, puerto) ) {
			
			EstrategiaRemoto remoto = new EstrategiaRemoto(conexion, ColorPiedra.BLANCO);
			conexion.setEstrategiaRemoto(remoto);
			conexion.enviarMensajeTamanioTablero(fullMoonGo.getTablero().getAncho());
			
			fullMoonGo.crearJugador("Remoto", ColorPiedra.BLANCO, remoto);
			fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorNegro(), ColorPiedra.NEGRO, controladorMenuInicio.getEstrategiaJugadorNegro());
			iniciarFullMoon();
			
		} else {
			System.out.println("TODO: ACA MOSTRAR MENSAJE DE QUE NO SE PUDO CONECTAR");
			conexion = null;
		}
	}
	
	public void jugarEnRed(String ip, int puerto) { /* yo soy blanco y el remoto negro */
		conexion = new Cliente();
		
		//TODO: Aca mientras conecta tendria que aparecer una pantalla
		if(conexion.iniciar(Constantes.IP, puerto)) {
			
			EstrategiaRemoto remoto = new EstrategiaRemoto(conexion, ColorPiedra.NEGRO);
			conexion.setEstrategiaRemoto(remoto);
			//conexion.enviarMensajeTamanioTablero(fullMoonGo.getTablero().getAncho());
			
			fullMoonGo.crearJugador("Remoto", ColorPiedra.NEGRO, remoto);
			fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorBlanco(), ColorPiedra.BLANCO, controladorMenuInicio.getEstrategiaJugadorBlanco());
			iniciarFullMoon();
			
		} else {
			System.out.println("TODO: ACA MOSTRAR MENSAJE DE QUE NO SE PUDO CONECTAR");
			conexion = null;
		}
	}
	
	public void jugarLocal() {
		fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorBlanco(), ColorPiedra.BLANCO, controladorMenuInicio.getEstrategiaJugadorBlanco());
		fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorNegro(), ColorPiedra.NEGRO, controladorMenuInicio.getEstrategiaJugadorNegro());
		iniciarFullMoon();
	}
	
	public FullMoonGo getFullMoon() {
		return fullMoonGo;
	}

	@Override
	public void update(Observable o, Object arg) { 
		if(fullMoonGo.getEstado() == EstadoJuego.TERMINADO){
			ventana.mostrarGanador(fullMoonGo.getGanador().getNombre(), fullMoonGo.getGanador().getColor());
			fullMoonGo.reiniciarEstado();
			ventana.mostrarMenu();
			conexion = null;
		}		
	}
}
