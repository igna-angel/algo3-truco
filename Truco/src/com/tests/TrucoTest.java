package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.Partido;
import com.modelo.acciones.Accion;
import com.modelo.acciones.Truco;

public class TrucoTest {

	private Truco _truco;
	private Partido _partido;
	
	@Before
	public void setup(){
		this._partido = new Partido(false);
		this._truco = new Truco(null, null);
	}
	
	@Test
	public void testElIDDebeSerTruco(){
		Assert.assertEquals("Truco", this._truco.getID());
	}
	
	@Test
	public void testSePideUnaNuevaAccionDebeSerReTruco(){
		Accion nuevaAccion = this._truco.getNuevaAccion(this._truco.getAccionPosible("Re Truco"), null, null, this._partido);
		Assert.assertEquals("Re Truco", nuevaAccion.getID());
	}
		
	@Test
	public void testLaCantidadDePuntosNoQueridosDebeSerUno(){
		Assert.assertEquals(1, this._truco.getPuntosNoQueridos());
	}
	
	@Test
	public void testLaCantidadDePuntosQueridosDebeSerDos(){
		Assert.assertEquals(2, this._truco.getPuntosQueridos());
	}
	
	@Test
	public void testSeCantaReTrucoElPuntajeQueridoDebeSerTres(){
		Accion nuevaAccion = this._truco.getNuevaAccion(this._truco.getAccionPosible("Re Truco"), null, null, this._partido);
		Assert.assertEquals(3, nuevaAccion.getPuntosQueridos());
	}
}
