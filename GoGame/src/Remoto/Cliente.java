package Remoto;


import java.io.*;
import java.net.*;


public class Cliente {

	private Socket skCliente;
	private DataInputStream entrada;
	private DataOutputStream salida;
	

	public Cliente( String host, int puerto) {
	
		try{
	
		skCliente = new Socket( host , puerto );
		entrada = new DataInputStream( skCliente.getInputStream() );
		salida = new DataOutputStream( skCliente.getOutputStream() );

		System.out.println("Servidor: " + entrada.readUTF());
		System.out.println("Yo: chica esta");
		salida.writeUTF("chica esta");
		System.out.println("Servidor: " + entrada.readUTF());
		
		cerrarConexion();
		
		} catch( IOException e ) {
			System.out.println( e.getMessage() );
		}

	}

	public void cerrarConexion(){
		try{
		skCliente.close();
		}catch(IOException e){
			System.out.println( e.getMessage() );
		}
	}
	
	public static void main( String[] arg ) {

	Cliente c = new Cliente("localhost",5000);

	}


}
