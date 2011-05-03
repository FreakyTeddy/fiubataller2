package Remoto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SocketCliente extends Thread {
	
	private Cliente	cliente;
	private Socket socket;
	private ObjectOutputStream data_out;
	private ObjectInputStream data_in;
	
	private boolean quit;
	
	public SocketCliente(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	public void run() {
		receiveMessages();
		closeConnection();
	}
	
	public void connect(String ip, int puerto) throws Exception {
		System.out.println("Conectando...");
		connectToServer(ip, puerto);
		System.out.println("Conectado!!");
		startBuffers();
		System.out.println("Bufferssssssss");
	}

	private void connectToServer(String ip, int puerto) throws IOException {
		socket = new Socket(InetAddress.getByName(ip), 1234);
	}

	private void startBuffers() throws IOException {
		// Salida
		data_out = new ObjectOutputStream(socket.getOutputStream());
		data_out.flush(); 
		System.out.println("2");
		
		// Entrada
		data_in = new ObjectInputStream(socket.getInputStream());
		System.out.println("3");
	}

	private void receiveMessages() {
		this.quit = false;
		do { // Procesar mensajes enviados del servidor
    		try {
    			String mensajeRecibido = (String) data_in.readObject();
  		    System.out.println("SERVER>> " + mensajeRecibido);
	    		cliente.procesarMensajeEntrante(mensajeRecibido);
	    		if(!cliente.estaConectado())
	    			this.quit = true;
				} catch (Exception e) {	
					System.out.println(">> EXCEPTION: receiveMessages <<");
					this.quit = true;
					cliente.servidorCerro();
				}
		} while(!this.quit);
	}
	
	private void closeConnection() {
    try {
    	if(data_out != null)
    		data_out.close();
    	if(data_in != null)
    		data_in.close();
    	if(socket != null)
      	socket.close();
    } catch (IOException excepcionES) {
    	System.out.println(">> EXCEPTION: closeConnetion <<");
    	excepcionES.printStackTrace();
    }
    
    System.out.println(">> Connection close");
	}
	
	 public void sendMessage(String messageToSend) {
		 // Enviar mensaje al servidor
		 try {
			 if(cliente.estaConectado()) {
				 System.out.println("SEND>>: " + messageToSend);
				 data_out.writeObject(messageToSend);
				 data_out.flush();
				 }
		 } catch ( IOException excepcionES ) {
			 System.out.println(">> EXCEPTION: sendMessage <<");
				cliente.servidorCerro();
				this.quit = true;
		 }
	 }
	 
	 public void stopSocket() {
		 this.quit = true;
	 }
}