package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import Juego.Tablero;

public class AppWindow {

	private JFrame frame;
	TableroGo tableroGo;
	
	/**
	 * Create the application.
	 */
	public AppWindow(Tablero tablero) {
		frame = new JFrame("GoGame");
		tableroGo = new TableroGo(tablero);
		
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