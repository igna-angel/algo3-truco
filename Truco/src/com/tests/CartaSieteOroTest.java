package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.cartas.Carta;
import com.modelo.cartas.CartaAnchoBasto;
import com.modelo.cartas.CartaAnchoEspada;
import com.modelo.cartas.CartaAnchoFalso;
import com.modelo.cartas.CartaDos;
import com.modelo.cartas.CartaNormal;
import com.modelo.cartas.CartaSieteEspada;
import com.modelo.cartas.CartaSieteOro;
import com.modelo.cartas.CartaTres;
import com.modelo.cartas.Carta.Palo;

public class CartaSieteOroTest {

	private Carta _carta;
	
	@Before
	public void setup(){
		_carta = new CartaSieteOro();
	}

	@Test
	public void testSeCreaCartaConValoresValidos(){
		this._carta = new CartaSieteOro();
		Assert.assertEquals(Palo.Oro, this._carta.getPalo());
		Assert.assertEquals(7, this._carta.getNumero());
	}
	
	@Test
	public void testGanaACartaNormal(){
		Carta carta = new CartaSieteOro();
		Carta cartaNormal = new CartaNormal(Palo.Copa, 12);
		
		Assert.assertTrue(carta.ganaA(cartaNormal));
	}
	
	@Test
	public void testGanaACartaAnchoFalso(){
		Carta carta = new CartaSieteOro();
		Carta cartaFalso = new CartaAnchoFalso(Palo.Oro);
		Assert.assertTrue(carta.ganaA(cartaFalso));
	}
	
	@Test
	public void testGanaACartaDos(){
		Carta carta = new CartaSieteOro();
		Carta cartaDos = new CartaDos(Palo.Copa);
		Assert.assertTrue(carta.ganaA(cartaDos));
	}
	
	@Test
	public void testGanaACartaTres(){
		Carta carta = new CartaSieteOro();
		Carta cartaTres = new CartaTres(Palo.Copa);
		Assert.assertTrue(carta.ganaA(cartaTres));
	}
	
	@Test
	public void testGanaACartaSieteOro(){
		Carta carta = new CartaSieteOro();
		Carta cartaSieteOro = new CartaSieteOro();
		Assert.assertFalse(carta.ganaA(cartaSieteOro));
	}
	
	@Test
	public void testGanaACartaSieteEspada(){
		Carta carta = new CartaSieteOro();
		Carta cartaSieteEspada = new CartaSieteEspada();
		Assert.assertFalse(carta.ganaA(cartaSieteEspada));
	}
	
	@Test
	public void testGanaACartaAnchoBasto(){
		Carta carta = new CartaSieteOro();
		Carta cartaAnchoBasto = new CartaAnchoBasto();
		Assert.assertFalse(carta.ganaA(cartaAnchoBasto));
	}
	
	@Test
	public void testGanaACartaAnchoEspada(){
		Carta carta = new CartaSieteOro();
		Carta cartaAnchoEspada = new CartaAnchoEspada();
		Assert.assertFalse(carta.ganaA(cartaAnchoEspada));
	}
}
