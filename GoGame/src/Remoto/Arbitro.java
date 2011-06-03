package Remoto;

import Juego.Posicion;

public interface Arbitro {
	
	public boolean iniciarConexion(String ip, int puerto);

	/**
	 * Este metodo es llamado al llegar un "genmove"
	 * @return piedra jugada localmente, en formato GTP
	 */
	public String getPosicionLocal();
	
	/**
	 * Este metodo es invocado por el resultado del genmove o al llegar un play
	 * @param posicion posicion en la que hay que jugar
	 * @param colorDelRemoto color de la piedra que se esta jugando
	 */
	public boolean setPosicionObtenida(Posicion posicion, String colorDelRemoto);
	
	/**
	 * Este metodo es invocado por el resultado del genmove o al llegar un play
	 * @param posicion posicion en la que hay que jugar
	 */
	public boolean setPosicionObtenida(Posicion posicion);
	
	/**
	 * Envia un "boardsize" mediante GTP
	 */
	public void enviarTamanioTablero();
	
	/**
	 * Este metodo es llamado al llegar un "boardsize"
	 * @param tamanio tamanio del tablero
	 */
	public void setTamanioTablero(int tamanio);
	
	/**
	 * Este metodo es llamado al llegar un "quit" o ante un
	 * cierre de conexion
	 */
	public void setFinDePartida();
	
	/**
	 * Finaliza la partida remota.
	 */
	public void finalizarPartida();
	
	/**
	 * @return la conexion remota
	 */
	public Remoto getRemoto();
	
	/**
	 * @return estrategia remota asociada
	 */
	public EstrategiaRemoto getEstrategia();
	
}
