package Remoto;

public class Cliente extends Remoto {

	private SocketCliente socket;

	public Cliente() {
		super();
		conectado = false;
	}

	public boolean iniciar(String ip, int puerto) {
		this.socket = new SocketCliente(this);
		try {
			this.socket.conectar(ip, puerto);
		} catch (Exception e) {
			System.out.println(">Fallo en la conexion");
			return false;
		}
		this.conectado = true;
		this.socket.start();
		return true;
	}

	public boolean estaConectado() {
		return conectado;
	}

	public void enviarMensaje(String mensaje) {
		socket.enviarMensaje(mensaje);
	}

	public void servidorCerro() {
		this.conectado = false;
	}

	public void terminarTest() {
		try {
			this.socket.join();
			System.out.println(">> END: socket thread");
		} catch (InterruptedException e) {
			System.out.print(">> EXCEPTION: stop <<");
		}
	}

	public void terminar() {
		if(conectado) {
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
