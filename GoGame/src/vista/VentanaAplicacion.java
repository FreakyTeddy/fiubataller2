package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;


import Juego.FullMoonGo;
import Juego.Tablero;

public class VentanaAplicacion implements Observer{

	private JFrame frame;
	TableroVista tableroGo;
	
	/**
	 * Create the application.
	 */
	public VentanaAplicacion(FullMoonGo fmg) {
		frame = new JFrame("GoGame");
		tableroGo = new TableroVista(fmg.getTablero());
		
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
	
	
	
	
	public TableroVista getVistaTablero(){
		return tableroGo;
	}




	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}