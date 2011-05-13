package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonJugador implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO: Crear un nuevo juego contra un jugador.
		FullMoonGo.getInstancia().jugarVsPersona();
		

	}

}
