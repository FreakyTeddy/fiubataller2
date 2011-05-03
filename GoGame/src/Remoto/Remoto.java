package Remoto;

import Remoto.GTP.GTP;

public class Remoto {

	//TODO:
	//Por ahora se harcodea!
	private static String HOST= "localhost";
	private static int PUERTO= 5000;
	
	
	private Cliente cliente;
	private GTP gtp;
	private boolean mensajeSalida;
	
	public Remoto() {
		cliente= new Cliente(this);
		gtp= new GTP(this);
		mensajeSalida= false;
	}
	
	public void iniciar() {
		cliente.conectar(HOST, PUERTO);
		if(cliente.estaConectado())
			cliente.start();
	}
	
	public void procesarMensajeEntrante(String mensaje) {
		String msjRespuesta= gtp.procesarMensajeEntrante(mensaje);
		cliente.enviar(msjRespuesta);
		if(mensajeSalida) {
			terminar();
		}
	}
	
	public void finConexion() {
		mensajeSalida= true;
	}
	
	public void terminar() {
		cliente.terminarConexion();
		try {
			this.cliente.join();
			System.out.println(">> END: Cliente thread");
		} catch (InterruptedException e) {
			System.out.print(">> EXCEPTION: Join Cliente thread <<");
		}
		cliente.cerrarConexion();
		System.out.println(">> Conexion terminada XD");
	}
}
