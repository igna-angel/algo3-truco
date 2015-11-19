package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.cartas.Carta;
import com.modelo.cartas.CartaAnchoBasto;
import com.modelo.cartas.CartaAnchoEspada;
import com.modelo.cartas.CartaAnchoFalso;
import com.modelo.cartas.CartaDos;
import com.modelo.cartas.CartaInvalida;
import com.modelo.cartas.CartaNormal;
import com.modelo.cartas.CartaSieteEspada;
import com.modelo.cartas.CartaSieteOro;
import com.modelo.cartas.CartaTres;
import com.modelo.cartas.Carta.Palo;

public class CartaInvalidaTest {
	private Carta _carta;
	
	@Before
	public void setup(){
		this._carta = new CartaInvalida();
	}

	@Test
	public void testGanaACartaInvalida(){
		Carta cartaInvalida = new CartaInvalida();
		
		Assert.assertEquals(this._carta, this._carta.ganador(cartaInvalida));
	}
	
	@Test
	public void testGanaACartaNormal(){
		Carta cartaNormal = new CartaNormal(Palo.Copa, 7);
		Assert.assertEquals(cartaNormal, this._carta.ganador(cartaNormal));
	}
	
	@Test
	public void testGanaACartaAnchoFalso(){
		Carta cartaFalso = new CartaAnchoFalso(Palo.Copa);
		Assert.assertEquals(cartaFalso, this._carta.ganador(cartaFalso));
	}
	
	@Test
	public void testGanaACartaDos(){
		Carta cartaDos = new CartaDos(Palo.Copa);
		Assert.assertEquals(cartaDos, this._carta.ganador(cartaDos));
	}
	
	@Test
	public void testGanaACartaTres(){
		Carta cartaTres = new CartaTres(Palo.Copa);
		Assert.assertEquals(cartaTres, this._carta.ganador(cartaTres));
	}
	
	@Test
	public void testGanaACartaSieteOro(){
		Carta cartaSieteOro = new CartaSieteOro();
		Assert.assertEquals(cartaSieteOro, this._carta.ganador(cartaSieteOro));
	}
	
	@Test
	public void testGanaACartaSieteEspada(){
		Carta cartaSieteEspada = new CartaSieteEspada();
		Assert.assertEquals(cartaSieteEspada, this._carta.ganador(cartaSieteEspada));
	}
	
	@Test
	public void testGanaACartaAnchoBasto(){
		Carta cartaAnchoBasto = new CartaAnchoBasto();
		Assert.assertEquals(cartaAnchoBasto, this._carta.ganador(cartaAnchoBasto));
	}
	
	@Test
	public void testGanaACartaAnchoEspada(){
		Carta cartaAnchoEspada = new CartaAnchoEspada();
		Assert.assertEquals(cartaAnchoEspada, this._carta.ganador(cartaAnchoEspada));
	}
}
