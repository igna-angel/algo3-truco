package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.Partido;
import com.modelo.acciones.Accion;
import com.modelo.acciones.Envido;

public class EnvidoTest {

	private Envido _envido;
	private Partido _partido;
	
	@Before
	public void setup(){
		this._partido = new Partido(true);
		this._envido = new Envido(null, null);
	}
	
	@Test
	public void testElIDDebeSerEnvido(){
		Assert.assertEquals(Accion.ACCION_ENVIDO, this._envido.getID());
	}
	
	@Test
	public void testSePideUnaNuevaAccionDebeSerEnvidoEnvido(){
		Accion nuevaAccion = this._envido.getNuevaAccion(this._envido.getAccionPosible(Accion.ACCION_ENVIDO_ENVIDO), null, null, this._partido);
		Assert.assertEquals(Accion.ACCION_ENVIDO_ENVIDO, nuevaAccion.getID());
	}
	
	@Test
	public void testSePideUnaNuevaAccionDebeSerRealEnvido(){
		Accion nuevaAccion = this._envido.getNuevaAccion(this._envido.getAccionPosible(Accion.ACCION_REAL_ENVIDO), null, null, this._partido);
		Assert.assertEquals(Accion.ACCION_REAL_ENVIDO, nuevaAccion.getID());
	}
	
	@Test
	public void testSePideUnaNuevaAccionDebeSerFaltaEnvido(){
		Accion nuevaAccion = this._envido.getNuevaAccion(this._envido.getAccionPosible(Accion.ACCION_FALTA_ENVIDO), null, null, this._partido);
		Assert.assertEquals(Accion.ACCION_FALTA_ENVIDO, nuevaAccion.getID());
	}
	
	@Test
	public void testLaCantidadDePuntosNoQueridosDebeSerUno(){
		Assert.assertEquals(1, this._envido.getPuntosNoQueridos());
	}
	
	@Test
	public void testLaCantidadDePuntosQueridosDebeSerDos(){
		Assert.assertEquals(2, this._envido.getPuntosQueridos());
	}
	
	@Test
	public void testSeCantaEnvidoEnvidoElPuntajeQueridoDebeSerCuatro(){
		Accion nuevaAccion = this._envido.getNuevaAccion(this._envido.getAccionPosible(Accion.ACCION_ENVIDO_ENVIDO), null, null, this._partido);
		Assert.assertEquals(4, nuevaAccion.getPuntosQueridos());
	}
	
	@Test
	public void testSeCantaRealEnvidoElPuntajeQueridoDebeSerCinco(){
		Accion nuevaAccion = this._envido.getNuevaAccion(this._envido.getAccionPosible(Accion.ACCION_REAL_ENVIDO), null, null, this._partido);
		Assert.assertEquals(5, nuevaAccion.getPuntosQueridos());
	}
}
