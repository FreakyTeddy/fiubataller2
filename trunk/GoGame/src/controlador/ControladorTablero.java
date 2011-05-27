package controlador;

import java.util.Observable;
import java.util.Observer;

import vista.TableroVista;
import vista.VentanaAplicacionGo;
import Juego.Tablero;

public class ControladorTablero implements Observer {

	private VentanaAplicacionGo ventanaPrincipal;
	
	public ControladorTablero(Tablero tablero, VentanaAplicacionGo ventanaPrincipal) {
		this.ventanaPrincipal= ventanaPrincipal;
		tablero.addObserver(this);
	}
	
	public void mostrarTablero(Tablero tablero) {
		ventanaPrincipal.mostrarTablero(tablero);
	}
	
	public void ocultarTablero() {

	}

	@Override
	public void update(Observable o, Object arg) {
		TableroVista tableroVista= ventanaPrincipal.getTablero();
		if(tableroVista != null)
			tableroVista.actualizar();
	}
}
