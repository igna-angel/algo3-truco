package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.Basto;
import com.modelo.Copa;
import com.modelo.Espada;
import com.modelo.Oro;
import com.modelo.Palo;
import com.modelo.cartas.CartaUno;

public class CartaUnoTest {
	
	private CartaUno _cartaUnoEspada;
	private CartaUno _cartaUnoOro;
	private CartaUno _cartaUnoCopa;
	private CartaUno _cartaUnoBasto;
	
	private Palo oro = new Oro();
	private Palo copa = new Copa();
	private Palo basto = new Basto();
	private Palo espada = new Espada();
	
	@Before
	public void setup(){
		_cartaUnoEspada = new CartaUno(espada);
		_cartaUnoCopa = new CartaUno(copa);
		_cartaUnoOro = new CartaUno(oro);
		_cartaUnoBasto = new CartaUno(basto);
	}
	
	@Test
	public void cartaUnoEspadaGanaATodas(){
		Assert.assertEquals(_cartaUnoEspada,_cartaUnoEspada.vs(_cartaUnoBasto));
		Assert.assertEquals(_cartaUnoEspada,_cartaUnoEspada.vs(_cartaUnoOro));
		Assert.assertEquals(_cartaUnoEspada,_cartaUnoEspada.vs(_cartaUnoCopa));
	}
	
	@Test
	public void cartaUnoBastoGanaATodasMenosEspada(){
		Assert.assertEquals(_cartaUnoEspada,_cartaUnoBasto.vs(_cartaUnoEspada));
		Assert.assertEquals(_cartaUnoBasto,_cartaUnoBasto.vs(_cartaUnoOro));
		Assert.assertEquals(_cartaUnoBasto,_cartaUnoBasto.vs(_cartaUnoCopa));
	}
	
	@Test
	public void cartaUnoOroPierdeYPardaContraCopa(){
		Assert.assertEquals(_cartaUnoEspada,_cartaUnoOro.vs(_cartaUnoEspada));
		Assert.assertEquals(_cartaUnoBasto,_cartaUnoOro.vs(_cartaUnoBasto));
		
		Assert.assertNotEquals(_cartaUnoEspada,_cartaUnoOro.vs(_cartaUnoCopa));
		Assert.assertNotEquals(_cartaUnoBasto,_cartaUnoOro.vs(_cartaUnoCopa));
		Assert.assertNotEquals(_cartaUnoCopa,_cartaUnoOro.vs(_cartaUnoCopa));
		//significa Parda
	}
	
	@Test
	public void cartaUnoCopaPierdeYPardaContraOro(){
		Assert.assertEquals(_cartaUnoEspada,_cartaUnoCopa.vs(_cartaUnoEspada));
		Assert.assertEquals(_cartaUnoBasto,_cartaUnoCopa.vs(_cartaUnoBasto));
		
		Assert.assertNotEquals(_cartaUnoEspada,_cartaUnoCopa.vs(_cartaUnoOro));
		Assert.assertNotEquals(_cartaUnoBasto,_cartaUnoCopa.vs(_cartaUnoOro));
		Assert.assertNotEquals(_cartaUnoOro,_cartaUnoCopa.vs(_cartaUnoOro));
		//significa Parda
	}
}
