package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.Equipo;
import com.modelo.RondaPicaPica;
import com.modelo.RondaRedonda;

public class RondaPicaPicaTest {

	private RondaPicaPica _ronda;
	
	@Before
	public void setup(){
		this._ronda = new RondaPicaPica(new Equipo(), new Equipo());
	}

	@Test
	public void testSiguienteRondaDebeSerRondaRedonda(){
		Assert.assertTrue(this._ronda.getRondaSiguiente(true) instanceof RondaRedonda);
		Assert.assertTrue(this._ronda.getRondaSiguiente(false) instanceof RondaRedonda);
	}
}
