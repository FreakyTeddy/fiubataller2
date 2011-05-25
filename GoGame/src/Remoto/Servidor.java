package Remoto;

import java.io.IOException;

import Juego.EstrategiaRemoto;

public class Servidor extends Remoto {

	private SocketServidor socket;
	
	public Servidor(EstrategiaRemoto estrategiaRemoto) {
		super(estrategiaRemoto);
	}
	
	@Override
	public boolean iniciar(String ip, int puerto) {
		socket= new SocketServidor(this, puerto);
		try {
			socket.esperarCliente();
		} catch (IOException e) {
			System.err.println(">> EXCEPTION: al conectarse a cliente <<");
			return false;
		}
		socket.start();
		return true;
	}

	public void enviarMensaje(String mensaje) {
		socket.enviarMensaje(mensaje);
	}
	
	@Override
	public void terminar() {
		socket.dejarDeRecibir();
	}

	/********* Test *********/
	public void terminarTest() {
		try {
			this.socket.join();
			System.out.println(">> END: socket thread");
		} catch (InterruptedException e) {
			System.out.print(">> EXCEPTION: terminar <<");
		}
	}
}
