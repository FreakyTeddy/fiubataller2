package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.JugadaInvalidaException;
import Juego.Posicion;
import Juego.Tablero;

import vista.TableroGo;

/**
 * Clase adaptadora de eventos de mouse en el tablero de go.
 * TODO: Hay que decidir que se va a hacer con los clicks.
 * Lo que se hace ahora, tiene que volar. Quizas deba notificar
 *  a los jugadores, pero no puede saltearse el modelo.
 * @author matias
 *
 */
public class AdaptadorTablero extends MouseAdapter implements Estrategia {
	
	TableroGo ventanaTablero;
	Tablero tablero;
	Posicion ultimaPiedra;
	
	public AdaptadorTablero(TableroGo tableroGo){
		ventanaTablero = tableroGo;
		tablero = FullMoonGo.getInstancia().getTablero();
	}
	
	public void mouseClicked(MouseEvent e){
			//TODO sacar el agregar piedra!!! ver una forma linda de hacer esto
		try{
			if(e.getButton()==MouseEvent.BUTTON1){
				ultimaPiedra =  ventanaTablero.transformarPosicionFicha(e.getX(), e.getY());
				tablero.agregarPiedra(ultimaPiedra, ColorPiedra.BLANCO);
			}
			else
				tablero.agregarPiedra(ventanaTablero.estrategiaNegro().getJugada(), ColorPiedra.NEGRO);
			}
			catch(JugadaInvalidaException ex){
				System.out.println("Eeeeeeeeeeeepa");
				System.out.println(ex.toString());
			}
	}

	@Override
	public Posicion getJugada() {
		return ultimaPiedra;
	}

}
