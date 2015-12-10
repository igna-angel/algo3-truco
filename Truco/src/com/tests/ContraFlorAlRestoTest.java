package com.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.exceptions.NoHayAccionesException;
import com.modelo.JugadorHumano;
import com.modelo.Partido;
import com.modelo.cartas.CartaNormal;
import com.modelo.cartas.Carta.Palo;

import NuevasAcciones.Accion;
import NuevasAcciones.ContraFlor;
import NuevasAcciones.ContraFlorAlResto;
import NuevasAcciones.Flor;


public class ContraFlorAlRestoTest {
	private Flor _flor;
	private ContraFlor _contraFlor;
	
	private ContraFlorAlResto _florContraFlorAlResto;
	private ContraFlorAlResto _florContraFlorContraFlorAlResto;
	
	@Before
	public void setup(){
		this._flor = new Flor(null, null);
		
		this._contraFlor = (ContraFlor) this._flor.getNuevaAccion(this._flor.getAccionPosible(Accion.ACCION_CONTRA_FLOR), null, null);
		
		this._florContraFlorAlResto =  (ContraFlorAlResto) this._flor.getNuevaAccion(this._flor.getAccionPosible(Accion.ACCION_CONTRA_FLOR_AL_RESTO), null, null);
		this._florContraFlorContraFlorAlResto = (ContraFlorAlResto) this._contraFlor.getNuevaAccion(this._contraFlor.getAccionPosible(Accion.ACCION_CONTRA_FLOR_AL_RESTO), null, null);
	}
	
	@Test
	public void testElIDDebeSerContraFlorAlResto(){
		Assert.assertEquals(Accion.ACCION_CONTRA_FLOR_AL_RESTO, this._florContraFlorAlResto.getID());
		Assert.assertEquals(Accion.ACCION_CONTRA_FLOR_AL_RESTO, this._florContraFlorContraFlorAlResto.getID());
	}
	
	@Test (expected = NoHayAccionesException.class)
	public void testSePideUnaNuevaAccionDebeTirarExcepcion(){
		this._florContraFlorAlResto.getNuevaAccion(this._florContraFlorAlResto.getAccionesPosibles().get(0), null, null);
		this._florContraFlorContraFlorAlResto.getNuevaAccion(this._florContraFlorContraFlorAlResto.getAccionesPosibles().get(0), null, null);
	}
	
	@Test
	public void testLaCantidadDePuntosNoQueridosDebenSerCuatroYOcho(){
		Assert.assertEquals(4, this._florContraFlorAlResto.getPuntosNoQueridos());
		Assert.assertEquals(8, this._florContraFlorContraFlorAlResto.getPuntosNoQueridos());
	}
	
	@Test
	public void testLaCantidadDePuntosQueridosDebenSerLasDiferenciasParaLlegarAQuinceYTreinta(){
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
		jugadorAEA.recibirCarta(new CartaNormal(Palo.Basto, 7));
		
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 4));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Espada, 5));
		jugadorBEA.recibirCarta(new CartaNormal(Palo.Oro, 6));
				
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 4));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 5));
		jugadorAEB.recibirCarta(new CartaNormal(Palo.Copa, 6));
		
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 4));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Oro, 5));
		jugadorBEB.recibirCarta(new CartaNormal(Palo.Espada, 6));
				
		Assert.assertEquals(15, this._florContraFlorAlResto.getPuntosQueridos(partido, partido.getPrimerEquipo()));
		Assert.assertEquals(15, this._florContraFlorAlResto.getPuntosQueridos(partido, partido.getUltimoEquipo()));
		
		partido.agregarPuntos(5, 7);
		
		Assert.assertEquals(10, this._florContraFlorAlResto.getPuntosQueridos(partido, partido.getPrimerEquipo()));
		Assert.assertEquals(8, this._florContraFlorAlResto.getPuntosQueridos(partido, partido.getUltimoEquipo()));
		
		partido.agregarPuntos(11, 0);
		
		Assert.assertEquals(14, this._florContraFlorAlResto.getPuntosQueridos(partido, partido.getPrimerEquipo()));
		Assert.assertEquals(8, this._florContraFlorAlResto.getPuntosQueridos(partido, partido.getUltimoEquipo()));
		
		partido.agregarPuntos(0, 8);
		
		Assert.assertEquals(14, this._florContraFlorAlResto.getPuntosQueridos(partido, partido.getPrimerEquipo()));
		Assert.assertEquals(15, this._florContraFlorAlResto.getPuntosQueridos(partido, partido.getUltimoEquipo()));
		
	}
}
