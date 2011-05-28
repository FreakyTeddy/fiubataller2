package Juego;

import java.util.Stack;

import Remoto.Remoto;


public abstract class EstrategiaRemoto implements Estrategia {

	protected Remoto remoto;
	protected Stack<Posicion> ultimaPiedraRemoto;
	protected Stack<Posicion> ultimaPiedraLocal;
	protected String colorRemoto;
	protected String colorLocal;

	public EstrategiaRemoto(ColorPiedra colorRemoto, ColorPiedra colorLocal){
		this.colorRemoto = traducirColor(colorRemoto);
		this.colorLocal = traducirColor(colorLocal);
		this.remoto = crearRemoto();
		this.ultimaPiedraRemoto = new Stack<Posicion>();
		this.ultimaPiedraLocal = new Stack<Posicion>();
	}

	private String traducirColor(ColorPiedra color) {
		if(color == ColorPiedra.BLANCO) 
			return new String("white");
		return new String("black");
	}
	
	protected abstract Remoto crearRemoto();
	
	protected abstract void intercambiarJugadas();
	
	public Remoto getRemoto() {
		return remoto;
	}
		
	@Override
	public Posicion getJugada() { 
		intercambiarJugadas();
		return ultimaPiedraRemoto.pop();
	}
	
	protected synchronized void esperar() {
		try{
			this.wait();		//Espero respuesta 
		} catch (InterruptedException e) {
		}
	}
	
	protected synchronized void finDeEspera() {
		this.notifyAll();
	}
	
	
	/**
	 * Este metodo es llamado al llegar un "genmove"
	 * @return piedra jugada localmente, en formato GTP
	 */
	public synchronized String getPosicionLocal() {
		
		if(ultimaPiedraLocal.size() <= 1){
			System.out.println("___Esperando Jugada Local___"); 
			esperar();	//espero que el jugador local juegue. 	warning!! si el tipo juega antes que llegue el genmove	
				
		}
			
		Posicion jugadaLocal = ultimaPiedraLocal.pop();
		String piedra = "pass";//hardcodeee
		if (jugadaLocal != null)
			piedra = jugadaLocal.toString();
		return piedra;
	}
	
	
	/**
	 * Este metodo es invocado por el resultado del play
	 * @param posicion posicion en la que hay que jugar
	 * @param colorDelRemoto color de la piedra que se esta jugando
	 */
	public void setPosicionObtenida(Posicion posicion, String colorDelRemoto){
		if(colorDelRemoto.equalsIgnoreCase(this.colorRemoto)) {
			setPosicionObtenida(posicion);
		}
	}
	
	/**
	 * Este metodo es invocado por el resultado del play
	 * @param posicion posicion en la que hay que jugar
	 */
	public synchronized void setPosicionObtenida(Posicion posicion){
		ultimaPiedraRemoto.add(posicion);
		finDeEspera();
	}
	
	/**
	 * Este metodo es llamado al llegar un "boardsize"
	 * @param tamanio tamanio del tablero
	 */
	public void setTamanioTablero(int tamanio) {
		System.out.println("se recibio un boardsize: " + tamanio);
		if(FullMoonGo.getInstancia().getEstado() == EstadoJuego.NO_INICIADO)
			FullMoonGo.getInstancia().crearTablero(TamanioTablero.NUEVE); //por ahora
	}
	

}
