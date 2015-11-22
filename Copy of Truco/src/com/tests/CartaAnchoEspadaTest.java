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

public class CartaAnchoEspadaTest {

	private Carta _carta;
	
	@Before
	public void setup(){
		this._carta = new CartaAnchoEspada();
	}

	@Test
	public void testSeCreaCartaConValoresValidos(){
		this._carta = new CartaAnchoEspada();
		Assert.assertEquals(Palo.Espada, this._carta.getPalo());
		Assert.assertEquals(1, this._carta.getNumero());
	}
	
	@Test
	public void testGanaACartaInvalida(){
		Carta carta = new CartaAnchoEspada();
		Carta cartaInvalida = new CartaInvalida();
		
		Assert.assertEquals(carta, carta.ganador(cartaInvalida));
	}
	
	@Test
	public void testGanaACartaNormal(){
		Carta carta = new CartaAnchoEspada();
		Carta cartaNormal = new CartaNormal(Palo.Copa, 12);
		
		Assert.assertEquals(carta, carta.ganador(cartaNormal));
	}
	
	@Test
	public void testGanaACartaAnchoFalso(){
		Carta carta = new CartaAnchoEspada();
		Carta cartaFalso = new CartaAnchoFalso(Palo.Oro);
		Assert.assertEquals(carta, carta.ganador(cartaFalso));
	}
	
	@Test
	public void testGanaACartaDos(){
		Carta carta = new CartaAnchoEspada();
		Carta cartaDos = new CartaDos(Palo.Copa);
		Assert.assertEquals(carta, carta.ganador(cartaDos));
	}
	
	@Test
	public void testGanaACartaTres(){
		Carta carta = new CartaAnchoEspada();
		Carta cartaTres = new CartaTres(Palo.Copa);
		Assert.assertEquals(carta, carta.ganador(cartaTres));
	}
	
	@Test
	public void testGanaACartaSieteOro(){
		Carta carta = new CartaAnchoEspada();
		Carta cartaSieteOro = new CartaSieteOro();
		Assert.assertEquals(carta, carta.ganador(cartaSieteOro));
	}
	
	@Test
	public void testGanaACartaSieteEspada(){
		Carta carta = new CartaAnchoEspada();
		Carta cartaSieteEspada = new CartaSieteEspada();
		Assert.assertEquals(carta, carta.ganador(cartaSieteEspada));
	}
	
	@Test
	public void testGanaACartaAnchoBasto(){
		Carta carta = new CartaAnchoEspada();
		Carta cartaAnchoBasto = new CartaAnchoBasto();
		Assert.assertEquals(carta, carta.ganador(cartaAnchoBasto));
	}
	
	@Test
	public void testGanaACartaAnchoEspada(){
		Carta carta = new CartaAnchoEspada();
		Carta cartaAnchoEspada = new CartaAnchoEspada();
		Assert.assertEquals(carta, carta.ganador(cartaAnchoEspada));
	}
}
