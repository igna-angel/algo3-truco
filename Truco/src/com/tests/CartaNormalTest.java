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
import com.modelo.cartas.CartaInvalida;
import com.modelo.cartas.CartaNormal;
import com.modelo.cartas.CartaSieteEspada;
import com.modelo.cartas.CartaSieteOro;
import com.modelo.cartas.CartaTres;

public class CartaNormalTest {
	
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
	
	@Test
	public void testGanaACartaInvalida(){
		Carta carta = new CartaNormal(Palo.Copa, 4);
		Carta cartaInvalida = new CartaInvalida();
		
		Assert.assertEquals(carta, carta.ganador(cartaInvalida));
	}
	
	@Test
	public void testGanaACartaNormal(){
		Carta carta = new CartaNormal(Palo.Copa, 7);
		Carta cartaNormalGana = new CartaNormal(Palo.Copa, 4);
		Carta cartaNormalPierde = new CartaNormal(Palo.Copa, 11);
		Carta cartaNormalEmpataNoGana = new CartaNormal(Palo.Basto, 7);
		
		Assert.assertEquals(carta, carta.ganador(cartaNormalGana));
		Assert.assertEquals(cartaNormalPierde, carta.ganador(cartaNormalPierde));
		Assert.assertEquals(carta, carta.ganador(cartaNormalEmpataNoGana));
	}
	
	@Test
	public void testGanaACartaAnchoFalso(){
		Carta carta = new CartaNormal(Palo.Copa, 7);
		Carta cartaFalso = new CartaAnchoFalso(Palo.Copa);
		Assert.assertEquals(cartaFalso, carta.ganador(cartaFalso));
	}
	
	@Test
	public void testGanaACartaDos(){
		Carta carta = new CartaNormal(Palo.Copa, 7);
		Carta cartaDos = new CartaDos(Palo.Copa);
		Assert.assertEquals(cartaDos, carta.ganador(cartaDos));
	}
	
	@Test
	public void testGanaACartaTres(){
		Carta carta = new CartaNormal(Palo.Copa, 7);
		Carta cartaTres = new CartaTres(Palo.Copa);
		Assert.assertEquals(cartaTres, carta.ganador(cartaTres));
	}
	
	@Test
	public void testGanaACartaSieteOro(){
		Carta carta = new CartaNormal(Palo.Copa, 7);
		Carta cartaSieteOro = new CartaSieteOro();
		Assert.assertEquals(cartaSieteOro, carta.ganador(cartaSieteOro));
	}
	
	@Test
	public void testGanaACartaSieteEspada(){
		Carta carta = new CartaNormal(Palo.Copa, 7);
		Carta cartaSieteEspada = new CartaSieteEspada();
		Assert.assertEquals(cartaSieteEspada, carta.ganador(cartaSieteEspada));
	}
	
	@Test
	public void testGanaACartaAnchoBasto(){
		Carta carta = new CartaNormal(Palo.Copa, 7);
		Carta cartaAnchoBasto = new CartaAnchoBasto();
		Assert.assertEquals(cartaAnchoBasto, carta.ganador(cartaAnchoBasto));
	}
	
	@Test
	public void testGanaACartaAnchoEspada(){
		Carta carta = new CartaNormal(Palo.Copa, 7);
		Carta cartaAnchoEspada = new CartaAnchoEspada();
		Assert.assertEquals(cartaAnchoEspada, carta.ganador(cartaAnchoEspada));
	}
}

