package Juego;

public enum TamanioTablero {
	NUEVE(9), TRECE (13), DIECINUEVE (19);
	
	private int tamanio;
	
	TamanioTablero(int tam) {
		this.tamanio = tam;
	}
	
	public int getTamanio(){
		return tamanio;
	}
}
