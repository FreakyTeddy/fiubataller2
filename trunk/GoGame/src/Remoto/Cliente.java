package Remoto;

import Remoto.GTP.ProcesadorMsjsEntrantes;

public class Cliente {

	private SocketCliente socket;
	private boolean conectado;
	private boolean envioMsjSalida;
	private int msjsEnviados;
	private ProcesadorMsjsEntrantes procesador;

	
	public Cliente() {
		procesador = new ProcesadorMsjsEntrantes();
		conectado = false;
		envioMsjSalida = false;
		msjsEnviados = 0;
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
		msjsEnviados++;
	}

	public void seEnvioMensajeSalida() {
		envioMsjSalida = true;
	}

	public void procesarMensajeEntrante(String mensaje) {
		String mensajeRta= procesador.procesarMensaje(mensaje);
		if(!mensajeRta.equals(""))           
			socket.enviarMensaje(mensajeRta);  
		msjsEnviados--;
		if (msjsEnviados == 0 && envioMsjSalida)
			conectado = false;
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
		if (conectado) {
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
