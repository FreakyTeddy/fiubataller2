package controlador;

import java.util.Observable;
import java.util.Observer;

import Juego.ColorPiedra;
import Juego.Constantes;
import Juego.EstadoJuego;
import Juego.EstrategiaRemoto;
import Juego.FullMoonGo;
import vista.VentanaAplicacionGo;

public class ControladorGeneral implements Observer {

	private VentanaAplicacionGo ventana;
	//Si si ya vi el singleton
	private FullMoonGo fullMoonGo;
	private ControladorMenuInicio controladorMenuInicio;
	
	public ControladorGeneral() {
		fullMoonGo= new FullMoonGo();
		fullMoonGo.addObserver(this);
	    ventana= new VentanaAplicacionGo();
	    iniciarControladorMenuInicio();
	}
	
	private void iniciarControladorMenuInicio() {
		controladorMenuInicio= new ControladorMenuInicio(ventana, this);
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
	
	public void levantarServidor(int puerto){
		EstrategiaRemoto remoto = new EstrategiaRemoto(fullMoonGo.getTablero(), true);
		//TODO: Aca mientras conecta tendria que aparecer una pantalla
		if(remoto.iniciarRemoto(puerto, Constantes.IP)) {
			fullMoonGo.crearJugador("Remoto", ColorPiedra.BLANCO, remoto);
			fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorNegro(), ColorPiedra.NEGRO, controladorMenuInicio.getEstrategiaJugadorNegro());
			iniciarFullMoon();
		} else {
			System.out.println("TODO: ACA MOSTRAR MENSAJE DE QUE NO SE PUDO CONECTAR");
		}
	}
	
	public void jugarEnRed(String ip, int puerto) {
		EstrategiaRemoto remoto = new EstrategiaRemoto(fullMoonGo.getTablero(), false);
		//TODO: Aca mientras conecta tendria que aparecer una pantalla
		if(remoto.iniciarRemoto(puerto, Constantes.IP)) {
			fullMoonGo.crearJugador("Remoto", ColorPiedra.BLANCO, remoto);
			fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorNegro(), ColorPiedra.NEGRO, controladorMenuInicio.getEstrategiaJugadorNegro());
			iniciarFullMoon();
		} else {
			System.out.println("TODO: ACA MOSTRAR MENSAJE DE QUE NO SE PUDO CONECTAR");			
		}
	}
	
	public void jugarLocal() {
		fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorBlanco(), ColorPiedra.BLANCO, controladorMenuInicio.getEstrategiaJugadorBlanco());
		fullMoonGo.crearJugador(controladorMenuInicio.getNombreJugadorNegro(), ColorPiedra.NEGRO, controladorMenuInicio.getEstrategiaJugadorNegro());
		iniciarFullMoon();
	}
	
	
	//TODO: por ahora
	public FullMoonGo getFullMoon() {
		return fullMoonGo;
	}

	@Override
	public void update(Observable o, Object arg) { 
		if(fullMoonGo.getEstado() == EstadoJuego.TERMINADO){
			ventana.mostrarGanador(fullMoonGo.getGanador().getNombre(), fullMoonGo.getGanador().getColor());
			fullMoonGo.reiniciarEstado();
			ventana.mostrarMenu();
		}		
	}
}
