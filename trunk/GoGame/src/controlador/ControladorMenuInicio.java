package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import Juego.TamanioTablero;

import vista.MenuInicio;
import vista.VentanaAplicacionGo;

public class ControladorMenuInicio {

	private ControladorGeneral controlador;
	private ControladorRemoto controladorRemoto;
	private MenuInicio menuInicio;
	private VentanaAplicacionGo vistaJuego;
	
	private String tamanios[] = {"9x9", "13x13", "19x19"};
	private String estrategias[] = {"Humano", "Minimax", "Muy Facil", "Facil", "Medio"};
	private ArrayList<CreadorEstrategia> creadores;
	private ArrayList<TamanioTablero> tamanioTablero;
	
	public ControladorMenuInicio(VentanaAplicacionGo vistaJuego, ControladorGeneral controlador) {
		this.controlador= controlador;
		this.vistaJuego= vistaJuego;
		menuInicio= vistaJuego.getMenuInicio();
		controladorRemoto= new ControladorRemoto(controlador, vistaJuego);
		cargarDatos();
		iniciarCallbacks();
	}
	
	private void cargarDatos() {
		JComboBox comboBoxTamanios= menuInicio.getComboTamanioTablero();
		for(int i = 0; i < tamanios.length; i++)
			comboBoxTamanios.addItem(tamanios[i]);

		tamanioTablero = new ArrayList<TamanioTablero>();
		tamanioTablero.add(TamanioTablero.NUEVE);
		tamanioTablero.add(TamanioTablero.TRECE);
		tamanioTablero.add(TamanioTablero.DIECINUEVE);

		creadores = new ArrayList<CreadorEstrategia>();
		creadores.add(new CreadorEstrategiaHumano(vistaJuego));
		creadores.add(new CreadorEstrategiaMinMax());
		creadores.add(new CreadorEstrategiaMuyFacil());
		creadores.add(new CreadorEstrategiaFacil());
		creadores.add(new CreadorEstrategiaMedio());
	  
		cargarComboJugador(menuInicio.getComboJugadorBlanco());
		cargarComboJugador(menuInicio.getComboJugadorNegro());
	}
	
	private void cargarComboJugador(JComboBox comboBox) {
	  for(int i = 0; i < estrategias.length; i++)
      comboBox.addItem(estrategias[i]);
	}
	
	private void iniciarCallbacks() {
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
				
				controlador.getFullMoon().crearTablero(
					tamanioTablero.get(menuInicio.getComboTamanioTablero().getSelectedIndex()));
			}
		});
	}
	
	public void mostrarMenuInicio() {
		
	}
	
	public void ocultarMenuInicio() {
		vistaJuego.getFramePrincipal().getContentPane().remove(menuInicio);
	}
	
	public String getNombreJugadorBlanco() {
		return menuInicio.getJTextFieldNombreJugadorBlanco().getText();
	}
	
	public String getNombreJugadorNegro() {
		return menuInicio.getJTextFieldNombreJugadorNegro().getText();
	}
	
	public CreadorEstrategia getEstrategiaJugadorBlanco() {
		JComboBox comboBoxBlanco= menuInicio.getComboJugadorBlanco(); 
		return (creadores.get(comboBoxBlanco.getSelectedIndex()));
	}
	
	public CreadorEstrategia getEstrategiaJugadorNegro() {
		JComboBox comboBoxNegro= menuInicio.getComboJugadorNegro(); 
		return (creadores.get(comboBoxNegro.getSelectedIndex()));
	}
}
