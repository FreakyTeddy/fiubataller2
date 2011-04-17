package Juego;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class CadenaTest {
	
	Tablero tablero;
	
	@Before
	public void setUp() throws Exception {
		tablero = new Tablero();
	}

	@Test
	public void testEsAdyacenteSimple() {
		Posicion posicion1 = new Posicion(10,10);
		Posicion posicion2 = new Posicion(10,11);
		
		Cadena cadena1 = new Cadena(posicion1, ColorPiedra.BLANCO, tablero);
		
		assert(!cadena1.esAdyacente(posicion2));
	}

	@Test
	public void testEsLibreSimple() {
		Posicion posicion = new Posicion(10,10);
		tablero.setCasillero(posicion, ColorPiedra.BLANCO);
		Cadena cadena = new Cadena(posicion, ColorPiedra.BLANCO, tablero);
		
		assert(!cadena.esLibre());
	}
	
	@Test
	public void testEsLibreSimple2(){
		Posicion posicion1 = new Posicion(5,5);
		Posicion posicion2 = new Posicion(5,6);
		Posicion posicion3 = new Posicion(6,6);
		
		tablero.setCasillero(posicion1, ColorPiedra.BLANCO);
		tablero.setCasillero(posicion2, ColorPiedra.BLANCO);
		tablero.setCasillero(posicion3, ColorPiedra.BLANCO);
		
		Cadena cadena = new Cadena(posicion1, ColorPiedra.BLANCO, tablero);
		cadena.agregarPosicion(posicion2);
		cadena.agregarPosicion(posicion3);
		
		assert(!cadena.esLibre());
	}
	
	@Test
	public void testNoLibre(){
		Posicion posicion1 = new Posicion(3,3);
		ArrayList<Posicion> adyacentes = posicion1.obtenerPosicionesAdyacentes();
		
		for(Posicion adyacente : adyacentes)
			tablero.setCasillero(adyacente, ColorPiedra.NEGRO);
		
		tablero.setCasillero(posicion1, ColorPiedra.BLANCO);
		
		Cadena cadena = new Cadena(posicion1, ColorPiedra.BLANCO, tablero);
		
		assert(cadena.esLibre());
	}
}
