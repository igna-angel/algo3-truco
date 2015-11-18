package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.exceptions.PaloInvalidoException;
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

public class CartaDosTest {
	
	private Carta _carta;
	
	@Before
	public void setup(){
		_carta = new CartaDos(Palo.Copa);
	}

	@Test
	public void testSeCreaCartaVacia(){		
		Assert.assertEquals(Palo.Copa, this._carta.getPalo());
		Assert.assertEquals(2, this._carta.getNumero());
	}
	
	@Test
	public void testSeCreaCartaConValoresValidos(){
		this._carta = new CartaDos(Palo.Oro);
		Assert.assertEquals(Palo.Oro, this._carta.getPalo());
		Assert.assertEquals(2, this._carta.getNumero());
	}
	
	@Test
	public void testGanaACartaNormal(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaNormal = new CartaNormal(Palo.Copa, 12);
		
		Assert.assertTrue(carta.ganaA(cartaNormal));
	}
	
	@Test
	public void testGanaACartaAnchoFalso(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaFalso = new CartaAnchoFalso(Palo.Oro);
		Assert.assertTrue(carta.ganaA(cartaFalso));
	}
	
	@Test
	public void testGanaACartaDos(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaDos = new CartaDos(Palo.Copa);
		Assert.assertFalse(carta.ganaA(cartaDos));
	}
	
	@Test
	public void testGanaACartaTres(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaTres = new CartaTres(Palo.Copa);
		Assert.assertFalse(carta.ganaA(cartaTres));
	}
	
	@Test
	public void testGanaACartaSieteOro(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaSieteOro = new CartaSieteOro();
		Assert.assertFalse(carta.ganaA(cartaSieteOro));
	}
	
	@Test
	public void testGanaACartaSieteEspada(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaSieteEspada = new CartaSieteEspada();
		Assert.assertFalse(carta.ganaA(cartaSieteEspada));
	}
	
	@Test
	public void testGanaACartaAnchoBasto(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaAnchoBasto = new CartaAnchoBasto();
		Assert.assertFalse(carta.ganaA(cartaAnchoBasto));
	}
	
	@Test
	public void testGanaACartaAnchoEspada(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaAnchoEspada = new CartaAnchoEspada();
		Assert.assertFalse(carta.ganaA(cartaAnchoEspada));
	}
}
