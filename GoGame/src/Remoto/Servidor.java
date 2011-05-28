package Remoto;

import java.io.IOException;

public class Servidor extends Remoto {

	private SocketServidor socket;
	
	public Servidor(EstrategiaRemotoServidor estrategia) {
		super(estrategia);
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
	public boolean hayRemoto() {
		return socket.estaConectado();
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
