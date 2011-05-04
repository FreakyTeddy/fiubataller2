package Remoto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServidor extends Thread {

	private static final int PORT= 1234;

	
	private Servidor servidor;
	private boolean salir;
	private Socket cliente;
	private ServerSocket socket;
	private PrintWriter data_out;
	private BufferedReader data_in;

	public SocketServidor(Servidor servidor) {
		this.servidor= servidor;	
	}

	public void run() {
		empezarAEscuchar();
	}

	private void empezarAEscuchar() {
		salir= false;
		try {
			socket= new ServerSocket(PORT);
			esperandoConexiones();
			servidor.clienteConectado();
			empezarBuffers();
			recibirMensajes();
			cerrarConexion();
			servidor.clienteDesconectado();
		} catch (Exception e) {
			System.out.println(">> EXCEPTION: empezarAEscuchar <<");
			this.cerrarSocket();
		}

		System.out.println(">>Servidor detenido");
	}

	private void esperandoConexiones() throws IOException {
		System.out.println(">Servidor creado en puerto:" + PORT);
		System.out.println(">Esperando conexiones...");
		cliente= socket.accept();
		System.out.println(">Conexion: " + socket.getInetAddress().getHostName());
	}

	private void empezarBuffers() throws IOException {
		// Salida
		data_out = new PrintWriter(cliente.getOutputStream(), true);
		data_out.flush();
		// Entrada
		data_in= new BufferedReader(new InputStreamReader(cliente.getInputStream()));
	}

	private void recibirMensajes() throws IOException {
		while (!this.salir) {
			try {
				System.out.println(">> Esperando mensajes...");
				String mensajeRecibido= (String) data_in.readLine();
				if(mensajeRecibido != null) {
					System.out.println("FROM_CLIENT>>: " + mensajeRecibido);
					servidor.procesarMensaje(mensajeRecibido);
				} else {
					servidor.clienteDesconectado();
				}
				if(!servidor.estaClienteConectado())
					this.salir= true;
			} catch (Exception e) {
				System.out.println(">> EXCEPTION: recibirMensajes <<");
				this.salir= true;
			}
		}
	}

	public void enviarMensaje(String mensajeAEnviar) {
			if (servidor.estaClienteConectado()) {
				if(!mensajeAEnviar.equals("")) {
					System.out.println("SEND>> " + mensajeAEnviar);
					data_out.println(mensajeAEnviar);
					data_out.flush();
				}
			}
	}

	private void cerrarConexion() {
		try {
			data_out.close();
			data_in.close();
			socket.close();
		} catch (IOException excepcionES) {
			System.out.println(">> EXCEPTION: cerrarConexion <<");
			excepcionES.printStackTrace();
		}
	}

	public void cerrarSocket() {
		this.salir= true;
		try {
			socket.close();
		} catch (Exception e) {
			System.out.println(">> EXCEPTION: cerrarSocket <<");
		}
	}
}
