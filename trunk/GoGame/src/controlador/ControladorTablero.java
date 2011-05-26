package controlador;

import java.util.Observable;
import java.util.Observer;

import vista.TableroVista;
import vista.VentanaAplicacionGo;
import Juego.Tablero;

public class ControladorTablero implements Observer {

	private Tablero tablero;
	private VentanaAplicacionGo ventanaPrincipal;
	
	public ControladorTablero(Tablero tablero, VentanaAplicacionGo ventanaPrincipal) {
		this.tablero= tablero;
		this.ventanaPrincipal= ventanaPrincipal;
		tablero.addObserver(this);
	}
	
	public void mostrarTablero() {
		ventanaPrincipal.mostrarTablero(tablero);
	}
	
	public void ocultarTablero() {

	}

	@Override
	public void update(Observable o, Object arg) {
		TableroVista tableroVista= ventanaPrincipal.getTablero();
		tableroVista.actualizar();
	}
}
