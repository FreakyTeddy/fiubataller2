package Juego;

import Remoto.Remoto;


public abstract class EstrategiaRemoto implements Estrategia {

	protected Remoto remoto;
	protected Posicion ultimaPiedra;
	protected String miColor;
	protected String contrarioColor;

	public EstrategiaRemoto(ColorPiedra micolor, ColorPiedra contrarioColor){
		this.miColor = traducirColor(micolor);
		this.contrarioColor = traducirColor(contrarioColor);
		this.remoto = crearRemoto();
		this.ultimaPiedra = null;
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
		return ultimaPiedra;
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
	
	public synchronized void setPosicionObtenida(Posicion p){
		ultimaPiedra = p;
		notificarRespuesta();
	}
	
	public void setTamanioTablero(int tamanio) {
		System.out.println("se recibio un boardsize: " + tamanio);
		if(FullMoonGo.getInstancia().getEstado() == EstadoJuego.NO_INICIADO)
			FullMoonGo.getInstancia().crearTablero(TamanioTablero.NUEVE); //por ahora
	}
	

}
