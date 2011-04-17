package Remoto;

import Juego.Posicion;
/**
 * Parser de mensajes del Protocolo GTP (Go Text Protocol)
 * 
 * */
public class Mensajero {

	private int idJugada;
	
	public Mensajero() {
		idJugada = 0;
	}
	
	public void enviarJugada(Posicion pos){
		idJugada++;
	}
	
	public Posicion recibirJugada(){
		return null;
	}
	
	public void enviarTamanioTablero(int tamanio){
		
	}
	
	public int recibirTamanioTablero(){
		return 0;
	}
	
	public void informarJugadaInvalida(){
		
	}
	
	
	
}
