package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.PopupMenu;
import java.awt.event.MouseAdapter;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


import Juego.FullMoonGo;
import Juego.Tablero;

public class VentanaAplicacionGo {

	private JFrame frame;
	private TableroVista tableroGo;
	private MenuInicio menuInicio;
	private MouseAdapter mouseListener;
	
	private static final String pathIcono = "./images/icono.jpg";
	
	/**
	 * Create the application.
	 * TODO: REvisar esto no esta bien...
	 */
	public VentanaAplicacionGo() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			System.out.println("Error al cargar el look and feel");
		}
		
		frame = new JFrame("FullMoonGo");
		frame.setBounds(100, 100, 500, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage((new ImageIcon(pathIcono)).getImage());
		
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
