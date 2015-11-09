package com.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.exceptions.NumeroFueraDeRangoException;
import com.modelo.Carta;
import com.modelo.Carta.Palo;

public class CartaTest {
	
	private Carta _carta;
	
	@Before
	public void setup(){
		_carta = new Carta();
	}

	@Test
	public void seCreaCartaVacia(){		
		Assert.assertEquals(Palo.Indefinido, this._carta.getPalo());
		Assert.assertEquals(1, this._carta.getNumero());
	}
	
	@Test
	public void seCreaCartaConValoresValidos(){
		this._carta = new Carta(Palo.Basto, 5);
		Assert.assertEquals(Palo.Basto, this._carta.getPalo());
		Assert.assertEquals(5, this._carta.getNumero());
	}
	
	@Test (expected = NumeroFueraDeRangoException.class)
	public void seCreaCartaConValoresInvalidos(){
		this._carta = new Carta(Palo.Basto, 13);
		this._carta = new Carta(Palo.Espada, 0);
	}
	
}
