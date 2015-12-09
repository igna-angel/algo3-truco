package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import NuevasAcciones.Accion;
import NuevasAcciones.Envido;
import NuevasAcciones.EnvidoEnvido;
import NuevasAcciones.RealEnvido;

public class RealEnvidoTest {
	private RealEnvido _realEnvido;
	private Envido _envido;
	private EnvidoEnvido _envidoEnvido;
	
	@Before
	public void setup(){
		this._envido = new Envido(null, null);
		this._envidoEnvido = (EnvidoEnvido) this._envido.getNuevaAccion(this._envido.getAccionPosible(Accion.ACCION_ENVIDO_ENVIDO), null, null);
	}
		
	@Test
	public void testElIDDebeSerRealEnvido(){
		this._realEnvido = new RealEnvido(null, null);
		Assert.assertEquals(Accion.ACCION_REAL_ENVIDO, this._realEnvido.getID());
		
		this._realEnvido = (RealEnvido) this._envido.getNuevaAccion(this._envido.getAccionPosible(Accion.ACCION_REAL_ENVIDO), null, null);
		Assert.assertEquals(Accion.ACCION_REAL_ENVIDO, this._realEnvido.getID());
		
		this._realEnvido = (RealEnvido) this._envidoEnvido.getNuevaAccion(this._envidoEnvido.getAccionPosible(Accion.ACCION_REAL_ENVIDO), null, null);
		Assert.assertEquals(Accion.ACCION_REAL_ENVIDO, this._realEnvido.getID());	
	}
	
	@Test
	public void testSePideUnaNuevaAccionDebeSerFaltaEnvido(){
		this._realEnvido = new RealEnvido(null, null);
		Accion nuevaAccion = this._realEnvido.getNuevaAccion(this._realEnvido.getAccionPosible(Accion.ACCION_FALTA_ENVIDO), null, null);
		Assert.assertEquals(Accion.ACCION_FALTA_ENVIDO, nuevaAccion.getID());
		
		this._realEnvido = (RealEnvido) this._envido.getNuevaAccion(this._envido.getAccionPosible(Accion.ACCION_REAL_ENVIDO), null, null);
		nuevaAccion = this._realEnvido.getNuevaAccion(this._realEnvido.getAccionPosible(Accion.ACCION_FALTA_ENVIDO), null, null);
		Assert.assertEquals(Accion.ACCION_FALTA_ENVIDO, nuevaAccion.getID());
		
		this._realEnvido = (RealEnvido) this._envido.getNuevaAccion(this._envidoEnvido.getAccionPosible(Accion.ACCION_REAL_ENVIDO), null, null);
		nuevaAccion = this._realEnvido.getNuevaAccion(this._realEnvido.getAccionPosible(Accion.ACCION_FALTA_ENVIDO), null, null);
		Assert.assertEquals(Accion.ACCION_FALTA_ENVIDO, nuevaAccion.getID());
	}

	@Test
	public void testLaCantidadDePuntosNoQueridosDebenSerUnoDosYTres(){
		this._realEnvido = new RealEnvido(null, null);
		Assert.assertEquals(1, this._realEnvido.getPuntosNoQueridos());
		
		this._realEnvido = (RealEnvido) this._envido.getNuevaAccion(this._envido.getAccionPosible(Accion.ACCION_REAL_ENVIDO), null, null);
		Assert.assertEquals(2, this._realEnvido.getPuntosNoQueridos());
		
		this._realEnvido = (RealEnvido) this._envidoEnvido.getNuevaAccion(this._envidoEnvido.getAccionPosible(Accion.ACCION_REAL_ENVIDO), null, null);
		Assert.assertEquals(3, this._realEnvido.getPuntosNoQueridos());	
	}
	
	@Test
	public void testLaCantidadDePuntosQueridosDebenSerTresCincoYSiete(){
		this._realEnvido = new RealEnvido(null, null);
		Assert.assertEquals(3, this._realEnvido.getPuntosQueridos());
		
		this._realEnvido = (RealEnvido) this._envido.getNuevaAccion(this._envido.getAccionPosible(Accion.ACCION_REAL_ENVIDO), null, null);
		Assert.assertEquals(5, this._realEnvido.getPuntosQueridos());
		
		this._realEnvido = (RealEnvido) this._envidoEnvido.getNuevaAccion(this._envidoEnvido.getAccionPosible(Accion.ACCION_REAL_ENVIDO), null, null);
		Assert.assertEquals(7, this._realEnvido.getPuntosQueridos());
	}
}
