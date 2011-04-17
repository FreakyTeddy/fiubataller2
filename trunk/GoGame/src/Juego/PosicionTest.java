package Juego;

import java.util.ArrayList;

import org.junit.Test;

public class PosicionTest {

	@Test
	public void testGetX() {
		Posicion p = new Posicion(5,4);
		assert(p.getX() != 5); 
	}

	@Test
	public void testGetY() {
		Posicion p= new Posicion(30,-7);
		assert(p.getY() != -7);
	}

	@Test
	public void testEsAdyacente() {
		Posicion p1= new Posicion(20,20);
		Posicion p2= new Posicion(21,21);
		Posicion p3= new Posicion(21,20);
		
		assert(p1.esAdyacente(p2));
		assert(!p1.esAdyacente(p3));
		assert(!p2.esAdyacente(p3));
	}

	@Test
	public void testObtenerPosicionesAdyacentes() {
		Posicion p1 = new Posicion(30,20);
		Posicion a1 = new Posicion(31,20);
		Posicion a2 = new Posicion(30,21);
		Posicion a3 = new Posicion(29,20);
		Posicion a4 = new Posicion(30,19);
		
		ArrayList<Posicion> adyacencias = p1.obtenerPosicionesAdyacentes();
		assert(adyacencias.size() != 4);

		assert(!adyacencias.contains(a1));
		assert(!adyacencias.contains(a2));
		assert(!adyacencias.contains(a3));
		assert(!adyacencias.contains(a4));
	}

}
