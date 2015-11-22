package com.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.modelo.CircularList;
import com.modelo.Jugador;
import com.modelo.JugadorHumano;
import com.modelo.Mazo;
import com.modelo.cartas.Carta;

public class MazoTest {
	private Mazo _mazo;
	
	@Before
	public void setup(){
		this._mazo = new Mazo();
	}
	
	@Test
	public void testSeCreaMazoExitosamente(){
		this._mazo.crear();
		
		Assert.assertEquals(Mazo.CARTAS_EN_MAZO, this._mazo.getCartas().size());
	}
	
	@Test
	public void testSeCreaMazoYSePideUnaCarta(){
		this._mazo.crear();
		
		Carta carta = this._mazo.getCarta(0);
		
		Assert.assertEquals(Carta.Palo.getTipoPalo(1), carta.getPalo());
		Assert.assertEquals(4, carta.getNumero());
	}
	
	
	@Test
	public void testSeRepartenCartas(){
		this._mazo.crear();
		
		CircularList<Jugador> jugadores = new CircularList<Jugador>();
		jugadores.add(new JugadorHumano());
		jugadores.add(new JugadorHumano());
		jugadores.add(new JugadorHumano());
		
		this._mazo.mezclar();
		this._mazo.repartir(jugadores, jugadores.getFirst(), 3);
		
		jugadores.resetToFirst();
		
		do{
			Assert.assertEquals(3, jugadores.getCurrent().getCantidadCartas());
			jugadores.advanceCursor();
		}while(!jugadores.isCurrentFirst());
	}
}
