package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.Partido;
import com.modelo.RondaPicaPica;
import com.modelo.RondaRedonda;

public class RondaRedondaTest {
	private RondaRedonda _ronda;
	
	@Before
	public void setup(){
		Partido partido = new Partido();
		partido.agregarEquipo();
		partido.agregarEquipo();
		partido.agregarJugadorAEquipo(0);
		partido.agregarJugadorAEquipo(1);
		partido.crearOrdenJugadores();
		partido.getOrdenJugadores().resetToFirst();
		
		this._ronda = new RondaRedonda(partido, partido.getOrdenJugadores().getCurrent());
	}
	
	@Test
	public void testSiguienteRondaDebeSerRondaPicaPica(){
		Assert.assertTrue(this._ronda.getRondaSiguiente(true) instanceof RondaPicaPica);
	}
	
	@Test
	public void testSiguienteRondaDebeSerRondaRedonda(){
		Assert.assertTrue(this._ronda.getRondaSiguiente(false) instanceof RondaRedonda);
	}
}
