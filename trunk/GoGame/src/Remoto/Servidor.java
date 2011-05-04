package Remoto;

import Remoto.GTP.ProcesadorMsjsEntrantes;

public class Servidor extends Remoto {

	private SocketServidor socket;
	private boolean clienteConectado;
	private ProcesadorMsjsEntrantes procesador;
	
	public Servidor() {
		clienteConectado= false;
		socket= new SocketServidor(this);
		procesador= new ProcesadorMsjsEntrantes(this);
	}

	public void iniciar() {
		socket.start();
	}

	public void procesarMensaje(String mensaje) {
		String msjRespuesta= procesador.procesarMensaje(mensaje);
		if(!msjRespuesta.equals(""))
			socket.enviarMensaje(msjRespuesta);
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
		socket.cerrarSocket();
	}
	
	public static void main( String[] arg ) {	
		Servidor s= new Servidor();
		s.iniciar();
	}
}
