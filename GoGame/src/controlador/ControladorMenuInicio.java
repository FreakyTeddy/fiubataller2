package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Juego.ColorPiedra;
import Juego.Estrategia;
import vista.MenuInicio;
import vista.VentanaAplicacionGo;

public class ControladorMenuInicio {

	ControladorGeneral controlador;
	MenuInicio menuInicio;
	
	public ControladorMenuInicio(VentanaAplicacionGo vistaJuego, ControladorGeneral controlador) {
		this.controlador= controlador;
		menuInicio= vistaJuego.getMenuInicio();
	}
	
	public void iniciarCallbacks() {
		//Boton jugar en red
		menuInicio.getBotonJugarEnRed().addActionListener(new BotonRemoto(menuInicio.getVentanaAplicacionGo().getFrame(), controlador));
		
		//Boton crear servidor
		menuInicio.getBotonCrearServidor().addActionListener(new BotonServidor(menuInicio.getVentanaAplicacionGo().getFrame(), controlador));
		
		//Boton jugar local
		menuInicio.getBotonJugarLocal().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controlador.jugarLocal();
			}
		});
		
		//Combo tamanio tablero
		menuInicio.getComboTamanioTablero().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controlador.getFullMoon().crearTablero();
			}
		});
	}
	
	public String getNombreJugadorBlanco() {
		return menuInicio.getJTextFieldNombreJugadorBlanco().getText();
	}
	
	public String getNombreJugadorNegro() {
		return menuInicio.getJTextFieldNombreJugadorNegro().getText();
	}
	
	public Estrategia getEstrategiaJugadorBlanco() {
		ComboJugador cj= menuInicio.getComboJugadorBlanco(); 
		return (cj.getEstrategiaSeleccionada((cj.getCombo().getSelectedIndex()))).crearEstrategia(controlador.getFullMoon().getTablero(), ColorPiedra.BLANCO);
	}
	
	public Estrategia getEstrategiaJugadorNegro() {
		ComboJugador cj= menuInicio.getComboJugadorNegro(); 
		return (cj.getEstrategiaSeleccionada((cj.getCombo().getSelectedIndex()))).crearEstrategia(controlador.getFullMoon().getTablero(), ColorPiedra.NEGRO);
	}
}
