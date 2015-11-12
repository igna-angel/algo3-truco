package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.Jugador;
import com.modelo.JugadorHumano;
import com.modelo.Mazo;

public class JugadorHumanoTest {
	
	private Jugador jugador1;
	private Jugador jugador2;
	private Jugador jugador3;
	private Jugador jugador4;
	
	@Before
	public void setup(){
		jugador1 = new JugadorHumano();
		jugador2 = new JugadorHumano();
		jugador3 = new JugadorHumano();
		jugador4 = new JugadorHumano();
		Mazo.getInstanciaMazo();
	}
	
	@Test
	public void repartirCartasExitosoTest(){
		Mazo.getInstanciaMazo().crear();
		jugador1.repartirCartas(jugador2,jugador3,jugador4,jugador1);
		
		Assert.assertTrue(jugador1.getCartaEnMano(0) != null);
		Assert.assertTrue(jugador2.getCartaEnMano(0) != null);
		Assert.assertTrue(jugador3.getCartaEnMano(0) != null);
		Assert.assertTrue(jugador4.getCartaEnMano(0) != null);
		
		Assert.assertTrue(jugador1.getCartaEnMano(1) != null);
		Assert.assertTrue(jugador2.getCartaEnMano(1) != null);
		Assert.assertTrue(jugador3.getCartaEnMano(1) != null);
		Assert.assertTrue(jugador4.getCartaEnMano(1) != null);
		
		Assert.assertTrue(jugador1.getCartaEnMano(2) != null);
		Assert.assertTrue(jugador2.getCartaEnMano(2) != null);
		Assert.assertTrue(jugador3.getCartaEnMano(2) != null);
		Assert.assertTrue(jugador4.getCartaEnMano(2) != null);
	}
	
	@Test
	public void devolverCartasAlMazoExitosoTest(){
		Mazo.getInstanciaMazo().crear();
		jugador1.repartirCartas(jugador2,jugador3,jugador4,jugador1);
		
		jugador1.devolverCartasAlMazo();
		jugador2.devolverCartasAlMazo();
		jugador3.devolverCartasAlMazo();
		jugador4.devolverCartasAlMazo();
		
		Assert.assertTrue(jugador1.getCartaEnMano(0) == null);
		Assert.assertTrue(jugador2.getCartaEnMano(0) == null);
		Assert.assertTrue(jugador3.getCartaEnMano(0) == null);
		Assert.assertTrue(jugador4.getCartaEnMano(0) == null);

	}
}
