package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Juego.ColorPiedra;
import Juego.Estrategia;
import Juego.JugadaInvalidaException;
import Juego.FinDelJuegoException;
import Juego.Posicion;
import Juego.Tablero;

import vista.TableroGo;

/**
 * Clase adaptadora de eventos de mouse en el tablero de go.
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
		ultimaPiedra = new Posicion(0,0);
	}
	
	public void mouseClicked(MouseEvent e){

		// if(e.getButton()==MouseEvent.BUTTON1)
		// 	setUltimaPiedra(ventanaTablero.transformarPosicionFicha(e.getX(), e.getY()));
		
		//descomentar esto y comentar lo de arriba para probar las estrategias :)
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
			catch(FinDelJuegoException ex){
				System.out.println("El acabose");
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
