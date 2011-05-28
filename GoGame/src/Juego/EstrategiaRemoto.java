package Juego;

import Remoto.Remoto;


public abstract class EstrategiaRemoto implements Estrategia {

	protected Remoto remoto;
	protected Posicion ultimaPiedraRemoto;
	protected Posicion ultimaPiedraLocal;
	protected String colorRemoto;
	protected String colorLocal;

	public EstrategiaRemoto(ColorPiedra colorRemoto, ColorPiedra colorLocal){
		this.colorRemoto = traducirColor(colorRemoto);
		this.colorLocal = traducirColor(colorLocal);
		this.remoto = crearRemoto();
		this.ultimaPiedraRemoto = null;
		this.ultimaPiedraLocal = null;
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
		return ultimaPiedraRemoto;
	}
	
	protected synchronized void esperarRespuesta() {
		try{
			this.wait();			//Espero respuesta 
		} catch (InterruptedException e) {
		}
	}
	
	protected synchronized void notificarRespuesta() {
		this.notifyAll();
	}
	
	public synchronized String getPosicionLocal() {
		esperarRespuesta();	//espero que el jugador local juegue. Be careful with this!
		String piedra = "PASS";
		if (ultimaPiedraLocal != null)
			piedra = ultimaPiedraLocal.toString();
		return piedra;
	}
	
	public void setPosicionObtenida(Posicion posicion, String colorDelRemoto){
		if(colorDelRemoto.equalsIgnoreCase(this.colorRemoto)) {
			setPosicionObtenida(posicion);
		}
	}
	
	public synchronized void setPosicionObtenida(Posicion posicion){
		ultimaPiedraRemoto = posicion;
		notificarRespuesta();
	}
	
	public void setTamanioTablero(int tamanio) {
		System.out.println("se recibio un boardsize: " + tamanio);
		if(FullMoonGo.getInstancia().getEstado() == EstadoJuego.NO_INICIADO)
			FullMoonGo.getInstancia().crearTablero(TamanioTablero.NUEVE); //por ahora
	}
	

}
