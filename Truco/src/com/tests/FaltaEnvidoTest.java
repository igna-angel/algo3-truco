package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.acciones.Accion;
import com.acciones.Envido;
import com.acciones.EnvidoEnvido;
import com.acciones.FaltaEnvido;
import com.acciones.RealEnvido;
import com.exceptions.NoHayAccionesException;
import com.modelo.JugadorHumano;
import com.modelo.Partido;

public class FaltaEnvidoTest {
	private Envido _envido;
	private EnvidoEnvido _envidoEnvido;
	private RealEnvido _realEnvido;
	private FaltaEnvido _faltaEnvido;
	
	private RealEnvido _envidoRealEnvido;
	private RealEnvido _envidoEnvidoEnvidoRealEnvido;
	
	@Before
	public void setup(){
		this._faltaEnvido = new FaltaEnvido(null, null);
		
		this._envido = new Envido(null, null);
		this._envidoEnvido = (EnvidoEnvido) this._envido.getNuevaAccion(this._envido.getAccionPosible(Accion.ACCION_ENVIDO_ENVIDO), null, null);
		this._realEnvido = new RealEnvido(null, null);
		
		this._envidoRealEnvido = (RealEnvido) this._envido.getNuevaAccion(this._envido.getAccionPosible(Accion.ACCION_REAL_ENVIDO), null, null);
		this._envidoEnvidoEnvidoRealEnvido = (RealEnvido)this._envidoEnvido.getNuevaAccion(this._envidoEnvido.getAccionPosible(Accion.ACCION_REAL_ENVIDO), null, null);
	}
	
	@Test
	public void testElIDDebeSerFaltaEnvido(){
		Assert.assertEquals(Accion.ACCION_FALTA_ENVIDO, this._faltaEnvido.getID());
	}
	
	@Test (expected = NoHayAccionesException.class)
	public void testSePideUnaNuevaAccionDebeTirarExcepcion(){
		this._faltaEnvido.getNuevaAccion(this._faltaEnvido.getAccionesPosibles().get(0), null, null);
	}
	
	@Test
	public void testLaCantidadDePuntosNoQueridosDebenSerUnoDosTresYCuatro(){
		Assert.assertEquals(1, this._faltaEnvido.getPuntosNoQueridos());
		
		this._faltaEnvido = (FaltaEnvido) this._envido.getNuevaAccion(this._envido.getAccionPosible(Accion.ACCION_FALTA_ENVIDO), null, null);
		Assert.assertEquals(2, this._faltaEnvido.getPuntosNoQueridos());
		
		this._faltaEnvido = (FaltaEnvido) this._realEnvido.getNuevaAccion(this._realEnvido.getAccionPosible(Accion.ACCION_FALTA_ENVIDO), null, null);
		Assert.assertEquals(2, this._faltaEnvido.getPuntosNoQueridos());
						
		this._faltaEnvido = (FaltaEnvido) this._envidoEnvido.getNuevaAccion(this._envidoEnvido.getAccionPosible(Accion.ACCION_FALTA_ENVIDO), null, null);
		Assert.assertEquals(3, this._faltaEnvido.getPuntosNoQueridos());
		
		this._faltaEnvido = (FaltaEnvido) this._envidoRealEnvido.getNuevaAccion(this._envidoRealEnvido.getAccionPosible(Accion.ACCION_FALTA_ENVIDO), null, null);
		Assert.assertEquals(3, this._faltaEnvido.getPuntosNoQueridos());
				
		this._faltaEnvido = (FaltaEnvido) this._envidoEnvidoEnvidoRealEnvido.getNuevaAccion(this._envidoEnvidoEnvidoRealEnvido.getAccionPosible(Accion.ACCION_FALTA_ENVIDO), null, null);
		Assert.assertEquals(4, this._faltaEnvido.getPuntosNoQueridos());
	}
	
	@Test
	public void testLaCantidadDePuntosQueridosDebenSerLasDiferenciasParaLlegarAQuinceYTreinta(){
		Partido partido = new Partido();
		partido.agregarEquipo();
		partido.agregarEquipo();

		partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
				
		Assert.assertEquals(15, this._faltaEnvido.getPuntosQueridos(partido, partido.getPrimerEquipo()));
		Assert.assertEquals(15, this._faltaEnvido.getPuntosQueridos(partido, partido.getUltimoEquipo()));
		
		partido.agregarPuntos(5, 7);
		
		Assert.assertEquals(10, this._faltaEnvido.getPuntosQueridos(partido, partido.getPrimerEquipo()));
		Assert.assertEquals(8, this._faltaEnvido.getPuntosQueridos(partido, partido.getUltimoEquipo()));
		
		partido.agregarPuntos(11, 0);
		
		Assert.assertEquals(14, this._faltaEnvido.getPuntosQueridos(partido, partido.getPrimerEquipo()));
		Assert.assertEquals(8, this._faltaEnvido.getPuntosQueridos(partido, partido.getUltimoEquipo()));
		
		partido.agregarPuntos(0, 8);
		
		Assert.assertEquals(14, this._faltaEnvido.getPuntosQueridos(partido, partido.getPrimerEquipo()));
		Assert.assertEquals(15, this._faltaEnvido.getPuntosQueridos(partido, partido.getUltimoEquipo()));
		
	}
}
