package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.Equipo;
import com.modelo.RondaPicaPica;
import com.modelo.RondaRedonda;

public class RondaRedondaTest {
	private RondaRedonda _ronda;
	
	@Before
	public void setup(){
		this._ronda = new RondaRedonda(new Equipo(), new Equipo());
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
