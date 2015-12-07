package com.tests;

import org.junit.Before;
import org.junit.Test;

import com.modelo.JugadorHumano;
import com.modelo.Partido;

import org.junit.Assert;


public class TrucoReTrucoValeCuatroTest {
	
	private Partido partido;
	
	@Before
	public void setup(){
		partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		
		partido.crearPartido();
		
		partido.getOrdenJugadores().resetToFirst();
	}
	
	@Test
	public void testSeCantaTrucoReTrucoValeCuatroQueridoDuranteTresRondasConsecutivas(){
		
		partido.nuevaRonda();

		partido.getRondaActual().nuevaVuelta();
		
		partido.getRondaActual().seCantoTruco();
		
		partido.getRondaActual().nuevaVueltaSecundaria(partido.getRondaActual().getVueltaActual().getAccionTruco());
		
		partido.getRondaActual().seCantoReTruco();
		
		partido.getRondaActual().nuevaVueltaSecundaria(partido.getRondaActual().getVueltaActual().getAccionTruco());
		
		partido.getRondaActual().seCantoValeCuatro();
		
		System.out.println("");

		partido.getRondaActual().agregarPuntajeTruco();
		
		Assert.assertEquals(4, this.partido.getRondaActual().getVueltaActual().getAccionTruco().cantar());
	}
	
	@Test
	public void testSeCantaTrucoReTrucoValeCuatroQueridoDuranteLaMismaRonda(){
		
		partido.nuevaRonda();

		partido.getRondaActual().nuevaVuelta();
		
		partido.getRondaActual().seCantoTruco();
		
		partido.getRondaActual().seCantoReTruco();
		
		partido.getRondaActual().seCantoValeCuatro();
		
		System.out.println("");

		partido.getRondaActual().agregarPuntajeTruco();
		
		Assert.assertEquals(4, this.partido.getRondaActual().getVueltaActual().getAccionTruco().cantar());
	}
	
	@Test
	public void testSeCantaTrucoReTrucoValeCuatroQueridoDesdeElTrucoCantado(){
		
		partido.nuevaRonda();

		partido.getRondaActual().nuevaVuelta();
		
		partido.getRondaActual().seCantoTruco();
		
		System.out.println("");

		partido.getRondaActual().agregarPuntajeTruco();
		
		Assert.assertEquals(4, this.partido.getRondaActual().getVueltaActual().getAccionTruco().cantar());
	}
}
