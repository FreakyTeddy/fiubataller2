package Remoto;

import java.util.Stack;

import Juego.ColorPiedra;
import Juego.EstadoJuego;
import Juego.Estrategia;
import Juego.FinDelJuegoException;
import Juego.FullMoonGo;
import Juego.Posicion;
import Remoto.GTP.ConstantesGtp;


public abstract class EstrategiaRemoto implements Estrategia {

	protected Remoto remoto;
	protected Stack<Posicion> ultimaPiedraRemoto;
	protected Stack<Posicion> ultimaPiedraLocal;
	protected String colorRemoto;
	protected String colorLocal;
	private boolean finDePartida;
	

	public EstrategiaRemoto(ColorPiedra colorRemoto, ColorPiedra colorLocal){
		this.colorRemoto = traducirColor(colorRemoto);
		this.colorLocal = traducirColor(colorLocal);
		this.ultimaPiedraRemoto = new Stack<Posicion>();
		this.ultimaPiedraLocal = new Stack<Posicion>();
		this.remoto = crearRemoto();
		this.finDePartida = false;
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
	public synchronized Posicion getJugada() { 
		if(!remoto.hayRemoto() || finDePartida){
			throw new FinDelJuegoException(ColorPiedra.VACIO, "No hay conexion");
		}
		obtenerJugadaLocal();
		obtenerJugadaRemota();	
//		if (finDePartida) { 
//			System.out.println("Fin del juegooooooo 22222222222222222");
//			throw new FinDelJuegoException(ColorPiedra.VACIO, "No hay conexion");
//		}			
		return ultimaPiedraRemoto.pop();
	}

	/**
	 * Este metodo es llamado al llegar un "quit"
	 * o ante un cierre en la conexion
	 */
	public void finalizarPartida() {
		System.out.println("******** fin de partida ********");		
		finDeEspera();	//desbloqueo si algun hilo quedo colgado
		if(!finDePartida && remoto.hayRemoto()) {
			System.out.println("*** enviar ultima ficha ****");
			obtenerJugadaLocal(); 
//				System.out.println("*** enviar quit ****");
//				remoto.enviarMensajeSalida();//envia el quit antes que el rdo del genmove porque este tiene el lock
		}
		
		remoto.terminarConexion(); //a veces se cierra la conexion antes de que se envie todo
		finDePartida = true;
	}
	
	public synchronized void setFinDePartida() {
		System.out.println("____set fin de partida ___");
		finDePartida = true;
		finalizarPartida();
	}

	/**
	 * Este metodo es llamado al llegar un "genmove"
	 * @return piedra jugada localmente, en formato GTP
	 */
	public synchronized String getPosicionLocal() {
		
		while(ultimaPiedraLocal.size() <= 1){
			System.out.println("___Esperando Jugada Local___"); 
			esperar();	//espero que el jugador local juegue. 	
		}
		Posicion jugadaLocal = ultimaPiedraLocal.pop();
		System.out.println("___obtuve jugada local___");
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
	public synchronized boolean setPosicionObtenida(Posicion posicion, String colorDelRemoto){
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
	public synchronized  void setTamanioTablero(int tamanio) {
		if(FullMoonGo.getInstancia().getEstado() != EstadoJuego.INICIADO && tamanio>=9 && tamanio <=19) {
			FullMoonGo.getInstancia().crearTablero(tamanio);
		}
	}
	
}
