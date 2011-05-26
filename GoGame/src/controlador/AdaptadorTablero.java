package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Juego.Estrategia;
import Juego.FullMoonGo;
import Juego.Posicion;

import vista.TableroVista;

/**
 * Clase adaptadora de eventos de mouse en el tablero de go.
 * @author matias
 *
 */
public class AdaptadorTablero extends MouseAdapter implements Estrategia {

	Posicion ultimaPiedra;
	
	public AdaptadorTablero(){
		ultimaPiedra = null;
	}
	
	public void mouseClicked(MouseEvent e){
		 if(e.getButton()==MouseEvent.BUTTON1)
		 	setUltimaPiedra(TableroVista.transformarPosicionFicha(e.getX(), e.getY()));
		 if(e.getButton()==MouseEvent.BUTTON2) {
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
