package Remoto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SocketCliente extends Thread {

	private Cliente cliente;
	private Socket socket;
	private PrintWriter data_out;
	private BufferedReader data_in;
	private boolean salir;

	public SocketCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void run() {
		recibirMensajes();
		cerrarConexion();
	}

	public void conectar(String ip, int puerto) throws Exception {
		System.out.println(">Conectando a IP: " + ip + " Puerto: " + puerto
				+ " ...");
		conectarAServidor(ip, puerto);
		System.out.println(">Conexion establecida");
		empezarBuffers();
		System.out.println(">Buffers");
	}

	private void conectarAServidor(String ip, int puerto) throws IOException {
		socket = new Socket(InetAddress.getByName(ip), puerto);
	}

	private void empezarBuffers() throws IOException {
		// Salida
		data_out = new PrintWriter(socket.getOutputStream(), true);
		data_out.flush();
		// Entrada
		data_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	private void recibirMensajes() {
		salir = false;
		do { // Procesar mensajes enviados del servidor
			try {
				// System.out.println(">> Esperando por mensajes...");
				String mensajeRecibido = data_in.readLine();
				if (!mensajeRecibido.equals("")) {
					System.out.println("FROM_SERVER>> " + mensajeRecibido);
					cliente.procesarMensajeEntrante(mensajeRecibido);
					if (!cliente.estaConectado())
						salir = true;
				}
			} catch (Exception e) {
				System.out.println(">> EXCEPTION: recibirMensajes <<");
				salir = true;
				cliente.servidorCerro();
			}
		} while (!salir);
	}

	private void cerrarConexion() {
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

	public void enviarMensaje(String mensajeAEnviar) {
		// Enviar mensaje al servidor
		if (cliente.estaConectado()) {
			System.out.println("SEND>>: " + mensajeAEnviar);
			data_out.println(mensajeAEnviar);
			data_out.flush();
		}
	}

	public void detenerSocket() {
		this.salir = true;
	}
}