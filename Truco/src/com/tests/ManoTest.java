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
		Assert.assertEquals(0, this._mano.getCantidadCartas());
		this._mano.recibirCarta(new Carta());
		Assert.assertEquals(1, this._mano.getCantidadCartas());
	}
	
	@Test
	public void testSeRemueveUnaCartaDeLaMano(){
		Carta cartaA = new Carta();
		Carta cartaB = new Carta();
		Carta cartaC = new Carta();
		
		this._mano.recibirCarta(cartaA);
		this._mano.recibirCarta(cartaB);
		this._mano.recibirCarta(cartaC);
		
		Assert.assertTrue(this._mano.contiene(cartaB));
		
		this._mano.quitarCarta(cartaB);
		
		Assert.assertFalse(this._mano.contiene(cartaB));
	}
	
	@Test
	public void testSeDevuelvenLasCartas(){
		Assert.assertEquals(0, this._mano.getCantidadCartas());
		this._mano.recibirCarta(new Carta());
		this._mano.recibirCarta(new Carta());
		this._mano.recibirCarta(new Carta());
		this._mano.recibirCarta(new Carta());
		Assert.assertEquals(4, this._mano.getCantidadCartas());
		this._mano.devolverCartas();
		Assert.assertEquals(0, this._mano.getCantidadCartas());
	}
}
