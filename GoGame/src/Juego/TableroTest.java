package Juego;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TableroTest {

	Tablero tablero;
	
	@Before
	public void setUp() throws Exception {
			tablero = new Tablero();
	}

	@Test
	public void testEstaOcupado() {
		Posicion posicion1 = new Posicion(0,0);
		Posicion posicion2 = new Posicion(1,1);
		Posicion posicion3 = new Posicion(2,2);
		
		assert(tablero.estaOcupado(posicion1));
		assert(tablero.estaOcupado(posicion2));
		assert(tablero.estaOcupado(posicion3));
		
		tablero.setCasillero(new Posicion(0,0), ColorPiedra.BLANCO);
		assert(!tablero.estaOcupado(posicion1));
		
		tablero.setCasillero(new Posicion(0,0), ColorPiedra.NEGRO);
		assert(!tablero.estaOcupado(posicion2));
		
		assert(tablero.estaOcupado(posicion3));
	}
	
	@Test
	public void testOcupadoPosicionInexistente(){
		Posicion posicion = new Posicion(-5,40);
		assert(tablero.estaOcupado(posicion));
		
		tablero.setCasillero(posicion, ColorPiedra.BLANCO);
		assert(tablero.estaOcupado(posicion));
	}

	@Test
	public void testAgregarPiedra() {
		Posicion posicion1 = new Posicion(0,0);
		Posicion posicion2 = new Posicion(1,1);
		Posicion posicion3 = new Posicion(2,2);
		try{
		tablero.agregarPiedra(posicion1, ColorPiedra.BLANCO);
		assert(!tablero.estaOcupado(posicion1));

		tablero.agregarPiedra(posicion2, ColorPiedra.NEGRO);
		assert(!tablero.estaOcupado(posicion1));
		assert(!tablero.estaOcupado(posicion2));
		
		tablero.agregarPiedra(posicion3, ColorPiedra.BLANCO);
		assert(!tablero.estaOcupado(posicion1));
		assert(!tablero.estaOcupado(posicion2));
		assert(!tablero.estaOcupado(posicion3));
		}
		catch(JugadaInvalidaException e){
			fail();
		}
	}
	
	@Test
	public void testSuicidio(){
		Posicion posicion1 = new Posicion(1,0);
		Posicion posicion2 = new Posicion(0,1);
		Posicion posicion3 = new Posicion(2,1);
		Posicion posicion4 = new Posicion(1,2);
		
		Posicion posicion5 = new Posicion(1,1);
	
		try{
			tablero.agregarPiedra(posicion1, ColorPiedra.BLANCO);
			tablero.agregarPiedra(posicion2, ColorPiedra.BLANCO);
			tablero.agregarPiedra(posicion3, ColorPiedra.BLANCO);
			tablero.agregarPiedra(posicion4, ColorPiedra.BLANCO);
		}
		catch(JugadaInvalidaException e){
			fail("Es una jugada válida");
		}
		
		try{
			tablero.agregarPiedra(posicion5, ColorPiedra.NEGRO);
			fail("Es una jugada de suicidio.");
		}
		catch(JugadaInvalidaException e){
		
		}
	}
	
	@Test
	public void testConteoCadenasSeparadas(){
		Posicion posicion1 = new Posicion(0,0);
		Posicion posicion2 = new Posicion(1,1);
	
		assert(tablero.cadenas.size()!=0);
		
		try{
			tablero.agregarPiedra(posicion1, ColorPiedra.BLANCO);
			assert(tablero.cadenas.size()!=1);
			tablero.agregarPiedra(posicion2, ColorPiedra.BLANCO);
			assert(tablero.cadenas.size()!=2);
		}
		catch(JugadaInvalidaException e){
			fail("Es una jugada válida");
		}
	}
	
        @Test
	public void testConteoCadenasUnion(){
		Posicion posicion1 = new Posicion(0,0);
		Posicion posicion2 = new Posicion(0,2);
		Posicion posicion3 = new Posicion(0,1);
	
		assert(tablero.cadenas.size()!=0);
		
		try{
			tablero.agregarPiedra(posicion1, ColorPiedra.BLANCO);
			assert(tablero.cadenas.size()!=1);
			tablero.agregarPiedra(posicion2, ColorPiedra.BLANCO);
			assert(tablero.cadenas.size()!=2);
			tablero.agregarPiedra(posicion3, ColorPiedra.BLANCO);
			assert(tablero.cadenas.size()!=1);
		}
		catch(JugadaInvalidaException e){
			fail("Es una jugada válida");
		}
		
	}
}
