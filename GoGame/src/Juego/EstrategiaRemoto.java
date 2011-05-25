package Juego;

import Remoto.Cliente;
import Remoto.Remoto;
import Remoto.Servidor;

public class EstrategiaRemoto implements Estrategia {

	Tablero _tablero;
	Remoto _remoto;
	Posicion _ultimaPiedra;
	
	//El parametro escuchar es true si queremos modo servidor, de lo contrario sera modo cliente.	
	public EstrategiaRemoto(Tablero tablero, boolean escuchar){
		_tablero= tablero;
		if(escuchar)
			_remoto= new Servidor(this);
		else
			_remoto= new Cliente(this);
		_ultimaPiedra = new Posicion(0,0);
	}
	
	public boolean iniciarRemoto(int puerto, String ip) {
		return _remoto.iniciar(ip, puerto);
	}
	
	public synchronized void setUltimaPiedra(Posicion p){
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
		System.out.println(">Obteniendo jugada de remoto...");
		//Envio el mensaje de generar jugada al remoto
		//TODO: 1) harcodeado, siempre es blanco el remoto?????????
		//      2) antes de enviar pregunta si esta conectado
		if(_remoto.hayRemoto())
			_remoto.enviarMensajeGenerarMovimiento("White");
		//Espero respuesta 
		return getUltimaPiedra();
	}

	//TODO: para q sirve esto?
	public void informarJugadaInvalida() {
		//_mensajero.informarJugadaInvalida();		
	}
}
