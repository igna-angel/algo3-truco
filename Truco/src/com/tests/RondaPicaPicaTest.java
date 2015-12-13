package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.JugadorHumano;
import com.modelo.Partido;
import com.modelo.Ronda;
import com.modelo.RondaRedonda;

public class RondaPicaPicaTest {

	private Ronda _ronda;
	
	@Before
	public void setup(){
		Partido partido = new Partido(false);
		partido.agregarEquipo();
		partido.agregarEquipo();
		partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		
		partido.crearPartido();
		partido.nuevaRonda();
		
		this._ronda = partido.getRondaActual().getRondaSiguiente(true);
	}

	@Test
	public void testSiguienteRondaDebeSerRondaRedonda(){
		Assert.assertTrue(this._ronda.getRondaSiguiente(true) instanceof RondaRedonda);
		Assert.assertTrue(this._ronda.getRondaSiguiente(false) instanceof RondaRedonda);
	}
}
