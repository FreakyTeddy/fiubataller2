package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import Juego.Tablero;

public class AppWindow extends MouseAdapter{

	private JFrame frame;
	TableroGo tableroGo;
	/**
	 * Create the application.
	 */
	public AppWindow(Tablero tablero) {
		frame = new JFrame("GoGame");
		tableroGo = new TableroGo(tablero);
		
		frame.addMouseListener(this);

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

	public void mouseClicked(MouseEvent e){
		
		tableroGo.click(e.getX(), e.getY(), e.getButton()==MouseEvent.BUTTON1?0:1);
	}

}