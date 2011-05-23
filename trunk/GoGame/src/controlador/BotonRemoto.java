package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Juego.FullMoonGo;

public class BotonRemoto implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Jugar contra remoto!!");
		FullMoonGo.getInstancia().jugarContraPersona(false);
	}

}
