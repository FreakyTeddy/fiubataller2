package Remoto;

import java.util.Stack;

import Juego.ColorPiedra;
import Juego.EstadoJuego;
import Juego.Estrategia;
import Juego.FinDelJuegoException;
import Juego.FullMoonGo;
import Juego.Posicion;
import Remoto.GTP.ConstantesGtp;


public abstract class EstrategiaRemoto implements Estrategia, Arbitro {

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
	
	protected abstract void enviarUltimaJugada();
	
	protected synchronized void esperar() {
		try{
			this.wait();
		} catch (InterruptedException e) {
		}
	}
	
	protected synchronized void finDeEspera() {
		this.notifyAll();
	}
	
	@Override
	public synchronized Posicion getJugada() { 
		if(!remoto.hayRemoto() || finDePartida){
			throw new FinDelJuegoException(ColorPiedra.VACIO, "No hay conexion");
		}
		obtenerJugadaLocal();
		obtenerJugadaRemota();
		if (ultimaPiedraRemoto.isEmpty())
			throw new FinDelJuegoException(ColorPiedra.VACIO, "No hay conexion!!!");
		return ultimaPiedraRemoto.pop();
	}

	@Override
	public void finalizarPartida() {
		System.out.println("******** fin de partida ********");		
		finDeEspera();	//desbloqueo si algun hilo quedo colgado
		if(!finDePartida && remoto.hayRemoto()) {
			enviarUltimaJugada();
		}
		finDePartida = true;
	}
	
	@Override
	public synchronized void setFinDePartida() {
		System.out.println("____set fin de partida ___");
		finDePartida = true;
		finalizarPartida();
		if(remoto.hayRemoto())
			remoto.terminarConexion();
	}

	@Override
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
	
	@Override
	public synchronized boolean setPosicionObtenida(Posicion posicion, String colorDelRemoto){
		if(colorDelRemoto.equalsIgnoreCase(this.colorRemoto)) {
			return setPosicionObtenida(posicion);
		}
		return false;
	}
	
	@Override
	public synchronized boolean setPosicionObtenida(Posicion posicion){
		ultimaPiedraRemoto.add(posicion);
		finDeEspera();
		return true; //TODO
	}
	
	@Override
	public synchronized void setTamanioTablero(int tamanio) {
		if(FullMoonGo.getInstancia().getEstado() != EstadoJuego.INICIADO && tamanio>=9 && tamanio <=19) {
			FullMoonGo.getInstancia().crearTablero(tamanio);
		}
	}

	@Override
	public void enviarTamanioTablero() {
		remoto.enviarMensajeTamanioTablero(FullMoonGo.getInstancia().getTablero().getAncho());
	}
	
	@Override
	public boolean iniciarConexion(String ip, int puerto) {
		return remoto.iniciar(ip, puerto);
	}
	
	@Override
	public Remoto getRemoto() {
		return remoto;
	}
	
	@Override
	public EstrategiaRemoto getEstrategia() {
		return this;
	}
	
}
