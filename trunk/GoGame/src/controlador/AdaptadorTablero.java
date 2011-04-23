package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import vista.TableroGo;

/**
 * Clase adaptadora de eventos de mouse en el tablero de go.
 * TODO: Hay que decidir que se va a hacer con los clicks.
 * Lo que se hace ahora, tiene que volar. Quizas deba notificar
 *  a los jugadores, pero no puede saltearse el modelo.
 * @author matias
 *
 */
public class AdaptadorTablero extends MouseAdapter {
	
	TableroGo tablero;
	
	public AdaptadorTablero(TableroGo tableroGo){
		tablero = tableroGo;
	}
	
public void mouseClicked(MouseEvent e){
		
		tablero.click(e.getX(), e.getY(), e.getButton()==MouseEvent.BUTTON1?0:1);
	}

}
