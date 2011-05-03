package Remoto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServidor {

        private static final int PORT=1234;
        
        private boolean quit;
        private Socket incoming;
        private ServerSocket socket;
        private ObjectOutputStream data_out;
        private ObjectInputStream data_in;
        
        public void startListening() {
                        this.quit = false;
                        try {
                                socket = new ServerSocket(3333);
                              waitConection();
                                //server.clienConnect();
                                startBuffers();
                                receiveMessage();
                                closeConnection();
                                //server.clienDisconnect();
                        } catch (Exception e) { 
                                System.out.println(">> EXCEPTION: startListening <<");
                                this.stopSocket();
                        }

                System.out.println(">>Server Stop");
        }

        private void waitConection() throws IOException {
                System.out.println(">Server started. Waiting for connections...");
                incoming = socket.accept();
                System.out.println(">Conection: " + socket.getInetAddress().getHostName());
        }
        
        private void startBuffers() throws IOException {
          //Salida
                data_out = new ObjectOutputStream(incoming.getOutputStream());
                data_out.flush();
                //Entrada
                data_in = new ObjectInputStream(incoming.getInputStream());
        }
        
        private void receiveMessage() throws IOException {
                //Mensaje de bienvenida
                String wel = "bienvenido";
                sendMessage(wel);
                
                while(!this.quit) {
                        try {
                    System.out.println(">> Waiting message...");
                                String messageReceive = (String) data_in.readObject();
                          System.out.println("CLIENT>>: " + messageReceive);
                          //if(!server.processMessage(messageReceive))
                           //     this.quit = true;
                        } catch (Exception e) {
                                System.out.println(">> EXCEPTION: receiveMessage <<");
                                this.quit = true;
                        }
                }
        }
        
        public void sendMessage(String messageTosend) {
                 try {
                         //if(server.isClientConnect()) {
                                 System.out.println("SEND>> " + messageTosend);
                                 data_out.writeObject(messageTosend);
                                 data_out.flush();
                         //}
                 } catch (IOException excepcionES) {
                         System.out.println(">> EXCEPTION: sendMessage <<");
                 }
        }
        
        private void closeConnection() {
          try {
                data_out.close();
                data_in.close();
                socket.close();
          } catch( IOException excepcionES ) {
                System.out.println(">> EXCEPTION: closeConnection <<");
                excepcionES.printStackTrace();
          }
        }
        
        public void stopSocket() {
                this.quit = true;
                try {
                        socket.close();
                } catch (Exception e) {
                        System.out.println(">> EXCEPTION: socket.close <<");
                }
        }

    	public static void main( String[] arg ) {
    		
    		SocketServidor s = new SocketServidor();
    		s.startListening();
 /*   				s.recibir();
    				s.enviar("1 " + Constantes.PROTOCOL_VERSION + " 1" + Constantes.FIN_MSJ);
    				s.recibir();
    				s.enviar("2 " + Constantes.QUIT + Constantes.FIN_MSJ);
    				s.recibir();
    				s.cerrarConexionCliente();*/
    	}
        
}
