package Remoto;

public class Cliente extends Remoto {

	private SocketCliente socket;

	public Cliente(EstrategiaRemotoCliente estrategia) {
		super(estrategia);
	}

	public boolean iniciar(String ip, int puerto) {
		this.socket = new SocketCliente(this);
		try {
			this.socket.conectar(ip, puerto);
			this.socket.start();
			return true;
		} catch (Exception e) {
			System.err.println(">> EXCEPTION: Fallo en la conexion <<");
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
		socket.interrupt();		
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
