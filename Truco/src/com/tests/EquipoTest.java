package com.tests;

import org.junit.Before;
import org.junit.Test;

import com.modelo.Equipo;

import org.junit.Assert;

public class EquipoTest {
	
	private Equipo _equipo;
	
	@Before
	public void setup(){
		this._equipo = new Equipo();
	}
	
	@Test
	public void seCreaEquipoVacioYSinPuntos(){
		Assert.assertEquals(0, this._equipo.getCantidadJugadores());
		Assert.assertEquals(0, this._equipo.getPuntaje());
	}
	
	@Test
	public void seAgreganDosJugadoresAlEquipo(){
		this._equipo.agregarJugador();		
		this._equipo.agregarJugador();	

		Assert.assertEquals(2, this._equipo.getCantidadJugadores());
	}
	
	@Test
	public void seAgreganPuntosAlEquipo(){
		this._equipo.agregarPuntos(5);
		this._equipo.agregarPuntos(1);
		this._equipo.agregarPuntos(-5);
		
		Assert.assertEquals(11, this._equipo.getPuntaje());
	}
	
}
