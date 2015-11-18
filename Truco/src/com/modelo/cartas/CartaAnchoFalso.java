package com.modelo.cartas;

import com.exceptions.PaloInvalidoException;

public class CartaAnchoFalso extends Carta{
	
	public CartaAnchoFalso(Palo palo) {
		super(palo, 1);
		
		if(palo == Palo.Basto || palo == Palo.Espada) throw new PaloInvalidoException();
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
