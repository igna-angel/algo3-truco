package com.modelo.cartas;

public class CartaAnchoBasto extends Carta{

	public CartaAnchoBasto() {
		super(Palo.Basto, 1);
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
		return true;
	}

	@Override
	public boolean ganaA(CartaSieteOro sieteOro) {
		return true;
	}

	@Override
	public boolean ganaA(CartaSieteEspada sieteOro) {
		return true;
	}

	@Override
	public boolean ganaA(CartaAnchoBasto anchoBasto) {
		return true;
	}

	@Override
	public boolean ganaA(CartaAnchoEspada anchoEspada) {
		return false;
	}
}
