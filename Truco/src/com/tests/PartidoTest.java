package com.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.exceptions.NoHayEquiposException;
import com.modelo.Partido;
import com.modelo.RondaPicaPica;
import com.modelo.RondaRedonda;

public class PartidoTest {
	
	private Partido _partido;
	
	@Before
	public void setup(){
		this._partido = new Partido();
	}
	
	@Test (expected = NoHayEquiposException.class)
	public void seCreaPartidoVacio(){
		Assert.assertEquals(0, this._partido.getCantidadDeRondas());
		Assert.assertEquals(0, this._partido.getCantidadEquipos());
		this._partido.getCantidadDeJugadoresPorEquipo();
	}
	
	@Test
	public void seAgregaUnEquipo(){
		this._partido.agregarEquipo();
		Assert.assertEquals(1, this._partido.getCantidadEquipos());
		Assert.assertEquals(0, this._partido.getCantidadDeJugadoresPorEquipo());
	}
	
	@Test
	public void seAgregaUnJugadorAEquipo(){
		this._partido.agregarEquipo();
		Assert.assertEquals(0, this._partido.getCantidadJugadoresEnEquipo(0));
		this._partido.agregarJugadorAEquipo(0);
		Assert.assertEquals(1, this._partido.getCantidadJugadoresEnEquipo(0));	
	}
	
	@Test
	public void seAgregaUnaRondaDebeSerRedonda(){
		this._partido.agregarEquipo();
		this._partido.agregarEquipo();
		
		this._partido.nuevaRonda();
		Assert.assertEquals(1, this._partido.getCantidadDeRondas());
		Assert.assertTrue(this._partido.getRondaActual() instanceof RondaRedonda);
	}
	
	@Test
	public void sePideUnaNuevaRondaDebeSerPicaPica(){
		this._partido.agregarEquipo();
		this._partido.agregarEquipo();
		
		this._partido.agregarJugadorAEquipo(0);
		this._partido.agregarJugadorAEquipo(0);
		this._partido.agregarJugadorAEquipo(0);
		this._partido.agregarJugadorAEquipo(1);
		this._partido.agregarJugadorAEquipo(1);
		this._partido.agregarJugadorAEquipo(1);
		
		this._partido.nuevaRonda();
		this._partido.getRondaActual().asignarPuntos(3, 4);
		this._partido.nuevaRonda();
		Assert.assertTrue(this._partido.getRondaActual() instanceof RondaRedonda);
		
		this._partido.getRondaActual().asignarPuntos(0, 1);
		this._partido.nuevaRonda();
		Assert.assertTrue(this._partido.getRondaActual() instanceof RondaPicaPica);
		
		this._partido.getRondaActual().asignarPuntos(0, 21);
		this._partido.nuevaRonda();
		Assert.assertTrue(this._partido.getRondaActual() instanceof RondaRedonda);
	}
	

}
