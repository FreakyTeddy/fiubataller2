package Remoto;


/**
 * Parser de mensajes del Protocolo GTP (Go Text Protocol)
 * 
 * */
public class Mensajero {

	private int idJugada;
	
	public Mensajero() {
		idJugada = 0;
	}
	
	public void enviarJugada(){
		idJugada++;
	}
	
	public void recibirJugada(){
		
	}
	
	public void enviarTamanioTablero(int tamanio){
		
	}
	
	public int recibirTamanioTablero(){
		return 0;
	}
	
	public void informarJugadaInvalida(){
		
	}
	
	
	
}
