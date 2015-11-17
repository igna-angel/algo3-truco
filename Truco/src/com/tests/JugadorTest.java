package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.Carta;
import com.modelo.Jugador;
import com.modelo.JugadorHumano;
import com.modelo.Vuelta;

public class JugadorTest {
	private Jugador _jugador = null;
	
	@Before
	public void setup(){
		this._jugador = new JugadorHumano();
	}
	
	@Test
	public void testSeCreaJugadorVacio(){
		Assert.assertEquals(0, this._jugador.getCantidadCartas());
	}
	
	@Test
	public void testElJugadorRecibeUnaCarta(){
		Assert.assertEquals(0, this._jugador.getCantidadCartas());
		this._jugador.recibirCarta(new Carta());
		Assert.assertEquals(1, this._jugador.getCantidadCartas());
	}
	
	@Test
	public void testElJugadorDevuelveLasCartas(){
		Assert.assertEquals(0, this._jugador.getCantidadCartas());

		this._jugador.recibirCarta(new Carta());
		this._jugador.recibirCarta(new Carta());
		this._jugador.recibirCarta(new Carta());
		
		Assert.assertEquals(3, this._jugador.getCantidadCartas());
		
		this._jugador.devolverCartas();
		
		Assert.assertEquals(0, this._jugador.getCantidadCartas());
	}
	
	@Test
	public void testSeBajaUnaCarta(){
		Vuelta vuelta = new Vuelta();
		
		Carta cartaTest = new Carta();
		
		Assert.assertEquals(0, this._jugador.getCantidadCartas());

		this._jugador.recibirCarta(cartaTest);

		Assert.assertEquals(1, this._jugador.getCantidadCartas());
		
		this._jugador.bajarCarta(vuelta, cartaTest);
		
		Assert.assertEquals(0, this._jugador.getCantidadCartas());	
	}
}
