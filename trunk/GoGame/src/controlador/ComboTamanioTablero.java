package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import Juego.FullMoonGo;
import Juego.TamanioTablero;

public class ComboTamanioTablero implements ActionListener {

	private String tamanios[] = {"9x9", "13x13", "19x19"};
	private TamanioTablero[] tam = {TamanioTablero.NUEVE, TamanioTablero.TRECE, TamanioTablero.DIECINUEVE};
	private JComboBox comboBox;
	
	public ComboTamanioTablero() {
		comboBox = new JComboBox(tamanios);
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener(this);
	}
	
	public JComboBox getCombo() {
		return comboBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		FullMoonGo.getInstancia().crearTablero(tam[comboBox.getSelectedIndex()]); TODO ver que se pueda elegir el tamanio
		FullMoonGo.getInstancia().crearTablero();
	}
	
}
