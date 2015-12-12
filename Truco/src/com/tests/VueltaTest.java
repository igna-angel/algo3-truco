package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.Jugador;
import com.modelo.JugadorHumano;
import com.modelo.Partido;
import com.modelo.RondaRedonda;
import com.modelo.Vuelta;
import com.modelo.cartas.Carta;
import com.modelo.cartas.Carta.Palo;
import com.modelo.cartas.CartaAnchoFalso;
import com.modelo.cartas.CartaDos;
import com.modelo.cartas.CartaInvalida;
import com.modelo.cartas.CartaNormal;

public class VueltaTest {
	private Vuelta _vuelta;
	private Partido _partido;
	
	@Before
	public void setup(){
		this._partido = new Partido(false);
		this._partido.agregarEquipo();
		this._partido.agregarEquipo();
		
		Jugador jugadorA = new JugadorHumano();
		Jugador jugadorB = new JugadorHumano();
		
		this._partido.agregarJugadorAEquipo(jugadorA, 0);
		this._partido.agregarJugadorAEquipo(jugadorB, 1);
		
		this._partido.crearPartido();
		this._partido.nuevaRonda();
		
		this._partido.recolectarCartasDeJugadores();
		
		this._vuelta = this._partido.getVueltaActual(); 
				//new Vuelta(new RondaRedonda(partido, partido.getEquipos().getFirst().getJugadores().getFirst()), null,new JugadorHumano());

	}
	
	@Test
	public void testSeCreaVueltaVacia(){
		Assert.assertTrue(this._vuelta.getCartaGanadora() instanceof CartaInvalida);
		Assert.assertEquals(0, this._vuelta.getCantidadDeCartasEnVuelta());
	}
	
	@Test
	public void testSeRecibenCartas(){
		Carta cartaNormal = new CartaNormal(Palo.Copa, 12);
		Carta cartaDos = new CartaDos(Palo.Oro); 
		Carta cartaFalso = new CartaAnchoFalso(Palo.Oro); 
		
		this._partido.getOrdenJugadores().getFirst().recibirCarta(cartaNormal);
		this._partido.getOrdenJugadores().getFirst().recibirCarta(cartaFalso);
		this._partido.getOrdenJugadores().getLast().recibirCarta(cartaDos);
		
		this._vuelta.recibirCarta(cartaNormal);
		Assert.assertEquals(1, this._vuelta.getCantidadDeCartasEnVuelta());
		Assert.assertTrue(this._vuelta.getCartaGanadora() instanceof CartaNormal);
		
		this._vuelta.recibirCarta(cartaDos);
		Assert.assertEquals(2, this._vuelta.getCantidadDeCartasEnVuelta());
		Assert.assertTrue(this._vuelta.getCartaGanadora() instanceof CartaDos);
		
		this._vuelta.recibirCarta(cartaFalso);
		Assert.assertEquals(3, this._vuelta.getCantidadDeCartasEnVuelta());
		Assert.assertTrue(this._vuelta.getCartaGanadora() instanceof CartaDos);
	}
}
