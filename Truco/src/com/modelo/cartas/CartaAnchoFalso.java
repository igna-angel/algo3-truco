package com.modelo.cartas;

import com.exceptions.PaloInvalidoException;

public class CartaAnchoFalso extends Carta{
	
	public CartaAnchoFalso(Palo palo) {
		super(palo, 1);
		
		if(palo == Palo.Basto || palo == Palo.Espada) throw new PaloInvalidoException();
	}	
		
	@Override
	public Carta ganador(IPeleable peleable) {
		return peleable.ganador(this);
	}	
	
	@Override
	public Carta ganador(CartaInvalida invalida) {
		return this;
	}
	
	@Override
	public Carta ganador(CartaNormal normal) {
		return this;
	}

	@Override
	public Carta ganador(CartaAnchoFalso anchoFalso) {
		return new CartaPardaAnchoFalso();
	}

	@Override
	public Carta ganador(CartaDos dos) {
		return dos;
	}

	@Override
	public Carta ganador(CartaTres tres) {
		return tres;
	}

	@Override
	public Carta ganador(CartaSieteOro sieteOro) {
		return sieteOro;
	}

	@Override
	public Carta ganador(CartaSieteEspada sieteEspada) {
		return sieteEspada;
	}

	@Override
	public Carta ganador(CartaAnchoBasto anchoBasto) {
		return anchoBasto;
	}

	@Override
	public Carta ganador(CartaAnchoEspada anchoEspada) {
		return anchoEspada;
	}

	@Override
	public Carta ganador(CartaPardaNormal pardaNormal) {
		return this;
	}

	@Override
	public Carta ganador(CartaPardaAnchoFalso pardaFalso) {
		return pardaFalso;
	}

	@Override
	public Carta ganador(CartaPardaDos pardaDos) {
		return pardaDos;
	}

	@Override
	public Carta ganador(CartaPardaTres pardaTres) {
		return pardaTres;
	}
}
