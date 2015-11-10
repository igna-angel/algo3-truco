package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.Carta;
import com.modelo.Mano;

public class ManoTest {

	private Mano _mano = null;
	
	@Before
	public void setup(){
		this._mano = new Mano();
	}
	
	@Test
	public void testSeCreaManoVacia(){
		Assert.assertEquals(0, this._mano.getCantidadCartas());
	}
	
	@Test
	public void testSeAgregaUnaCartaALaMano(){
		this._mano.recibirCarta(new Carta());
		Assert.assertEquals(1, this._mano.getCantidadCartas());
	}
	
}
