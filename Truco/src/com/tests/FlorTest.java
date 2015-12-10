package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.exceptions.NoSePuedeNoQuererException;
import com.modelo.JugadorHumano;
import com.modelo.Partido;
import com.modelo.cartas.CartaNormal;
import com.modelo.cartas.Carta.Palo;

import NuevasAcciones.Accion;
import NuevasAcciones.Flor;

public class FlorTest {
	private Flor _flor;
	
	@Before
	public void setup(){
		this._flor = new Flor(null, null);
	}
	
	@Test
	public void testElIDDebeSerFlor(){
		Assert.assertEquals(Accion.ACCION_FLOR, this._flor.getID());
	}
	
	@Test
	public void testSePideUnaNuevaAccionDebeSerContraFlor(){
		Accion nuevaAccion = this._flor.getNuevaAccion(this._flor.getAccionPosible(Accion.ACCION_CONTRA_FLOR), null, null);
		Assert.assertEquals(Accion.ACCION_CONTRA_FLOR, nuevaAccion.getID());
	}
	
	@Test
	public void testSePideUnaNuevaAccionDebeSerContraFlorAlResto(){
		Accion nuevaAccion = this._flor.getNuevaAccion(this._flor.getAccionPosible(Accion.ACCION_CONTRA_FLOR_AL_RESTO), null, null);
		Assert.assertEquals(Accion.ACCION_CONTRA_FLOR_AL_RESTO, nuevaAccion.getID());
	}
	
	@Test (expected = NoSePuedeNoQuererException.class)
	public void testLaCantidadDePuntosNoQueridosDebeTirarExcepcion(){
		this._flor.getPuntosNoQueridos();
	}
	
	@Test
	public void testUnoAUnoLaCantidadDePuntosQueridosDebeSerCero(){
		Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		JugadorHumano jugadorA = new JugadorHumano();
		JugadorHumano jugadorB = new JugadorHumano();
		
		partido.agregarJugadorAEquipo(jugadorA, 0);
		partido.agregarJugadorAEquipo(jugadorB, 1);
		
		jugadorA.recibirCarta(new CartaNormal(Palo.Basto, 4));
		jugadorA.recibirCarta(new CartaNormal(Palo.Copa, 5));
		jugadorA.recibirCarta(new CartaNormal(Palo.Basto, 6));
		
		jugadorB.recibirCarta(new CartaNormal(Palo.Espada, 4));
		jugadorB.recibirCarta(new CartaNormal(Palo.Oro, 5));
		jugadorB.recibirCarta(new CartaNormal(Palo.Copa, 6));
		
		Assert.assertEquals(0, this._flor.getPuntosQueridos(partido));
	}
	
	@Test
	public void testUnoAUnoLaCantidadDePuntosQueridosDebeSerTres(){
		Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		JugadorHumano jugadorA = new JugadorHumano();
		JugadorHumano jugadorB = new JugadorHumano();
		
		partido.agregarJugadorAEquipo(jugadorA, 0);
		partido.agregarJugadorAEquipo(jugadorB, 1);
		
		jugadorA.recibirCarta(new CartaNormal(Palo.Basto, 4));
		jugadorA.recibirCarta(new CartaNormal(Palo.Basto, 5));
		jugadorA.recibirCarta(new CartaNormal(Palo.Basto, 6));
		
		jugadorB.recibirCarta(new CartaNormal(Palo.Espada, 4));
		jugadorB.recibirCarta(new CartaNormal(Palo.Oro, 5));
		jugadorB.recibirCarta(new CartaNormal(Palo.Copa, 6));
		
		Assert.assertEquals(3, this._flor.getPuntosQueridos(partido));
	}
	
	/*@Test
	public void testUnoAUnoLaCantidadDePuntosQueridosDebeSerSeis(){
		Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		JugadorHumano jugadorA = new JugadorHumano();
		JugadorHumano jugadorB = new JugadorHumano();
		
		partido.agregarJugadorAEquipo(jugadorA, 0);
		partido.agregarJugadorAEquipo(jugadorB, 1);
		
		jugadorA.recibirCarta(new CartaNormal(Palo.Basto, 4));
		jugadorA.recibirCarta(new CartaNormal(Palo.Basto, 5));
		jugadorA.recibirCarta(new CartaNormal(Palo.Basto, 6));
		
		jugadorB.recibirCarta(new CartaNormal(Palo.Espada, 4));
		jugadorB.recibirCarta(new CartaNormal(Palo.Espada, 5));
		jugadorB.recibirCarta(new CartaNormal(Palo.Espada, 7));
		
		Assert.assertEquals(6, this._flor.getPuntosQueridos(partido));
	}*/
	
	@Test
	public void testDosADosLaCantidadDePuntosQueridosDebeSerCero(){
		Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		JugadorHumano jugadorAEA = new JugadorHumano();
		JugadorHumano jugadorBEA = new JugadorHumano();
		
		JugadorHumano jugadorAEB = new JugadorHumano();
		JugadorHumano jugadorBEB = new JugadorHumano();
		
		partido.agregarJugadorAEquipo(jugadorAEA, 0);
		partido.agregarJugadorAEquipo(jugadorBEA, 0);
		
		partido.agregarJugadorAEquipo(jugadorAEB, 1);
		partido.agregarJugadorAEquipo(jugadorBEB, 1);
		
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 4));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 5));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Espada, 6));
		
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 4));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 5));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Basto, 6));
		
		
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 4));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 5));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Oro, 6));
		
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 4));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 5));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Copa, 6));
		
		Assert.assertEquals(0, this._flor.getPuntosQueridos(partido));
	}
	
	@Test
	public void testDosADosLaCantidadDePuntosQueridosDebeSerTres(){
		Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		JugadorHumano jugadorAEA = new JugadorHumano();
		JugadorHumano jugadorBEA = new JugadorHumano();
		
		JugadorHumano jugadorAEB = new JugadorHumano();
		JugadorHumano jugadorBEB = new JugadorHumano();
		
		partido.agregarJugadorAEquipo(jugadorAEA, 0);
		partido.agregarJugadorAEquipo(jugadorBEA, 0);
		
		partido.agregarJugadorAEquipo(jugadorAEB, 1);
		partido.agregarJugadorAEquipo(jugadorBEB, 1);
		
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 4));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 5));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 6));
		
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Oro, 4));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Copa, 5));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 6));
		
		
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 4));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Espada, 5));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Oro, 6));
		
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Espada, 4));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 5));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Copa, 6));
		
		Assert.assertEquals(3, this._flor.getPuntosQueridos(partido));
	}
	
	/*@Test
	public void testDosADosLaCantidadDePuntosQueridosDebeSerSeis(){
		Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		JugadorHumano jugadorAEA = new JugadorHumano();
		JugadorHumano jugadorBEA = new JugadorHumano();
		
		JugadorHumano jugadorAEB = new JugadorHumano();
		JugadorHumano jugadorBEB = new JugadorHumano();
		
		partido.agregarJugadorAEquipo(jugadorAEA, 0);
		partido.agregarJugadorAEquipo(jugadorBEA, 0);
		
		partido.agregarJugadorAEquipo(jugadorAEB, 1);
		partido.agregarJugadorAEquipo(jugadorBEB, 1);
		
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 4));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 5));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 6));
		
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 4));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 5));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 6));
		
		
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 4));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 5));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Oro, 6));
		
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 4));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 5));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Copa, 6));
		
		Assert.assertEquals(6, this._flor.getPuntosQueridos(partido));
	}
	
	@Test
	public void testDosADosLaCantidadDePuntosQueridosDebeSerNueve(){
		Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		JugadorHumano jugadorAEA = new JugadorHumano();
		JugadorHumano jugadorBEA = new JugadorHumano();
		
		JugadorHumano jugadorAEB = new JugadorHumano();
		JugadorHumano jugadorBEB = new JugadorHumano();
		
		partido.agregarJugadorAEquipo(jugadorAEA, 0);
		partido.agregarJugadorAEquipo(jugadorBEA, 0);
		
		partido.agregarJugadorAEquipo(jugadorAEB, 1);
		partido.agregarJugadorAEquipo(jugadorBEB, 1);
		
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 4));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 5));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 6));
		
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 4));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 5));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 6));
		
		
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 4));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 5));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 6));
		
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 4));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 5));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Copa, 7));
		
		Assert.assertEquals(9, this._flor.getPuntosQueridos(partido));
	}
	
	@Test
	public void testDosADosLaCantidadDePuntosQueridosDebeSerDoce(){
		Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		JugadorHumano jugadorAEA = new JugadorHumano();
		JugadorHumano jugadorBEA = new JugadorHumano();
		
		JugadorHumano jugadorAEB = new JugadorHumano();
		JugadorHumano jugadorBEB = new JugadorHumano();
		
		partido.agregarJugadorAEquipo(jugadorAEA, 0);
		partido.agregarJugadorAEquipo(jugadorBEA, 0);
		
		partido.agregarJugadorAEquipo(jugadorAEB, 1);
		partido.agregarJugadorAEquipo(jugadorBEB, 1);
		
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 4));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 5));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 6));
		
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 4));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 5));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 6));
		
		
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 4));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 5));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 6));
		
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 4));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 5));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 7));
		
		Assert.assertEquals(12, this._flor.getPuntosQueridos(partido));
	}*/
	
	@Test
	public void testTresATresLaCantidadDePuntosQueridosDebeSerCero(){
		Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		JugadorHumano jugadorAEA = new JugadorHumano();
		JugadorHumano jugadorBEA = new JugadorHumano();
		JugadorHumano jugadorCEA = new JugadorHumano();
		
		JugadorHumano jugadorAEB = new JugadorHumano();
		JugadorHumano jugadorBEB = new JugadorHumano();
		JugadorHumano jugadorCEB = new JugadorHumano();
		
		partido.agregarJugadorAEquipo(jugadorAEA, 0);
		partido.agregarJugadorAEquipo(jugadorBEA, 0);
		partido.agregarJugadorAEquipo(jugadorCEA, 0);
		
		partido.agregarJugadorAEquipo(jugadorAEB, 1);
		partido.agregarJugadorAEquipo(jugadorBEB, 1);
		partido.agregarJugadorAEquipo(jugadorCEB, 1);
		
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 4));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 5));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Espada, 7));
		
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 4));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Copa, 5));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Oro, 6));
		
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Espada, 10));
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Copa, 11));
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Oro, 12));
		
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 4));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Espada, 5));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Oro, 6));
		
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 4));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 5));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Copa, 7));
		
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Oro, 10));
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Oro, 11));
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Espada, 12));
		
		
		Assert.assertEquals(0, this._flor.getPuntosQueridos(partido));
	}
	
	@Test
	public void testTresATresLaCantidadDePuntosQueridosDebeSerTres(){
		Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		JugadorHumano jugadorAEA = new JugadorHumano();
		JugadorHumano jugadorBEA = new JugadorHumano();
		JugadorHumano jugadorCEA = new JugadorHumano();
		
		JugadorHumano jugadorAEB = new JugadorHumano();
		JugadorHumano jugadorBEB = new JugadorHumano();
		JugadorHumano jugadorCEB = new JugadorHumano();
		
		partido.agregarJugadorAEquipo(jugadorAEA, 0);
		partido.agregarJugadorAEquipo(jugadorBEA, 0);
		partido.agregarJugadorAEquipo(jugadorCEA, 0);
		
		partido.agregarJugadorAEquipo(jugadorAEB, 1);
		partido.agregarJugadorAEquipo(jugadorBEB, 1);
		partido.agregarJugadorAEquipo(jugadorCEB, 1);
		
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 4));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 5));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 6));
		
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 4));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Copa, 5));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Oro, 6));
		
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Espada, 10));
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Copa, 11));
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Oro, 12));
		
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 4));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Espada, 5));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Oro, 6));
		
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 4));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 5));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Copa, 7));
		
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Oro, 10));
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Oro, 11));
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Espada, 12));
		
		
		Assert.assertEquals(3, this._flor.getPuntosQueridos(partido));
	}

	/*@Test
	public void testTresATresLaCantidadDePuntosQueridosDebeSerSeis(){
		Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		JugadorHumano jugadorAEA = new JugadorHumano();
		JugadorHumano jugadorBEA = new JugadorHumano();
		JugadorHumano jugadorCEA = new JugadorHumano();
		
		JugadorHumano jugadorAEB = new JugadorHumano();
		JugadorHumano jugadorBEB = new JugadorHumano();
		JugadorHumano jugadorCEB = new JugadorHumano();
		
		partido.agregarJugadorAEquipo(jugadorAEA, 0);
		partido.agregarJugadorAEquipo(jugadorBEA, 0);
		partido.agregarJugadorAEquipo(jugadorCEA, 0);
		
		partido.agregarJugadorAEquipo(jugadorAEB, 1);
		partido.agregarJugadorAEquipo(jugadorBEB, 1);
		partido.agregarJugadorAEquipo(jugadorCEB, 1);
		
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 4));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 5));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 6));
		
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 4));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 5));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 6));
		
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Espada, 10));
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Copa, 11));
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Oro, 12));
		
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 4));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 5));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Oro, 6));
		
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 4));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 5));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Copa, 7));
		
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Oro, 10));
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Oro, 11));
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Espada, 12));
		
		
		Assert.assertEquals(6, this._flor.getPuntosQueridos(partido));
	}
	
	@Test
	public void testTresATresLaCantidadDePuntosQueridosDebeSerNueve(){
		Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		JugadorHumano jugadorAEA = new JugadorHumano();
		JugadorHumano jugadorBEA = new JugadorHumano();
		JugadorHumano jugadorCEA = new JugadorHumano();
		
		JugadorHumano jugadorAEB = new JugadorHumano();
		JugadorHumano jugadorBEB = new JugadorHumano();
		JugadorHumano jugadorCEB = new JugadorHumano();
		
		partido.agregarJugadorAEquipo(jugadorAEA, 0);
		partido.agregarJugadorAEquipo(jugadorBEA, 0);
		partido.agregarJugadorAEquipo(jugadorCEA, 0);
		
		partido.agregarJugadorAEquipo(jugadorAEB, 1);
		partido.agregarJugadorAEquipo(jugadorBEB, 1);
		partido.agregarJugadorAEquipo(jugadorCEB, 1);
		
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 4));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 5));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 6));
		
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 4));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 5));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 6));
		
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Copa, 10));
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Copa, 11));
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Copa, 12));
		
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 4));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 5));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Oro, 6));
		
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 4));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 5));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Copa, 7));
		
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Oro, 10));
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Oro, 11));
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Espada, 12));
		
		
		Assert.assertEquals(9, this._flor.getPuntosQueridos(partido));
	}

	@Test
	public void testTresATresLaCantidadDePuntosQueridosDebeSerDoce(){
		Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		JugadorHumano jugadorAEA = new JugadorHumano();
		JugadorHumano jugadorBEA = new JugadorHumano();
		JugadorHumano jugadorCEA = new JugadorHumano();
		
		JugadorHumano jugadorAEB = new JugadorHumano();
		JugadorHumano jugadorBEB = new JugadorHumano();
		JugadorHumano jugadorCEB = new JugadorHumano();
		
		partido.agregarJugadorAEquipo(jugadorAEA, 0);
		partido.agregarJugadorAEquipo(jugadorBEA, 0);
		partido.agregarJugadorAEquipo(jugadorCEA, 0);
		
		partido.agregarJugadorAEquipo(jugadorAEB, 1);
		partido.agregarJugadorAEquipo(jugadorBEB, 1);
		partido.agregarJugadorAEquipo(jugadorCEB, 1);
		
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 4));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 5));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 6));
		
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 4));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 5));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 6));
		
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Copa, 10));
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Copa, 11));
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Copa, 12));
		
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 4));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 5));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 6));
		
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 4));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 5));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Copa, 7));
		
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Oro, 10));
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Oro, 11));
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Espada, 12));
		
		
		Assert.assertEquals(12, this._flor.getPuntosQueridos(partido));
	}

	@Test
	public void testTresATresLaCantidadDePuntosQueridosDebeSerQuince(){
		Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		JugadorHumano jugadorAEA = new JugadorHumano();
		JugadorHumano jugadorBEA = new JugadorHumano();
		JugadorHumano jugadorCEA = new JugadorHumano();
		
		JugadorHumano jugadorAEB = new JugadorHumano();
		JugadorHumano jugadorBEB = new JugadorHumano();
		JugadorHumano jugadorCEB = new JugadorHumano();
		
		partido.agregarJugadorAEquipo(jugadorAEA, 0);
		partido.agregarJugadorAEquipo(jugadorBEA, 0);
		partido.agregarJugadorAEquipo(jugadorCEA, 0);
		
		partido.agregarJugadorAEquipo(jugadorAEB, 1);
		partido.agregarJugadorAEquipo(jugadorBEB, 1);
		partido.agregarJugadorAEquipo(jugadorCEB, 1);
		
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 4));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 5));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 6));
		
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 4));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 5));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 6));
		
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Copa, 10));
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Copa, 11));
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Copa, 12));
		
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 4));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 5));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 6));
		
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 4));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 5));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 7));
		
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Oro, 10));
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Oro, 11));
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Espada, 12));
		
		
		Assert.assertEquals(15, this._flor.getPuntosQueridos(partido));
	}

	@Test
	public void testTresATresLaCantidadDePuntosQueridosDebeSerDieciocho(){
		Partido partido = new Partido();
		
		partido.agregarEquipo();
		partido.agregarEquipo();
		
		JugadorHumano jugadorAEA = new JugadorHumano();
		JugadorHumano jugadorBEA = new JugadorHumano();
		JugadorHumano jugadorCEA = new JugadorHumano();
		
		JugadorHumano jugadorAEB = new JugadorHumano();
		JugadorHumano jugadorBEB = new JugadorHumano();
		JugadorHumano jugadorCEB = new JugadorHumano();
		
		partido.agregarJugadorAEquipo(jugadorAEA, 0);
		partido.agregarJugadorAEquipo(jugadorBEA, 0);
		partido.agregarJugadorAEquipo(jugadorCEA, 0);
		
		partido.agregarJugadorAEquipo(jugadorAEB, 1);
		partido.agregarJugadorAEquipo(jugadorBEB, 1);
		partido.agregarJugadorAEquipo(jugadorCEB, 1);
		
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 4));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 5));
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 6));
		
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 4));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 5));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 6));
		
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Copa, 10));
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Copa, 11));
		jugadorCEA.recibirCarta(new CartaNormal(Palo.Copa, 12));
		
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 4));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 5));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 6));
		
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 4));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 5));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 7));
		
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Oro, 10));
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Oro, 11));
		jugadorCEB.recibirCarta(new CartaNormal(Palo.Oro, 12));
		
		
		Assert.assertEquals(18, this._flor.getPuntosQueridos(partido));
	}*/
}
