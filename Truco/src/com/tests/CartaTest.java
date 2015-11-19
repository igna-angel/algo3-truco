package com.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.exceptions.NumeroFueraDeRangoException;
import com.modelo.cartas.Carta;
import com.modelo.cartas.Carta.Palo;
import com.modelo.cartas.CartaNormal;

public class CartaTest {
	
	private Carta _carta;
	
	@Before
	public void setup(){
		this._carta = new CartaNormal();
	}

	@Test
	public void testSeCreaCartaVacia(){		
		Assert.assertEquals(Palo.Indefinido, this._carta.getPalo());
		Assert.assertEquals(1, this._carta.getNumero());
	}
	
	@Test
	public void testSeCreaCartaConValoresValidos(){
		this._carta = new CartaNormal(Palo.Basto, 5);
		Assert.assertEquals(Palo.Basto, this._carta.getPalo());
		Assert.assertEquals(5, this._carta.getNumero());
	}
	
	@Test (expected = NumeroFueraDeRangoException.class)
	public void testSeCreaCartaConValoresInvalidos(){
		this._carta = new CartaNormal(Palo.Basto, 13);
		this._carta = new CartaNormal(Palo.Espada, 0);
	}
	
}
