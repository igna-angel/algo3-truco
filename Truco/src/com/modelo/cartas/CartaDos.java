package com.modelo.cartas;

public class CartaDos extends Carta {
	
	public CartaDos(Palo palo) {
		super(palo, 2);
	}

	@Override
	public boolean ganaA(IPeleable peleable) {
		return !peleable.ganaA(this);
	}	
	
	@Override
	public boolean ganaA(CartaNormal normal) {
		return true;
	}

	@Override
	public boolean ganaA(CartaAnchoFalso anchoFalso) {
		return true;
	}

	@Override
	public boolean ganaA(CartaDos dos) {
		return true;
	}

	@Override
	public boolean ganaA(CartaTres tres) {
		return false;
	}

	@Override
	public boolean ganaA(CartaSieteOro sieteOro) {
		return false;
	}

	@Override
	public boolean ganaA(CartaSieteEspada sieteOro) {
		return false;
	}

	@Override
	public boolean ganaA(CartaAnchoBasto anchoBasto) {
		return false;
	}

	@Override
	public boolean ganaA(CartaAnchoEspada anchoEspada) {
		return false;
	}	
}
