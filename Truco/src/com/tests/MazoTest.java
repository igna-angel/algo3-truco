package com.tests;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.modelo.Carta;
import com.modelo.Mazo;
import com.modelo.Carta.Palo;

public class MazoTest {

	private Mazo _mazo;
	private int _indice;
	private int _palo;
	
	@Before
	public void setup(){
		this._mazo = new Mazo();
	}
	
	@Test
	public void testSeCreaMazoExitosamente(){
		this._mazo.crear();
		
		List<Carta> cartas = this._mazo.getCartas();
		
		final int cartasPorPalo = Mazo.CARTAS_EN_MAZO / Mazo.CANTIDAD_DE_PALOS + 2; // +2 por el salto de 8 y 9
		this._indice = 1;
		this._palo = Palo.Indefinido.getValorPalo();
		this._palo++;
		
		cartas.forEach(carta ->
			{
				if(this._indice > cartasPorPalo){
					this._palo++;
					this._indice = 1;
				}
				
				if(this._indice == 8) this._indice = 10;
				
				Assert.assertEquals(this._indice, carta.getNumero());
				Assert.assertEquals(Palo.getTipoPalo(this._palo), carta.getPalo());
				
				this._indice++;
			}
		);
	}
	
}
