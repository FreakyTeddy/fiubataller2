package controlador;

import Juego.FullMoonGo;
import vista.AppWindow;

public class ControladorJuego {

	public FullMoonGo juego;
	
	public ControladorJuego() {
		juego = FullMoonGo.getInstancia();
	}
	
	public void iniciar(){
		juego.nuevaPartida();
		new AppWindow(juego.getTablero());
		//		juego.jugar();
	}
	
	public static void main(String[] args) {
		
		ControladorJuego controlador = new ControladorJuego();
		controlador.iniciar();
	}
}
