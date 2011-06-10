package controlador;

import java.util.Observable;
import java.util.Observer;

import vista.TableroVista;
import vista.VentanaAplicacionGo;
import Juego.Tablero;

public class ControladorTablero implements Observer {

	private VentanaAplicacionGo ventanaPrincipal;
	
	public ControladorTablero(VentanaAplicacionGo ventanaPrincipal) {
		this.ventanaPrincipal= ventanaPrincipal;
	}
	
	void mostrarTablero(Tablero tablero) {
		tablero.addObserver(this);
		ventanaPrincipal.mostrarTablero(tablero);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		TableroVista tableroVista= ventanaPrincipal.getTablero();
		if(tableroVista != null)
			tableroVista.actualizar();
	}
}
