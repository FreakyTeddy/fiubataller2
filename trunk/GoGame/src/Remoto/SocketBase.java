package Remoto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class SocketBase extends Thread {

	protected PrintWriter data_out;
	protected BufferedReader data_in;
	protected Socket socket;

	//Proveniente de Thread
	public abstract void run();
	
	protected void empezarBuffers() throws IOException {
		// Salida
		data_out = new PrintWriter(socket.getOutputStream(), true);
		data_out.flush();
		// Entrada
		data_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	protected abstract void recibirMensajes() throws IOException; 
	public abstract void enviarMensaje(String mensajeAEnviar);	
	public abstract void terminar();
	
	protected void cerrarConexion() {
		try {
			if (data_out != null)
				data_out.close();
			if (data_in != null)
				data_in.close();
			if (socket != null)
				socket.close();
		} catch (IOException excepcionES) {
			System.out.println(">> EXCEPTION: cerrarConexion <<");
			excepcionES.printStackTrace();
		}
		System.out.println(">Conexion cerrada");
	}
}