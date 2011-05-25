package Remoto;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketServidor extends SocketBase {

	private ServerSocket socketServidor;
	private int puerto;

	public SocketServidor(Remoto remoto, int puerto) {
		super(remoto);
		this.puerto= puerto;
	}

	public void esperarCliente() throws IOException {
		socketServidor= new ServerSocket(puerto);
		esperandoConexion();
		empezarBuffers();
	}
	
	public void run() {
		salir= false;
		recibirMensajes();
		cerrarConexion();
		System.out.println(">>Servidor detenido");
	}
	
	private void esperandoConexion() throws IOException {
		System.out.println(">Servidor creado en puerto:" + puerto);
		System.out.println(">Esperando adversario...");
		socket= socketServidor. accept();
		System.out.println(">Jugador conectado: " + socketServidor.getInetAddress().getHostName());
	}
	
	@Override
	public boolean estaConectado() {
		return !socketServidor.isClosed();
	}
}
