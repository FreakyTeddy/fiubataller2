package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.FullMoonGo;
import Juego.JugadaInvalidaException;
import Juego.FinDelJuegoException;
import Juego.Posicion;
import Juego.Tablero;

import vista.TableroVista;

/**
 * Clase adaptadora de eventos de mouse en el tablero de go.
 * @author matias
 *
 */
public class AdaptadorTablero extends MouseAdapter implements Estrategia {

	Posicion ultimaPiedra;
	
	public AdaptadorTablero(){
		ultimaPiedra = new Posicion(0,0);
	}
	
	public void mouseClicked(MouseEvent e){
		 if(e.getButton()==MouseEvent.BUTTON1)
		 	setUltimaPiedra(TableroVista.transformarPosicionFicha(e.getX(), e.getY()));
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
