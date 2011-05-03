package Remoto;


import java.io.*;
import java.net.*;


public class Cliente extends Thread {

	private Remoto remoto;
	private Socket skCliente;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private boolean conectado;
	private boolean cerrar;
	

	public Cliente(Remoto remoto) {
		super();
		this.remoto= remoto;
		skCliente = null;
		salida = null;
		entrada = null;
		conectado = false;
	}
	
	public void conectar( String host, int puerto){
		try{
			skCliente = new Socket( host , puerto );
			entrada = new DataInputStream( skCliente.getInputStream() );
			salida = new DataOutputStream( skCliente.getOutputStream() );
			System.out.println("Conectados! puerto local: " + skCliente.getLocalPort());
			conectado = true;
		} catch( IOException e ) {
			System.out.println( "Excepcion al conectar: " + e.getMessage() );
		}
	}

	@Override
	public void run() {
			recibirMensajes();
			cerrarConexion();
	}
	
	public void enviar(String mensaje){
		try{
		System.out.println("Enviando: " + mensaje);
		salida.writeUTF(mensaje);
		}catch(IOException e){
			System.out.println("Excepcion en el enviar! " + e);
		}
	}
	
	public void recibirMensajes(){
		cerrar= false;
				do { // Procesar mensajes enviados del servidor
					String mensaje="";
					System.out.println(">> Esperando mensajes...");					
					try{
						mensaje = entrada.readUTF();
						System.out.println("Recibido: " + mensaje);
						remoto.procesarMensajeEntrante(mensaje);
					}catch(IOException e){
						System.out.println("Excepcion en el recibir! " + e);
					}
				} while(!cerrar);
	}
	
		
	public void cerrarConexion(){
		try{
		skCliente.close();
		conectado = false;
		}catch(IOException e){
			System.out.println( e.getMessage() );
		}
	}
	
	public boolean estaConectado(){
		return conectado;
	}
	
	 public void terminarConexion() {
		 cerrar= true;
	 }
}
