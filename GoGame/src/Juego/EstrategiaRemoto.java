package Juego;

import java.util.Stack;

import Remoto.Remoto;
import Remoto.GTP.ConstantesGtp;


public abstract class EstrategiaRemoto implements Estrategia {

	protected Remoto remoto;
	protected Stack<Posicion> ultimaPiedraRemoto;
	protected Stack<Posicion> ultimaPiedraLocal;
	protected String colorRemoto;
	protected String colorLocal;

	public EstrategiaRemoto(ColorPiedra colorRemoto, ColorPiedra colorLocal){
		this.colorRemoto = traducirColor(colorRemoto);
		this.colorLocal = traducirColor(colorLocal);
		this.ultimaPiedraRemoto = new Stack<Posicion>();
		this.ultimaPiedraLocal = new Stack<Posicion>();
		this.remoto = crearRemoto();
	}

	private String traducirColor(ColorPiedra color) {
		if(color == ColorPiedra.BLANCO) 
			return new String("white");
		return new String("black");
	}
	
	protected abstract Remoto crearRemoto();
	
	protected abstract void obtenerJugadaLocal();
	
	protected abstract void obtenerJugadaRemota();
	
	protected synchronized void esperar() {
		try{
			this.wait();
		} catch (InterruptedException e) {
		}
	}
	
	protected synchronized void finDeEspera() {
		this.notifyAll();
	}
	
	public Remoto getRemoto() {
		return remoto;
	}
	
	public boolean iniciarConexion(String ip, int puerto) {
		return remoto.iniciar(ip, puerto);
	}
	
	public void enviarTamanioTablero() {
		remoto.enviarMensajeTamanioTablero(FullMoonGo.getInstancia().getTablero().getAncho());
	}
	
	@Override
	public Posicion getJugada() { 
		obtenerJugadaLocal();
		obtenerJugadaRemota();	
		return ultimaPiedraRemoto.pop();
	}

	/**
	 * Este metodo es llamado al llegar un "genmove"
	 * @return piedra jugada localmente, en formato GTP
	 */
	public synchronized String getPosicionLocal() {
		
		if(ultimaPiedraLocal.size() <= 1){
			System.out.println("___Esperando Jugada Local___"); 
			esperar();	//espero que el jugador local juegue. 	
				
		}
			
		Posicion jugadaLocal = ultimaPiedraLocal.pop();
		String piedra = ConstantesGtp.PASAR_TURNO;
		if (jugadaLocal != null)
			piedra = jugadaLocal.toString();
		return piedra;
	}
	
	
	/**
	 * Este metodo es invocado por el resultado del genmove o al llegar un play
	 * @param posicion posicion en la que hay que jugar
	 * @param colorDelRemoto color de la piedra que se esta jugando
	 */
	public boolean setPosicionObtenida(Posicion posicion, String colorDelRemoto){
		if(colorDelRemoto.equalsIgnoreCase(this.colorRemoto)) {
			return setPosicionObtenida(posicion);
		}
		return false;
	}
	
	/**
	 * Este metodo es invocado por el resultado del genmove o al llegar un play
	 * @param posicion posicion en la que hay que jugar
	 */
	public synchronized boolean setPosicionObtenida(Posicion posicion){
		ultimaPiedraRemoto.add(posicion);
		finDeEspera();
		return true; //TODO
	}
	
	/**
	 * Este metodo es llamado al llegar un "boardsize"
	 * @param tamanio tamanio del tablero
	 */
	public void setTamanioTablero(int tamanio) {
		System.out.println("se recibio un boardsize: " + tamanio);
		if(FullMoonGo.getInstancia().getEstado() == EstadoJuego.NO_INICIADO)
			FullMoonGo.getInstancia().crearTablero(TamanioTablero.NUEVE);
	}
	
	/**
	 * 
	 */
	public void finDePartida() {
		if(remoto.hayRemoto()) {
			obtenerJugadaLocal();
			remoto.enviarMensajeSalida();
			remoto.terminarConexion();	
		}
	}
	

}
