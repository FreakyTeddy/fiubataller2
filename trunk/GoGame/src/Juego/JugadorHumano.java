package Juego;

/**
 * Jugador Humano local
 * 
 * @author luuu
 *
 */


public class JugadorHumano extends Jugador {

	public JugadorHumano(Tablero tablero,ColorPiedra color, String nombre) {
		this.nombre = nombre;
		this.color = color;
		this.tablero = tablero;
	}
	
	
	@Override
	public void jugar() {
		
//		boolean valida = false;
//		while (!valida){
//			try {
//				Posicion pos = controlador.getJugada();
//				tablero.agregarPiedra(pos.getX(),pos.getY(), color);
//				valida = true;
//			}catch (JugadaInvalidaException ex){
//				System.out.println(ex);
//			}
//		}
	}

}
