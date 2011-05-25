package controlador;

import java.util.ArrayList;

import javax.swing.JComboBox;

import vista.VentanaAplicacionGo;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.FullMoonGo;

public class ComboJugador {

	private ColorPiedra colorJugador;
	private String estrategias[] = {"Humano", "Minimax", "Muy Facil", "Facil", "Medio"};
	private ArrayList<CreadorEstrategia> creadores;
	private JComboBox comboJugadores;
	
	public ComboJugador(ColorPiedra color, VentanaAplicacionGo vista) {
		colorJugador = color;
		comboJugadores = new JComboBox(estrategias);
		comboJugadores.setSelectedIndex(0);
		creadores = new ArrayList<CreadorEstrategia>();
		creadores.add(new CreadorEstrategiaHumano(vista));
		creadores.add(new CreadorEstrategiaMinMax());
		creadores.add(new CreadorEstrategiaMuyFacil());
		creadores.add(new CreadorEstrategiaFacil());
		creadores.add(new CreadorEstrategiaMedio());
	}
	
	public JComboBox getCombo() {
		return comboJugadores;
	}
	
	public Estrategia getEstrategiaElegida() {
		return creadores.get(comboJugadores.getSelectedIndex())
						.crearEstrategia(FullMoonGo.getInstancia().getTablero(), colorJugador);
	}
	
}
