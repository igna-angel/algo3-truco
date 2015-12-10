package com.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.exceptions.NoHayEquiposException;
import com.modelo.JugadorHumano;
import com.modelo.Mazo;
import com.modelo.Partido;
import com.modelo.RondaPicaPica;
import com.modelo.RondaRedonda;

public class PartidoTest {
	
	private Partido _partido;
	
	@Before
	public void setup(){
		this._partido = new Partido();
	}
	
	@Test (expected = NoHayEquiposException.class)
	public void testSeCreaPartidoVacio(){
		Assert.assertEquals(0, this._partido.getCantidadDeRondas());
		Assert.assertEquals(0, this._partido.getCantidadEquipos());
		Assert.assertEquals(Mazo.CARTAS_EN_MAZO, this._partido.getMazo().getCartas().size());
		this._partido.getCantidadDeJugadoresPorEquipo();
	}
	
	@Test
	public void testSeAgregaUnEquipo(){
		Assert.assertEquals(0, this._partido.getCantidadEquipos());
		this._partido.agregarEquipo();
		Assert.assertEquals(1, this._partido.getCantidadEquipos());
		Assert.assertEquals(0, this._partido.getCantidadDeJugadoresPorEquipo());
	}
	
	@Test
	public void testSeAgregaUnJugadorAEquipo(){
		this._partido.agregarEquipo();
		Assert.assertEquals(0, this._partido.getCantidadJugadoresEnEquipo(0));
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		Assert.assertEquals(1, this._partido.getCantidadJugadoresEnEquipo(0));	
	}
	
	@Test
	public void testSeAgregaUnaRondaDebeSerRedonda(){
		this._partido.agregarEquipo();
		this._partido.agregarEquipo();
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		
		System.out.println(this._partido.getCantidadEquipos());
		this._partido.crearOrdenJugadores();
		this._partido.nuevaRonda();
		Assert.assertEquals(1, this._partido.getCantidadDeRondas());
		Assert.assertTrue(this._partido.getRondaActual() instanceof RondaRedonda);
	}
	
	@Test
	public void testSePideUnaNuevaRondaDebeSerPicaPica(){
		this._partido.agregarEquipo();
		this._partido.agregarEquipo();
		
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		
		this._partido.crearPartido();
		this._partido.nuevaRonda();
		
		this._partido.getRondaActual().asignarPuntos(3, 4);
		this._partido.nuevaRonda();
		Assert.assertTrue(this._partido.getRondaActual() instanceof RondaRedonda);
		
		this._partido.getRondaActual().asignarPuntos(0, 1);
		this._partido.nuevaRonda();
		Assert.assertTrue(this._partido.getRondaActual() instanceof RondaPicaPica);
		
		this._partido.getRondaActual().asignarPuntos(0, 21);
		this._partido.nuevaRonda();
		Assert.assertTrue(this._partido.getRondaActual() instanceof RondaRedonda);
	}
	
	@Test
	public void testSeCreaElOrdenInicialALosJugadores(){
		this._partido.agregarEquipo();
		this._partido.agregarEquipo();
		
		JugadorHumano jugadorA1 = new JugadorHumano();
		JugadorHumano jugadorA2 = new JugadorHumano();
		JugadorHumano jugadorA3 = new JugadorHumano();
		JugadorHumano jugadorB1 = new JugadorHumano();
		JugadorHumano jugadorB2 = new JugadorHumano();
		JugadorHumano jugadorB3 = new JugadorHumano();
		
		this._partido.agregarJugadorAEquipo(jugadorA1, 0);
		this._partido.agregarJugadorAEquipo(jugadorA2, 0);
		this._partido.agregarJugadorAEquipo(jugadorA3, 0);
		this._partido.agregarJugadorAEquipo(jugadorB1, 1);
		this._partido.agregarJugadorAEquipo(jugadorB2, 1);
		this._partido.agregarJugadorAEquipo(jugadorB3, 1);
		
		this._partido.crearOrdenJugadores();
		
		Assert.assertTrue(this._partido.getOrdenJugadores().getAt(0) == jugadorA1);
		Assert.assertTrue(this._partido.getOrdenJugadores().getAt(1) == jugadorB1);
		Assert.assertTrue(this._partido.getOrdenJugadores().getAt(2) == jugadorA2);
		Assert.assertTrue(this._partido.getOrdenJugadores().getAt(3) == jugadorB2);
		Assert.assertTrue(this._partido.getOrdenJugadores().getAt(4) == jugadorA3);
		Assert.assertTrue(this._partido.getOrdenJugadores().getAt(5) == jugadorB3);
	}
	
	@Test
	public void testSeSumanPuntosAPartidoDeCuatroNoDebeSerPicaPica(){
		this._partido.agregarEquipo();
		this._partido.agregarEquipo();
		
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		
		this._partido.crearPartido();
		this._partido.nuevaRonda();
		
		Assert.assertFalse(this._partido.esPicaPica());
		
		this._partido.getRondaActual().asignarPuntos(3, 5);

		Assert.assertFalse(this._partido.esPicaPica());
	}
	
	@Test
	public void testSeSumanPuntosAPartidoDeSeisDebeSerPicaPica(){
		this._partido.agregarEquipo();
		this._partido.agregarEquipo();
		
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		
		this._partido.crearPartido();
		this._partido.nuevaRonda();
		
		Assert.assertFalse(this._partido.esPicaPica());
		
		this._partido.getRondaActual().asignarPuntos(3, 5);

		Assert.assertTrue(this._partido.esPicaPica());
	}
	
	@Test
	public void testElMaximoPuntajeDebeSerDiez(){
		this._partido.agregarEquipo();
		this._partido.agregarEquipo();
		
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		
		this._partido.crearPartido();
		this._partido.nuevaRonda();		
		
		this._partido.getRondaActual().asignarPuntos(2, 5);
		this._partido.getRondaActual().asignarPuntos(8, 1);
		this._partido.getRondaActual().asignarPuntos(0, 1); // puntaje primer Equipo: 10, puntaje segundo equipo: 7
		
		Assert.assertEquals(10, this._partido.getMaximoPuntaje());
	}
	
	@Test
	public void testSeCreaPartido(){
		this._partido.agregarEquipo();
		this._partido.agregarEquipo();
		
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 0);
		
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		this._partido.agregarJugadorAEquipo(new JugadorHumano(), 1);
		
		this._partido.crearPartido();
		this._partido.nuevaRonda();
		this._partido.mezclarYRepartir();
		
		this._partido.getOrdenJugadores().resetToFirst();
		do{
			Assert.assertEquals(3, this._partido.getOrdenJugadores().getCurrent().getCantidadCartas());
			this._partido.getOrdenJugadores().advanceCursor();
		}while(!this._partido.getOrdenJugadores().isCurrentFirst());
		
		Assert.assertTrue(this._partido.getRondaActual() instanceof RondaRedonda);
	}

	public void testElProximoEnRepartirDebeSerPrimeroDelSegundoEquipo(){
		this._partido.agregarEquipo();
		this._partido.agregarEquipo();
		
		JugadorHumano jugadorA1 = new JugadorHumano();
		JugadorHumano jugadorA2 = new JugadorHumano();
		JugadorHumano jugadorA3 = new JugadorHumano();
		JugadorHumano jugadorB1 = new JugadorHumano();
		JugadorHumano jugadorB2 = new JugadorHumano();
		JugadorHumano jugadorB3 = new JugadorHumano();
		
		this._partido.agregarJugadorAEquipo(jugadorA1, 0);
		this._partido.agregarJugadorAEquipo(jugadorA2, 0);
		this._partido.agregarJugadorAEquipo(jugadorA3, 0);
		this._partido.agregarJugadorAEquipo(jugadorB1, 1);
		this._partido.agregarJugadorAEquipo(jugadorB2, 1);
		this._partido.agregarJugadorAEquipo(jugadorB3, 1);
		
		this._partido.crearPartido();
		
		Assert.assertEquals(jugadorA1, this._partido.getRepartidorActual());
		
		this._partido.nuevaRonda();
		
		Assert.assertEquals(jugadorB1, this._partido.getRepartidorActual());	
	}
}
