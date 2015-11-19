package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.Jugador;
import com.modelo.JugadorHumano;
import com.modelo.Vuelta;
import com.modelo.cartas.Carta;
import com.modelo.cartas.CartaNormal;

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
		this._jugador.recibirCarta(new CartaNormal());
		Assert.assertEquals(1, this._jugador.getCantidadCartas());
	}
	
	@Test
	public void testElJugadorDevuelveLasCartas(){
		Assert.assertEquals(0, this._jugador.getCantidadCartas());

		this._jugador.recibirCarta(new CartaNormal());
		this._jugador.recibirCarta(new CartaNormal());
		this._jugador.recibirCarta(new CartaNormal());
		
		Assert.assertEquals(3, this._jugador.getCantidadCartas());
		
		this._jugador.devolverCartas();
		
		Assert.assertEquals(0, this._jugador.getCantidadCartas());
	}
	
	@Test
	public void testSeBajaUnaCarta(){
		Vuelta vuelta = new Vuelta(null, null);
		
		Carta cartaTest = new CartaNormal();
		
		Assert.assertEquals(0, this._jugador.getCantidadCartas());

		this._jugador.recibirCarta(cartaTest);

		Assert.assertEquals(1, this._jugador.getCantidadCartas());
		
		this._jugador.bajarCarta(vuelta, cartaTest);
		
		Assert.assertEquals(0, this._jugador.getCantidadCartas());	
	}
}
