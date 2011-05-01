package Remoto.GTP.ParsearMensajes;

public abstract class CadenaGTP {

	protected CadenaGTP cadenaSgte;
	
	public abstract void enviarSgteCadena(String[] mensaje);
	
	public void agregarCadena(CadenaGTP cadenaSgte) {
		this.cadenaSgte= cadenaSgte;
	}
}
