package Remoto;

import Remoto.GTP.GTP;

public class Cliente {

	private SocketCliente socket;
	private GTP gtp;
	private boolean conectado;
	
	public Cliente() {
		gtp= new GTP(this);
		conectado= false;
	}
	
	public boolean iniciar(String ip, int puerto) {
		this.socket = new SocketCliente(this);
        try {
                this.socket.connect(ip, puerto);
        } catch (Exception e) {
        		System.out.println("acaaaaaaaaaaa");
                return false;
        }
        this.conectado= true;
        this.socket.start();
        
        return true;
	}
	
	public boolean estaConectado() {
		return conectado;
	}
	
	public void procesarMensajeEntrante(String mensaje) {
		String msjRespuesta= gtp.procesarMensajeEntrante(mensaje);
		
		
		//cliente.enviar(msjRespuesta);
		//if(mensajeSalida) {
		//	terminar();
		//}
	}
	
    public void servidorCerro() {
        this.conectado= false;
    }
    
	public static void main( String[] arg ) {
		
		Cliente c= new Cliente();
		c.iniciar("localhost", 3333);
	}
}
