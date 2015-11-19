package com.modelo.cartas;

public class CartaAnchoEspada extends Carta{

	public CartaAnchoEspada() {
		super(Palo.Espada, 1);
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
		return this;
	}

	@Override
	public Carta ganador(CartaDos dos) {
		return this;
	}

	@Override
	public Carta ganador(CartaTres tres) {
		return this;
	}

	@Override
	public Carta ganador(CartaSieteOro sieteOro) {
		return this;
	}

	@Override
	public Carta ganador(CartaSieteEspada sieteEspada) {
		return this;
	}

	@Override
	public Carta ganador(CartaAnchoBasto anchoBasto) {
		return this;
	}

	@Override
	public Carta ganador(CartaAnchoEspada anchoEspada) {
		return anchoEspada;
	}
}
