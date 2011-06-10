package Remoto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class SocketBase extends Thread {

	protected Remoto remoto;
	protected PrintWriter data_out;
	protected BufferedReader data_in;
	protected Socket socket;
	protected boolean salir;

	public SocketBase(Remoto remoto) {
		this.remoto= remoto;
	}
	
	//Proveniente de Thread
	public abstract void run();
	
	protected void empezarBuffers() throws IOException {
		// Salida
		data_out = new PrintWriter(socket.getOutputStream(), true);
		data_out.flush();
		// Entrada
		data_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	protected void recibirMensajes() {
		while(!salir) {
			try {
				String mensajeRecibido = data_in.readLine();
				if(mensajeRecibido != null && !mensajeRecibido.equals("")) {
					System.out.println("RECEIVE>> " + mensajeRecibido);
					remoto.procesarMensajeEntrante(mensajeRecibido);
				} else if(mensajeRecibido == null) {
					salir= true;
					System.out.println(">Servidor se desconecto");
					remoto.getArbitro().setFinDePartida();
				}
			} catch (Exception e) {
				System.err.println(">> EXCEPTION: recibirMensajes <<");
				remoto.getArbitro().setFinDePartida();
				salir= true;
			}
		}		
	}
	
	public void enviarMensaje(String mensajeAEnviar) {
		if(estaConectado()) {
				System.out.println("SEND>> " + mensajeAEnviar);
				data_out.println(mensajeAEnviar);
				data_out.flush();
			}
	}
	
	public abstract boolean estaConectado();
	
	public void dejarDeRecibir() {
		salir= true;
	}
	
	protected void cerrarConexion() {
		try {
			if (data_out != null)
				data_out.close();
			if (data_in != null)
				data_in.close();
			if (socket != null)
				socket.close();
			System.out.println(">Conexion cerrada");
		} catch (IOException excepcionES) {
			System.out.println(">> EXCEPTION: al cerrar Conexion <<");
			excepcionES.printStackTrace();
		}		
	}
}
