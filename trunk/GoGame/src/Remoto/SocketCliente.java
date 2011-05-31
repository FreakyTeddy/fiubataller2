package Remoto;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketCliente extends SocketBase {

	public SocketCliente(Remoto remoto) {
		super(remoto);
	}

	public void run() {
		salir=false;
		recibirMensajes();
		cerrarConexion();
	}

	public void conectar(String ip, int puerto) throws Exception {
		System.out.println(">Conectando a IP: " + ip + " Puerto: " + puerto
				+ " ...");
		conectarAServidor(ip, puerto);
		System.out.println(">Conexion establecida");
		empezarBuffers();
	}

	private void conectarAServidor(String ip, int puerto) throws IOException {
		socket = new Socket(InetAddress.getByName(ip), puerto);
	}

	@Override
	public boolean estaConectado() {
		return socket.isConnected();
	}	
}