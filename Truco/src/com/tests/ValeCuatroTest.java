package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.acciones.ReTruco;
import com.acciones.Truco;
import com.acciones.ValeCuatro;
import com.exceptions.NoHayAccionesException;
import com.modelo.Partido;

public class ValeCuatroTest {

	private ValeCuatro _valeCuatro;
	private Partido _partido;
	
	@Before
	public void setup(){
		this._partido = new Partido(true);
		Truco truco = new Truco(null, null);
		ReTruco reTruco = (ReTruco)truco.getNuevaAccion(truco.getAccionesPosibles().get(0), null, null, this._partido);
		this._valeCuatro = (ValeCuatro)reTruco.getNuevaAccion(reTruco.getAccionesPosibles().get(0), null, null, this._partido);
	}
	
	@Test
	public void testElIDDebeSerValeCuatro(){
		Assert.assertEquals("Vale Cuatro", this._valeCuatro.getID());
	}
	
	@Test (expected = NoHayAccionesException.class)
	public void testSePideUnaNuevaAccionDebeTirarExcepcion(){
		this._valeCuatro.getNuevaAccion(this._valeCuatro.getAccionesPosibles().get(0), null, null, this._partido);
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
