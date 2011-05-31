package vista;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import Juego.ColorPiedra;
import Juego.Tablero;

public class VentanaAplicacionGo {

	private JFrame frame;
	private TableroVista tableroGo;
	private MenuInicio menuInicio;
	private MouseAdapter mouseListener;
	private VentanaEmergente ventanaEmergente;
	
	public VentanaAplicacionGo() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			System.out.println("Error al cargar el look and feel");
		}
		
		frame = new JFrame("FullMoon Go");
		frame.setBounds(100, 100, 500, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage((new ImageIcon(PathImages.Icono)).getImage());
		
		menuInicio = new MenuInicio(this);
		frame.getContentPane().add(menuInicio, BorderLayout.CENTER);
		
		ventanaEmergente= new VentanaEmergente(frame);
	}
	
	public void iniciar() {
		frame.setVisible(true);
	}
	
	public void addMouseListener(MouseAdapter mouse) {
		mouseListener = mouse;
	}
	
	public void mostrarTablero(Tablero tablero){
		frame.getContentPane().remove(menuInicio);
		tableroGo = new TableroVista(tablero);
		tableroGo.addMouseListener(mouseListener);
		frame.getContentPane().add(tableroGo);
		frame.validate();
		frame.repaint();
	}
	
	public void mostrarMenu() {
		frame.getContentPane().remove(tableroGo);
		frame.getContentPane().add(menuInicio);
		frame.validate();	
		frame.repaint();
	}
	
	public MenuInicio getMenuInicio() {
		return menuInicio;
	}
	
	public void mostrarGanador(String nombre, ColorPiedra color) { //TODO se puede enchular =P
		JOptionPane.showMessageDialog(frame,"El ganador es " + nombre + " - " + color,
			    "FullMoonGo", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void mostrarEmpate(String msj) { //TODO se puede enchular =P
		JOptionPane.showMessageDialog(frame,"Empate - " + msj,
			    "FullMoonGo", JOptionPane.PLAIN_MESSAGE);
	}
	
	public JFrame getFramePrincipal() {
		return frame;
	}

	public TableroVista getTablero() {
		return tableroGo;
	}
	
	public VentanaEmergente getVentanaEmergente() {
		return ventanaEmergente;
	}
}
