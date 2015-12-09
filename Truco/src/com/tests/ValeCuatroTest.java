package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.exceptions.NoHayAccionesException;

import NuevasAcciones.ReTruco;
import NuevasAcciones.Truco;
import NuevasAcciones.ValeCuatro;

public class ValeCuatroTest {

	private ValeCuatro _valeCuatro;
	
	@Before
	public void setup(){
		Truco truco = new Truco(null, null);
		ReTruco reTruco = (ReTruco)truco.getNuevaAccion(truco.getAccionesPosibles().get(0), null, null);
		this._valeCuatro = (ValeCuatro)reTruco.getNuevaAccion(reTruco.getAccionesPosibles().get(0), null, null);
	}
	
	@Test
	public void testElIDDebeSerValeCuatro(){
		Assert.assertEquals("Vale Cuatro", this._valeCuatro.getID());
	}
	
	@Test (expected = NoHayAccionesException.class)
	public void testSePideUnaNuevaAccionDebeTirarExcepcion(){
		this._valeCuatro.getNuevaAccion(this._valeCuatro.getAccionesPosibles().get(0), null, null);
	}
		
	@Test
	public void testLaCantidadDePuntosNoQueridosDebeSerTres(){
		Assert.assertEquals(3, this._valeCuatro.getPuntosNoQueridos());
	}
	
	@Test
	public void testLaCantidadDePuntosQueridosDebeSerCuatro(){
		Assert.assertEquals(4, this._valeCuatro.getPuntosQueridos());
	}
}
