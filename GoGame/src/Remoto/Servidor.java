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
			socket.start();
			return true;
		} catch (IOException e) {
			System.err.println(">> EXCEPTION: al conectarse a cliente << " + e.getMessage());
		}
		return false;
	}

	@Override
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
	
	@Override
	public void terminarHilo() {
		socket.terminarAccept();
	}

	/********* Test *********/
	public void terminarTest() {
		try {
			this.socket.join();
		} catch (InterruptedException e) {
			System.out.print(">> EXCEPTION: al terminar thread <<");
		}
	}
}
