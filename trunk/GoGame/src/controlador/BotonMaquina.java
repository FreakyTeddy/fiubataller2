package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonMaquina implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO: Crear un nuevo juego contra la estrategia.
		FullMoonGo.getInstancia().jugarVsPersona(false);

	}

}
