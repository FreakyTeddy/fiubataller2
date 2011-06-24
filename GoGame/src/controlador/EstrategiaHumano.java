package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Juego.Estrategia;
import Juego.Posicion;

import vista.TableroVista;

/**
 * Clase adaptadora de eventos de mouse en el tablero de go.
 * Obtiene la posicion a jugar por medio de clicks en el tablero
 * @author matias
 *
 */
public class EstrategiaHumano extends MouseAdapter implements Estrategia {

	Posicion ultimaPiedra;
	
	public EstrategiaHumano(){
		ultimaPiedra = null;
	}
	
	/**
	 * Click izquierdo ubica una piedra en el tablero
	 * Click medio pasa el turno
	 */
	public void mouseClicked(MouseEvent e){
		 if(e.getButton()==MouseEvent.BUTTON1)
		 	setUltimaPiedra(TableroVista.transformarPosicionFicha(e.getX(), e.getY()));
		 if(e.getButton()==MouseEvent.BUTTON3) {
			 setUltimaPiedra(null);
		 }
	}
	
	private synchronized void setUltimaPiedra(Posicion p){
		ultimaPiedra = p;
		this.notifyAll();
	}
	
	private synchronized Posicion getUltimaPiedra(){
		try {
			this.wait();
		} catch (InterruptedException e) {
			System.out.println("Excepcion al obtener piedra del click: " + e);
		}
		return ultimaPiedra;
	}

	@Override
	public Posicion getJugada() {
		return getUltimaPiedra();
	}

}
