package com.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.exceptions.NumeroCartasACompararIncorrectoException;
import com.modelo.ComparadorCartas;
import com.modelo.Mazo;

public class ComparadorCartasTest {

	private Mazo mazo;
	private ComparadorCartas comparador;
	
	@Before
	public void setup(){
		mazo = new Mazo();
		this.mazo.crear();
		comparador = new ComparadorCartas();
	}
	
	@Test
	public void testComparadorCuatroEspadaPierdeOParda(){
		//Espada
		Assert.assertEquals(5,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(1)).getNumero());
		Assert.assertEquals(6,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(2)).getNumero());
		Assert.assertEquals(10,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(3)).getNumero());
		Assert.assertEquals(11,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(4)).getNumero());
		Assert.assertEquals(12,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(5)).getNumero());
		
		//Basto
		Assert.assertEquals(4,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(6)).getNumero());
		Assert.assertEquals(5,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(7)).getNumero());
		Assert.assertEquals(6,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(8)).getNumero());
		Assert.assertEquals(10,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(9)).getNumero());
		Assert.assertEquals(11,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(10)).getNumero());
		Assert.assertEquals(12,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(11)).getNumero());
		
		//Oro
		Assert.assertEquals(4,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(12)).getNumero());
		Assert.assertEquals(5,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(13)).getNumero());
		Assert.assertEquals(6,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(14)).getNumero());
		Assert.assertEquals(10,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(15)).getNumero());
		Assert.assertEquals(11,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(16)).getNumero());
		Assert.assertEquals(12,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(17)).getNumero());
		
		//Copa
		Assert.assertEquals(4,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(18)).getNumero());
		Assert.assertEquals(5,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(19)).getNumero());
		Assert.assertEquals(6,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(20)).getNumero());
		Assert.assertEquals(10,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(21)).getNumero());
		Assert.assertEquals(11,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(22)).getNumero());
		Assert.assertEquals(12,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(23)).getNumero());
		
		//Sietes basto y copa
		Assert.assertEquals(7,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(24)).getNumero());
		Assert.assertEquals(7,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(25)).getNumero());
		
		//Unos
		Assert.assertEquals(1,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(26)).getNumero());
		Assert.assertEquals(1,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(27)).getNumero());
		Assert.assertEquals(1,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(28)).getNumero());
		Assert.assertEquals(1,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(29)).getNumero());
		
		//Dos y tres
		Assert.assertEquals(2,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(30)).getNumero());
		Assert.assertEquals(3,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(31)).getNumero());
		
		Assert.assertEquals(2,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(32)).getNumero());
		Assert.assertEquals(3,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(33)).getNumero());
		
		Assert.assertEquals(2,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(34)).getNumero());
		Assert.assertEquals(3,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(35)).getNumero());
		
		Assert.assertEquals(2,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(36)).getNumero());
		Assert.assertEquals(3,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(37)).getNumero());
		
		//Sietes oro y espada
		Assert.assertEquals(7,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(38)).getNumero());
		Assert.assertEquals(7,comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(39)).getNumero());
	}
	
	@Test (expected = NumeroCartasACompararIncorrectoException.class)
	public void testSiIngresoNumeroIncorrectoDeCartasPosibleOcurreExcepcion(){
		comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(1),mazo.getCarta(2));
	}
}
