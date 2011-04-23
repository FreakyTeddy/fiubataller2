package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import controlador.AdaptadorTablero;

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
		
		/*TODO: SI , ya se, codigo super redundante, pero me parece
		 * mucho mejor que el listener este en el panel y no el frame para
		 * eventos de tablero. Esto lo pongo asi igual porque cuando definamos que 
		 * va a hacer el controlador esto va a cambiar porque conceptualmente 
		 * no deberia la vista notificarse a si misma, sino avisar al modelo. 
		 */
		tableroGo.addMouseListener(new AdaptadorTablero(tableroGo));

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