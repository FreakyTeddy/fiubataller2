package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.PopupMenu;
import java.awt.event.MouseAdapter;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;


import Juego.FullMoonGo;
import Juego.Tablero;

public class VentanaAplicacionGo {

	private JFrame frame;
	TableroVista tableroGo;
	MenuInicio menuInicio;
	MouseAdapter mouseListener;
	
	/**
	 * Create the application.
	 * TODO: REvisar esto no esta bien...
	 */
	public VentanaAplicacionGo() {
		frame = new JFrame("GoGame");
		frame.setBounds(100, 100, 500, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuInicio = new MenuInicio(this);
		frame.getContentPane().add(menuInicio, BorderLayout.CENTER);
		EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}
	
	public void addMouseListener(MouseAdapter mouse) {
		mouseListener = mouse;
	}

	public void mostrarTablero(Tablero tb){
		frame.getContentPane().remove(menuInicio);
		tableroGo = new TableroVista(tb);
		tableroGo.addMouseListener(mouseListener);
		frame.getContentPane().add(tableroGo);
		frame.validate();
	}
	
}
