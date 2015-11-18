package com.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.exceptions.NumeroFueraDeRangoException;
import com.modelo.cartas.Carta;
import com.modelo.cartas.Carta.Palo;
import com.modelo.cartas.CartaAnchoBasto;
import com.modelo.cartas.CartaAnchoEspada;
import com.modelo.cartas.CartaAnchoFalso;
import com.modelo.cartas.CartaDos;
import com.modelo.cartas.CartaNormal;
import com.modelo.cartas.CartaSieteEspada;
import com.modelo.cartas.CartaSieteOro;
import com.modelo.cartas.CartaTres;

public class CartaNormalTest {
	
	private Carta _carta;
	
	@Before
	public void setup(){
		_carta = new CartaNormal();
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
	
	@Test
	public void testGanaACartaNormal(){
		Carta carta = new CartaNormal(Palo.Copa, 7);
		Carta cartaNormalGana = new CartaNormal(Palo.Copa, 4);
		Carta cartaNormalPierde = new CartaNormal(Palo.Copa, 11);
		Carta cartaNormalEmpataNoGana = new CartaNormal(Palo.Basto, 7);
		
		Assert.assertTrue(carta.ganaA(cartaNormalGana));
		Assert.assertFalse(carta.ganaA(cartaNormalPierde));
		Assert.assertFalse(carta.ganaA(cartaNormalEmpataNoGana));
	}
	
	@Test
	public void testGanaACartaAnchoFalso(){
		Carta carta = new CartaNormal(Palo.Copa, 7);
		Carta cartaFalso = new CartaAnchoFalso(Palo.Copa);
		Assert.assertFalse(carta.ganaA(cartaFalso));
	}
	
	@Test
	public void testGanaACartaDos(){
		Carta carta = new CartaNormal(Palo.Copa, 7);
		Carta cartaDos = new CartaDos(Palo.Copa);
		Assert.assertFalse(carta.ganaA(cartaDos));
	}
	
	@Test
	public void testGanaACartaTres(){
		Carta carta = new CartaNormal(Palo.Copa, 7);
		Carta cartaTres = new CartaTres(Palo.Copa);
		Assert.assertFalse(carta.ganaA(cartaTres));
	}
	
	@Test
	public void testGanaACartaSieteOro(){
		Carta carta = new CartaNormal(Palo.Copa, 7);
		Carta cartaSieteOro = new CartaSieteOro();
		Assert.assertFalse(carta.ganaA(cartaSieteOro));
	}
	
	@Test
	public void testGanaACartaSieteEspada(){
		Carta carta = new CartaNormal(Palo.Copa, 7);
		Carta cartaSieteEspada = new CartaSieteEspada();
		Assert.assertFalse(carta.ganaA(cartaSieteEspada));
	}
	
	@Test
	public void testGanaACartaAnchoBasto(){
		Carta carta = new CartaNormal(Palo.Copa, 7);
		Carta cartaAnchoBasto = new CartaAnchoBasto();
		Assert.assertFalse(carta.ganaA(cartaAnchoBasto));
	}
	
	@Test
	public void testGanaACartaAnchoEspada(){
		Carta carta = new CartaNormal(Palo.Copa, 7);
		Carta cartaAnchoEspada = new CartaAnchoEspada();
		Assert.assertFalse(carta.ganaA(cartaAnchoEspada));
	}
}

