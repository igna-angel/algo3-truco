package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.Partido;
import com.modelo.acciones.Accion;
import com.modelo.acciones.Envido;
import com.modelo.acciones.EnvidoEnvido;

public class EnvidoEnvidoTest {
	private EnvidoEnvido _envidoEnvido;
	private Partido _partido;
	
	@Before
	public void setup(){
		this._partido = new Partido(true);
		Envido envido = new Envido(null, null);
		this._envidoEnvido = (EnvidoEnvido) envido.getNuevaAccion(envido.getAccionPosible(Accion.ACCION_ENVIDO_ENVIDO), null, null, this._partido);
	}
	
	@Test
	public void testElIDDebeSerEnvidoEnvido(){
		Assert.assertEquals(Accion.ACCION_ENVIDO_ENVIDO, this._envidoEnvido.getID());
	}
	
	@Test
	public void testSePideUnaNuevaAccionDebeSerRealEnvido(){
		Accion nuevaAccion = this._envidoEnvido.getNuevaAccion(this._envidoEnvido.getAccionPosible(Accion.ACCION_REAL_ENVIDO), null, null, this._partido);
		Assert.assertEquals(Accion.ACCION_REAL_ENVIDO, nuevaAccion.getID());
	}
	
	@Test
	public void testSePideUnaNuevaAccionDebeSerFaltaEnvido(){
		Accion nuevaAccion = this._envidoEnvido.getNuevaAccion(this._envidoEnvido.getAccionPosible(Accion.ACCION_FALTA_ENVIDO), null, null, this._partido);
		Assert.assertEquals(Accion.ACCION_FALTA_ENVIDO, nuevaAccion.getID());
	}
	
	@Test
	public void testLaCantidadDePuntosNoQueridosDebeSerDos(){
		Assert.assertEquals(2, this._envidoEnvido.getPuntosNoQueridos());
	}
	
	@Test
	public void testLaCantidadDePuntosQueridosDebeSerCuatro(){
		Assert.assertEquals(4, this._envidoEnvido.getPuntosQueridos());
	}
	
	@Test
	public void testSeCantaRealEnvidoElPuntajeQueridoDebeSerSiete(){
		Accion nuevaAccion = this._envidoEnvido.getNuevaAccion(this._envidoEnvido.getAccionPosible(Accion.ACCION_REAL_ENVIDO), null, null, this._partido);
		Assert.assertEquals(7, nuevaAccion.getPuntosQueridos());
	}
}
