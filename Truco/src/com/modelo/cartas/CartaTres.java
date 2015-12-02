package com.modelo.cartas;

public class CartaTres extends Carta{

	public CartaTres(Palo palo) {
		super(palo, 3);
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
		return new CartaPardaTres();
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
		return this;
	}

	@Override
	public Carta ganador(CartaPardaDos pardaDos) {
		return this;
	}

	@Override
	public Carta ganador(CartaPardaTres pardaTres) {
		return pardaTres;
	}

	@Override
	public int getPuntosEnvido() {
		return this.getNumero();
	}
}
