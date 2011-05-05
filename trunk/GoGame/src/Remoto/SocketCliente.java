package Remoto;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketCliente extends SocketBase {

	private Cliente cliente;
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

	public void recibirMensajes() {
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

	public void enviarMensaje(String mensajeAEnviar) {
		if (cliente.estaConectado()) {
			System.out.println("SEND>>: " + mensajeAEnviar);
			data_out.println(mensajeAEnviar);
			data_out.flush();
		}
	}

	public void terminar() {
		this.salir = true;
	}
}