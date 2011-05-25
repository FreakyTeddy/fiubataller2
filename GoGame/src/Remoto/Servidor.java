package Remoto;

import java.io.IOException;

import Juego.EstrategiaRemoto;

public class Servidor extends Remoto {

	private SocketServidor socket;
	private boolean clienteConectado;
	
	public Servidor(EstrategiaRemoto estrategiaRemoto) {
		super(estrategiaRemoto);
		clienteConectado= false;
	}
	
	@Override
	public boolean iniciar(String ip, int puerto) {
		socket= new SocketServidor(this, puerto);
		try {
			socket.esperarCliente();
		} catch (IOException e) {
			System.err.println("ERROR: al conectarse a cliente");
			return false;
		}
		socket.start();
		return true;
	}

	public void enviarMensaje(String mensaje) {
		socket.enviarMensaje(mensaje);
	}
	
	public boolean estaConectado() {
		return clienteConectado;
	}
	
	public void clienteConectado() {
		clienteConectado= true;
	}

	public void clienteDesconectado() {
		clienteConectado= false;
	}

	public boolean estaClienteConectado() {
		return clienteConectado;
	}

	public void terminarTest() {
			try {
				this.socket.join();
				System.out.println(">> END: socket thread");
			} catch (InterruptedException e) {
				System.out.print(">> EXCEPTION: terminar <<");
			}
	}
	
	@Override
	public void terminar() {
		if(clienteConectado) {
			this.socket.terminar();
			try {
				this.socket.join();
				System.out.println(">> END: socket thread");
			} catch (InterruptedException e) {
				System.out.print(">> EXCEPTION: terminar <<");
			}
		}
	}
}
