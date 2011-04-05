package Remoto;

import java.io.* ;
import java.net.* ;

public class Servidor {

	private DataOutputStream salida;
	private DataInputStream entrada;
	private Socket skCliente;
	private ServerSocket skServidor;
	
	public Servidor(int puerto ) {
	
		try {
			skServidor = new ServerSocket( puerto );
			System.out.println("Escucho el puerto " + puerto );

			skCliente = skServidor.accept(); // Crea objeto
			System.out.println("Tenemos un cliente!! :)");
				
			salida = new DataOutputStream(skCliente.getOutputStream());
			entrada = new DataInputStream(skCliente.getInputStream()); 
			
			System.out.println("enviando");
			salida.writeUTF("Como te llamas?");
			String nombre = entrada.readUTF();
			salida.writeUTF("hola " + nombre + "!!!");
			System.out.println("fin conexion");		
			cerrarConexion();
			
		} catch (IOException e) {
		    System.out.println("Excepcion! " + e);
		}
		
	}
	
	public void cerrarConexion(){
		try{
			salida.close();
			entrada.close();
			skCliente.close();
			skServidor.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
	public static void main( String[] arg ) {
	
		Servidor s = new Servidor(5000);
	
	}

}


