package Remoto;

import Remoto.GTP.ProcesadorMsjsEntrantes;

public class Servidor {

	private SocketServidor socket;
	private boolean clienteConectado;
	private ProcesadorMsjsEntrantes procesador;
	
	public Servidor(int puerto) {
		clienteConectado= false;
		socket= new SocketServidor(this, puerto);
		procesador= new ProcesadorMsjsEntrantes();
	}

	public void iniciar() {
		socket.start();
	}

	public void procesarMensaje(String mensaje) {
		String msjRespuesta= procesador.procesarMensaje(mensaje);
		if(!msjRespuesta.equals(""))
			socket.enviarMensaje(msjRespuesta);
	}
	
	public void enviarMensaje(String mensaje) {
		socket.enviarMensaje(mensaje);
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

	public void pararDeEscuchar() {
		/*
		 * if(clientConnect)
		 * socket.sendMessage(util.createMessage(Util.END_CONECTION));
		 */
		socket.terminar();
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
		Servidor s= new Servidor(1111);
		s.iniciar();
	}
}
