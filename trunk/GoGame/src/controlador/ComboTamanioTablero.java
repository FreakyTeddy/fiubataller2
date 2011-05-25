package controlador;

import javax.swing.JComboBox;

import Juego.TamanioTablero;

public class ComboTamanioTablero {

	private String tamanios[] = {"9x9", "13x13", "19x19"};
	private TamanioTablero[] tam = {TamanioTablero.NUEVE, TamanioTablero.TRECE, TamanioTablero.DIECINUEVE};
	private JComboBox comboBox;
	
	public ComboTamanioTablero() {
		comboBox = new JComboBox(tamanios);
		comboBox.setSelectedIndex(0);
	}
	
	public JComboBox getCombo() {
		return comboBox;
	}
}