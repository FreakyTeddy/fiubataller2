package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;

import controlador.FullMoonGo;

import Juego.Tablero;

public class VentanaAplicacion {

	private JFrame frame;
	TableroGo tableroGo;
	
	/**
	 * Create the application.
	 */
	public VentanaAplicacion(FullMoonGo fmg) {
		frame = new JFrame("GoGame");
		tableroGo = new TableroGo(fmg.getTablero());
		
		frame.setBounds(100, 100, 500, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(tableroGo, BorderLayout.CENTER);
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
	
	

	
	public TableroGo getVistaTablero(){
		return tableroGo;
	}

}