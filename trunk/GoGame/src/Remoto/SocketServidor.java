package Remoto;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketServidor extends SocketBase {

	private Servidor servidor;
	private boolean salir;
	private ServerSocket socketServidor;
	private int puerto;
	private boolean seguirEsperandoClientes;

	public SocketServidor(Servidor servidor, int puerto) {
		this.servidor= servidor;
		this.puerto= puerto;
		seguirEsperandoClientes= true;
	}

	public void run() {
		while(seguirEsperandoClientes)
			escuchar();
	}

	private void escuchar() {
		salir= false;
		try {
			socketServidor= new ServerSocket(puerto);
			esperandoConexiones();
			servidor.clienteConectado();
			empezarBuffers();
			recibirMensajes();
			System.out.println(">>Servidor detenido 3");			
			cerrarConexion();
			System.out.println(">>Servidor detenido 2");
			servidor.clienteDesconectado();
			System.out.println(">>Servidor detenido 1");

		} catch (Exception e) {
			System.err.println(">> EXCEPTION: escuchar <<");
			this.terminar();
		}
		System.out.println(">>Servidor detenido");
	}

	private void esperandoConexiones() throws IOException {
		System.out.println(">Servidor creado en puerto:" + puerto);
		System.out.println(">Esperando adversario...");
		socket= socketServidor. accept();
		System.out.println(">Jugador conectado: " + socketServidor.getInetAddress().getHostName());
	}

	public void recibirMensajes() throws IOException {
		while (!this.salir) {
			try {
				System.out.println(">> Esperando mensajes...");
				String mensajeRecibido= (String) data_in.readLine();
				if(mensajeRecibido != null) {
					System.out.println("FROM_CLIENT>>: " + mensajeRecibido);
					servidor.procesarMensajeEntrante(mensajeRecibido);
				} else {
					servidor.clienteDesconectado();
				}
				if(!servidor.estaClienteConectado())
					this.salir= true;
			} catch (Exception e) {
				System.err.println(">> EXCEPTION: recibirMensajes <<");
				this.salir= true;
			}
		}
	}

	public void enviarMensaje(String mensajeAEnviar) {
			if(servidor.estaClienteConectado()) {
				System.out.println("SEND>> " + mensajeAEnviar);
				data_out.println(mensajeAEnviar);
				data_out.flush();
			}
	}

	public void terminar() {
		seguirEsperandoClientes= false;
		salir= true;
		try {
			socketServidor.close();
		} catch (Exception e) {
			System.err.println(">> EXCEPTION: cerrarSocket <<");
		}
	}
}
