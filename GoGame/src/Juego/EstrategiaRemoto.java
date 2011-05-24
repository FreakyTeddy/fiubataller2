package Juego;

import java.util.Observable;
import java.util.Observer;

import Remoto.Cliente;
import Remoto.Remoto;
import Remoto.Servidor;

public class EstrategiaRemoto implements Estrategia, Observer {

	Tablero _tablero;
	Remoto _remoto;
	Posicion _ultimaPiedra;
	
	//El parametro escuchar es true si queremos modo servidor, de lo contrario sera modo cliente.	
	public EstrategiaRemoto(Tablero tablero, int puerto, String ip, boolean escuchar){
		_tablero= tablero;
		if(escuchar)
			_remoto= new Servidor();
		else
			_remoto= new Cliente();
		_remoto.addObserver(this);
		_remoto.iniciar(ip, puerto);
		_ultimaPiedra = new Posicion(0,0);
	}
	
	private synchronized void setUltimaPiedra(Posicion p){
		_ultimaPiedra = p;
		this.notifyAll();
	}
	
	private synchronized Posicion getUltimaPiedra(){
		try {
			this.wait();
		} catch (InterruptedException e) {
			System.out.println("Excepcion al obtener piedra del click: " + e);
		}
		return _ultimaPiedra;
	}
	
	@Override
	public Posicion getJugada() { 
		System.out.println("Obtener jugada remoto");
		//Envio el mensaje de generar jugada al remoto
		//TODO: harcodeado, siempre es blanco el remoto?????????
		_remoto.enviarMensajeGenerarMovimiento("White");
		//Espero respuesta 
		return getUltimaPiedra();
	}

	//TODO: para q sirve esto?
	public void informarJugadaInvalida() {
		//_mensajero.informarJugadaInvalida();		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		String ultimaPosicion= _remoto.getPosicionObtenida();
		setUltimaPiedra(new Posicion(ultimaPosicion));
	}
}
