package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.JugadorHumano;
import com.modelo.Partido;
import com.modelo.Ronda;
import com.modelo.RondaPicaPica;
import com.modelo.RondaRedonda;

public class RondaRedondaTest {
	private Ronda _ronda;
	
	@Before
	public void setup(){
		Partido partido = new Partido();
		partido.agregarEquipo();
		partido.agregarEquipo();
		partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		
		partido.crearPartido();
		partido.nuevaRonda();
		
		this._ronda = partido.getRondaActual();
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
