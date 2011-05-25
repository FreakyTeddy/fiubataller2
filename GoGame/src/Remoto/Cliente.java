package Remoto;

import Juego.EstrategiaRemoto;

public class Cliente extends Remoto {

	private SocketCliente socket;

	public Cliente(EstrategiaRemoto estrategiaRemoto) {
		super(estrategiaRemoto);
	}

	public boolean iniciar(String ip, int puerto) {
		this.socket = new SocketCliente(this);
		try {
			this.socket.conectar(ip, puerto);
		} catch (Exception e) {
			System.err.println(">> EXCEPTION: Fallo en la conexion <<");
			return false;
		}
		this.socket.start();
		return true;
	}

	public void enviarMensaje(String mensaje) {
		socket.enviarMensaje(mensaje);
	}

	public void terminar() {
		socket.dejarDeRecibir();
	}
	
	/********* Test *********/
	public void terminarTest() {
		try {
			this.socket.join();
			System.out.println(">> END: socket thread");
		} catch (InterruptedException e) {
			System.out.print(">> EXCEPTION: stop <<");
		}
	}
}
