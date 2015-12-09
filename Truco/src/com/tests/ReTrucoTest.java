package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import NuevasAcciones.Accion;
import NuevasAcciones.ReTruco;
import NuevasAcciones.Truco;

public class ReTrucoTest {
	private ReTruco _reTruco;
	
	@Before
	public void setup(){
		Truco truco = new Truco(null, null);
		this._reTruco = (ReTruco)truco.getNuevaAccion(truco.getAccionesPosibles().get(0), null, null);
	}
	
	@Test
	public void testElIDDebeSerReTruco(){
		Assert.assertEquals("Re Truco", this._reTruco.getID());
	}
	
	@Test
	public void testSePideUnaNuevaAccionDebeSerValeCuatro(){
		Accion nuevaAccion = this._reTruco.getNuevaAccion(this._reTruco.getAccionesPosibles().get(0), null, null);
		Assert.assertEquals("Vale Cuatro", nuevaAccion.getID());
	}
		
	@Test
	public void testLaCantidadDePuntosNoQueridosDebeSerDos(){
		Assert.assertEquals(2, this._reTruco.getPuntosNoQueridos());
	}
	
	@Test
	public void testLaCantidadDePuntosQueridosDebeSerTres(){
		Assert.assertEquals(3, this._reTruco.getPuntosQueridos());
	}
	
	@Test
	public void testSeCantaValeCuatroElPuntajeQueridoDebeSerCuatro(){
		Accion nuevaAccion = this._reTruco.getNuevaAccion(this._reTruco.getAccionesPosibles().get(0), null, null);
		Assert.assertEquals(4, nuevaAccion.getPuntosQueridos());
	}
}
