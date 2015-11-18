package com.modelo.cartas;

public class CartaNormal extends Carta{

	public CartaNormal() {
		super();
	}	
	
	@Override
	public boolean ganaA(IPeleable peleable) {
		return !peleable.ganaA(this);
	}	
	
	public CartaNormal(Palo palo, int numero) {
		super(palo, numero);
	}	

	@Override
	public boolean ganaA(CartaNormal normal) {
		return (normal.getNumero() <= this.getNumero());
	}

	@Override
	public boolean ganaA(CartaAnchoFalso anchoFalso) {
		return false;
	}

	@Override
	public boolean ganaA(CartaDos dos) {
		return false;
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
