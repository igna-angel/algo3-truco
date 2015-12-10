package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.acciones.Accion;
import com.acciones.Envido;
import com.acciones.EnvidoEnvido;

public class EnvidoEnvidoTest {
	private EnvidoEnvido _envidoEnvido;
	
	@Before
	public void setup(){
		Envido envido = new Envido(null, null);
		this._envidoEnvido = (EnvidoEnvido) envido.getNuevaAccion(envido.getAccionPosible(Accion.ACCION_ENVIDO_ENVIDO), null, null);
	}
	
	@Test
	public void testElIDDebeSerEnvidoEnvido(){
		Assert.assertEquals(Accion.ACCION_ENVIDO_ENVIDO, this._envidoEnvido.getID());
	}
	
	@Test
	public void testSePideUnaNuevaAccionDebeSerRealEnvido(){
		Accion nuevaAccion = this._envidoEnvido.getNuevaAccion(this._envidoEnvido.getAccionPosible(Accion.ACCION_REAL_ENVIDO), null, null);
		Assert.assertEquals(Accion.ACCION_REAL_ENVIDO, nuevaAccion.getID());
	}
	
	@Test
	public void testSePideUnaNuevaAccionDebeSerFaltaEnvido(){
		Accion nuevaAccion = this._envidoEnvido.getNuevaAccion(this._envidoEnvido.getAccionPosible(Accion.ACCION_FALTA_ENVIDO), null, null);
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
		Accion nuevaAccion = this._envidoEnvido.getNuevaAccion(this._envidoEnvido.getAccionPosible(Accion.ACCION_REAL_ENVIDO), null, null);
		Assert.assertEquals(7, nuevaAccion.getPuntosQueridos());
	}
}
