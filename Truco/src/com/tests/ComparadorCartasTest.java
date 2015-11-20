package com.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.modelo.ComparadorCartas;
import com.modelo.Mazo;
import com.modelo.cartas.*;

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
	public void testComparadorCuatroContraCincoGanaCinco(){
		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(1)) instanceof CartaNormal);
	}
//	
//	@Test
//	public void testComparadorUnoBastoContraTodosGanaExceptoContraUnoEspada(){
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(1),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(2),mazo.getCarta(0)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(3),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(4),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(5),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(6),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(7),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(8),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(9),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(10),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(11),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(10),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(13),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(14),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(15),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(16),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(17),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(18),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(19),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(20),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(21),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(22),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(23),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(24),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(25),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(26),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(27),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(28),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(29),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(30),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(31),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(32),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(33),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(34),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(35),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(36),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(37),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(38),mazo.getCarta(0)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(39),mazo.getCarta(0)).getPalo() instanceof Basto);
//	}
//	
//	@Test
//	public void testComparadorSieteEspadaContraTodosGanaExceptoContraUnoBastoYUnoEspada(){
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(26)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(1),mazo.getCarta(26)).getPalo() instanceof Espada);
//		//Ganaria UnoEspada aca
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(2),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(2),mazo.getCarta(26)) instanceof CartaUno);
//		//Ganaria UnoEspada aca
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(3),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(4),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(5),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(6),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(7),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(8),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(9),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(10),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(11),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(12),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(13),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(14),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(15),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(16),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(17),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(18),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(19),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(20),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(21),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(22),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(23),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(24),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(25),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(27),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(28),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(29),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(30),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(31),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(32),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(33),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(34),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(35),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(36),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(37),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(38),mazo.getCarta(26)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(39),mazo.getCarta(26)).getPalo() instanceof Espada);
//	}
//	
//	@Test
//	public void testComparadorSieteOroContraTodosGanaExceptoContraUnoBastoYUnoEspadaYSieteEspada(){
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(0),mazo.getCarta(25)).getPalo() instanceof Basto);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(1),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(2),mazo.getCarta(25)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(3),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(4),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(5),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(6),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(7),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(8),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(9),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(10),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(11),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(12),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(13),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(14),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(15),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(16),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(17),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(18),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(19),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(20),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(21),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(22),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(23),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(24),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(26),mazo.getCarta(25)).getPalo() instanceof Espada);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(27),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(28),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(29),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(30),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(31),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(32),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(33),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(34),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(35),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(36),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(37),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(38),mazo.getCarta(25)).getPalo() instanceof Oro);
//		Assert.assertTrue(comparador.compararCartas(mazo.getCarta(39),mazo.getCarta(25)).getPalo() instanceof Oro);
//	}
}
