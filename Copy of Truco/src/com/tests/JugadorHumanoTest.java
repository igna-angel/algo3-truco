package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.Carta;
import com.modelo.JugadorHumano;
import com.modelo.Mano;

public class JugadorHumanoTest {
	
//	private JugadorHumano _jugador = null;
//	
//	@Before
//	public void setup(){
//		this._jugador = new JugadorHumano();
//	}
//	
//	@Test
//	public void testSeCreaJugadorVacio(){
//		Assert.assertEquals(0, this._jugador.getCantidadCartas());
//	}
//	
//	@Test
//	public void testElJugadorRecibeUnaCarta(){
//		this._jugador.recibirCarta(new Carta());
//		Assert.assertEquals(1, this._jugador.getCantidadCartas());
//	}
//	
//	@Test
//	public void testElJugadorDevuelveLasCartas(){
//		this._jugador.recibirCarta(new Carta());
//		this._jugador.recibirCarta(new Carta());
//		this._jugador.recibirCarta(new Carta());
//		
//		Assert.assertEquals(3, this._jugador.getCantidadCartas());
//		this._jugador.devolverCartas();
//		Assert.assertEquals(0, this._jugador.getCantidadCartas());
//	}
//	
//	@Test
//	public void testSeBajaUnaCarta(){
//		Mano mano = new Mano();
//		
//		Carta cartaTest = new Carta();
//		
//		this._jugador.recibirCarta(cartaTest);
//		this._jugador.bajarCarta(mano, cartaTest);
//		
//		Assert.assertEquals(0, this._jugador.getCantidadCartas());	
//	}
}
