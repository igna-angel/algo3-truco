package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.modelo.Partido;
import com.modelo.acciones.Accion;
import com.modelo.acciones.ReTruco;
import com.modelo.acciones.Truco;

public class ReTrucoTest {
	private ReTruco _reTruco;
	private Partido _partido;
	
	@Before
	public void setup(){
		this._partido = new Partido(true);
		Truco truco = new Truco(null, null);
		this._reTruco = (ReTruco)truco.getNuevaAccion(truco.getAccionesPosibles().get(0), null, null, this._partido);
	}
	
	@Test
	public void testElIDDebeSerReTruco(){
		Assert.assertEquals("Re Truco", this._reTruco.getID());
	}
	
	@Test
	public void testSePideUnaNuevaAccionDebeSerValeCuatro(){
		Accion nuevaAccion = this._reTruco.getNuevaAccion(this._reTruco.getAccionesPosibles().get(0), null, null, this._partido);
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
		Accion nuevaAccion = this._reTruco.getNuevaAccion(this._reTruco.getAccionesPosibles().get(0), null, null, this._partido);
		Assert.assertEquals(4, nuevaAccion.getPuntosQueridos());
	}
}
