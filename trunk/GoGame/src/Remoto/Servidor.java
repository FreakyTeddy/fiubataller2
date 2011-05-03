package Remoto;

import java.io.* ;
import java.net.* ;

import Remoto.GTP.Constantes;


/**
 * Servidor que maneja la conexion con un cliente.
 * 
 * @author luuu
 *
 */

//TODO ver como tratar las excepciones ante fallo en la conexion

public class Servidor {

	private DataOutputStream salida;
	private DataInputStream entrada;
	private Socket skCliente;
	private ServerSocket skServidor;
	boolean conectado;
	
	public Servidor(int puerto ) {
	
		try {
			skServidor = new ServerSocket( puerto );
			System.out.println("Servidor creado en el puerto " + puerto );
			
			salida = null;
			entrada = null;
			skCliente = null;
			conectado = false;
		} catch (IOException e) {
		    System.out.println("Excepcion! " + e);
		}
	}
	
	/**
	 * El aceptar es bloqueante!
	 */
	public void aceptarCliente(){
		try{
		skCliente = skServidor.accept(); // Crea objeto
		System.out.println("Tenemos un cliente !!");
	
		salida = new DataOutputStream(skCliente.getOutputStream());
		entrada = new DataInputStream(skCliente.getInputStream());
		
		conectado=true;

		} catch (IOException e) {
		    System.out.println("Excepcion en el Accept!! " + e);
		}
	}
	
	public void enviar(String mensaje) {
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
			System.out.println(">> Esperando mensajes...");
			mensaje = entrada.readUTF();
			System.out.println("Recibido: " + mensaje);
		}catch(IOException e){
			System.out.println("Excepcion en el recibir! " + e);
		}
		return mensaje;
	}
	
	/**
	 * Cierra la conexion con el cliente.. El servidor sigue activo
	 */
	public void cerrarConexionCliente(){
		try{
			salida.close();
			entrada.close();
			skCliente.close();
			conectado=false;
		}catch(IOException e){
			System.out.println("Excepcion en el close cliente! " + e);
		}
	}	
		
	/**
	 * Deja de escuchar la llegada de clientes
	 */
	public void cerrarConexionServidor(){
		try{
		skServidor.close();
		}catch(IOException e){
			System.out.println("Excepcion en el close servidor! " + e);
		}
	}
	
	public boolean estaConectado(){
		return conectado;
	}
	
	
	
	public static void main( String[] arg ) {
	
		Servidor s = new Servidor(1234);
		for(int i=0;i<3;i++){
			s.aceptarCliente();
			if( s.estaConectado()){
				s.recibir();
				s.enviar("1 " + Constantes.PROTOCOL_VERSION + " 1" + Constantes.FIN_MSJ);
				s.recibir();
				s.enviar("2 " + Constantes.QUIT + Constantes.FIN_MSJ);
				s.recibir();
				s.cerrarConexionCliente();
			}
		}
		s.cerrarConexionServidor();
	}

}


