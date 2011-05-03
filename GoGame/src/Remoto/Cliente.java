package Remoto;

import Remoto.GTP.GTP;

public class Cliente {

	private SocketCliente socket;
	private GTP gtp;
	private boolean conectado;
	private boolean mensajeSalida;
	
	public Cliente() {
		gtp= new GTP(this);
		conectado= false;
		mensajeSalida= false;
	}
	
	public boolean iniciar(String ip, int puerto) {
		this.socket = new SocketCliente(this);
        try {
                this.socket.connect(ip, puerto);
        } catch (Exception e) {
        		System.out.println(">Fallo en la conexion");
        		return false;
        }
        this.conectado= true;
        this.socket.start();
        return true;
	}
	
	public boolean estaConectado() {
		return conectado;
	}
	
	public void enviarMensaje(String mensaje) {
		socket.enviarMensaje(mensaje);
	}
	
	public void procesarMensajeEntrante(String mensaje) {
		String msjRespuesta= gtp.procesarMensajeEntrante(mensaje);
		if(!msjRespuesta.equals(""))
			socket.enviarMensaje(msjRespuesta);
		if(mensajeSalida) {
			conectado= false;
		}
	}
	
	public void mensajeSalida() {
		mensajeSalida= true;
	}
	
    public void servidorCerro() {
        this.conectado= false;
    }
    
    public void terminar2() {
        try {
        	this.socket.join();
            System.out.println(">> END: socket thread");
        } catch (InterruptedException e) {
            System.out.print(">> EXCEPTION: stop <<");
        }
    }
    
    public void terminar() {
    	if(conectado) {
    		this.socket.stopSocket();
            try {
            	this.socket.join();
                System.out.println(">> END: socket thread");
            } catch (InterruptedException e) {
                System.out.print(">> EXCEPTION: stop <<");
            }
        }
    }
}
