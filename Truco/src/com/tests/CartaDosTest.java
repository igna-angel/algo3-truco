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
import com.modelo.cartas.CartaPardaDos;
import com.modelo.cartas.CartaSieteEspada;
import com.modelo.cartas.CartaSieteOro;
import com.modelo.cartas.CartaTres;
import com.modelo.cartas.Carta.Palo;

public class CartaDosTest {
	
	private Carta _carta;
	
	@Before
	public void setup(){
		this._carta = new CartaDos(Palo.Copa);
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
	public void testGanaACartaInvalida(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaInvalida = new CartaInvalida();
		
		Assert.assertEquals(carta, carta.ganador(cartaInvalida));
	}
	
	@Test
	public void testGanaACartaNormal(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaNormal = new CartaNormal(Palo.Copa, 12);
		
		Assert.assertEquals(carta, carta.ganador(cartaNormal));
	}
	
	@Test
	public void testGanaACartaAnchoFalso(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaFalso = new CartaAnchoFalso(Palo.Oro);
		Assert.assertEquals(carta, carta.ganador(cartaFalso));
	}
	
	@Test
	public void testGanaACartaDos(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaDos = new CartaDos(Palo.Copa);
		Assert.assertTrue(carta.ganador(cartaDos) instanceof CartaPardaDos);
	}
	
	@Test
	public void testGanaACartaTres(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaTres = new CartaTres(Palo.Copa);
		Assert.assertEquals(cartaTres, carta.ganador(cartaTres));
	}
	
	@Test
	public void testGanaACartaSieteOro(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaSieteOro = new CartaSieteOro();
		Assert.assertEquals(cartaSieteOro, carta.ganador(cartaSieteOro));
	}
	
	@Test
	public void testGanaACartaSieteEspada(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaSieteEspada = new CartaSieteEspada();
		Assert.assertEquals(cartaSieteEspada, carta.ganador(cartaSieteEspada));
	}
	
	@Test
	public void testGanaACartaAnchoBasto(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaAnchoBasto = new CartaAnchoBasto();
		Assert.assertEquals(cartaAnchoBasto, carta.ganador(cartaAnchoBasto));
	}
	
	@Test
	public void testGanaACartaAnchoEspada(){
		Carta carta = new CartaDos(Palo.Copa);
		Carta cartaAnchoEspada = new CartaAnchoEspada();
		Assert.assertEquals(cartaAnchoEspada, carta.ganador(cartaAnchoEspada));
	}
}
