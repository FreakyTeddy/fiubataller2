package Remoto;


import java.io.*;
import java.net.*;


public class Cliente {

	private Socket skCliente;
	private DataInputStream entrada;
	private DataOutputStream salida;
	boolean conectado;
	

	public Cliente() {
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

	public void enviar(String mensaje){
		try{
		System.out.println("Enviando: " + mensaje);
		salida.writeUTF(mensaje);
		}catch(IOException e){
			System.out.println("Excepcion en el enviar! " + e);
		}
	}
	
	public String recibir(){
		String mensaje="";
		try{
			mensaje = entrada.readUTF();
			System.out.println("Recibido: " + mensaje);
		}catch(IOException e){
			System.out.println("Excepcion en el recibir! " + e);
		}
		return mensaje;
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
	
	public static void main( String[] arg ) {

		Cliente c = new Cliente();
		c.conectar("localhost",5000);
		if (c.estaConectado()){
			c.recibir();
			c.enviar("chica esta");
			c.recibir();
			c.cerrarConexion();
		}
	}


}
