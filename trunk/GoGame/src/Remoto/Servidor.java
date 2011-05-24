package Remoto;

public class Servidor extends Remoto {

	private SocketServidor socket;
	private boolean clienteConectado;
	
	public Servidor() {
		clienteConectado= false;
	}
	
	@Override
	public boolean iniciar(String ip, int puerto) {
		socket= new SocketServidor(this, puerto);
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
	
	public static void main( String[] arg ) {	
		Servidor s= new Servidor();
		s.iniciar("", 1111);
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
