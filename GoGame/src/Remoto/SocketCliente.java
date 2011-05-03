package Remoto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SocketCliente extends Thread {
	
	private Cliente	cliente;
	private Socket socket;
	private PrintWriter data_out;
	private BufferedReader data_in;
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
		System.out.println(">Conectando a IP: " + ip + " Puerto: " + puerto + " ...");
		connectToServer(ip, puerto);
		System.out.println(">Conexion establecida");
		startBuffers();
		System.out.println(">Buffers");
	}

	private void connectToServer(String ip, int puerto) throws IOException {
		socket = new Socket(InetAddress.getByName(ip), puerto);
	}

	private void startBuffers() throws IOException {
		// Salida
		data_out = new PrintWriter(socket.getOutputStream(), true);
		data_out.flush(); 
		
		// Entrada
		data_in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	private void receiveMessages() {
		this.quit = false;
		do { // Procesar mensajes enviados del servidor
    		try {
             //   System.out.println(">> Esperando por mensajes...");
                String mensajeRecibido = data_in.readLine();
    			if(!mensajeRecibido.equals("")) {
    				System.out.println("FROM_SERVER>> " + mensajeRecibido);
    				cliente.procesarMensajeEntrante(mensajeRecibido);
    				if(!cliente.estaConectado())
    					this.quit = true;
    			}
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
    
    System.out.println(">Conexion cerrada");
	}
	
	 public void enviarMensaje(String messageToSend) {
		 // Enviar mensaje al servidor
		 if(cliente.estaConectado()) {
				 System.out.println("SEND>>: " + messageToSend);
				 data_out.println(messageToSend);
				 data_out.flush();
		 }
	 }
	 
	 public void stopSocket() {
		 this.quit = true;
	 }
}