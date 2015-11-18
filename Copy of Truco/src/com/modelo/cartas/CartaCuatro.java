package com.modelo.cartas;

import com.modelo.Carta;

public class CartaCuatro extends Carta{
	public static final int VALOR_REPRESENTATIVO = 4;
	
	public CartaCuatro(Palo palo){
		this._palo = palo;
		this._numero = VALOR_REPRESENTATIVO;
	}
	
	public Carta vs(Carta carta) {
		return carta.vs(this);
	}

	public Carta vs(CartaUno cartaUno) {
		return cartaUno;
	}
	
	public Carta vs(CartaDos cartaDos) {
		return cartaDos;
	}

	@Override
	public Carta vs(CartaTres cartaTres) {
		return cartaTres;
	}

	@Override
	public Carta vs(CartaCuatro cartaCuatro) {
		return new Parda(cartaCuatro);
	}

	@Override
	public Carta vs(CartaCinco cartaCinco) {
		return cartaCinco;
	}

	@Override
	public Carta vs(CartaSeis cartaSeis) {
		return cartaSeis;
	}

	@Override
	public Carta vs(CartaSiete cartaSiete) {
		return cartaSiete;
	}
	
	@Override
	public Carta vs(CartaDiez cartaDiez) {
		return cartaDiez;
	}

	@Override
	public Carta vs(CartaOnce cartaOnce) {
		return cartaOnce;
	}

	@Override
	public Carta vs(CartaDoce cartaDoce) {
		return cartaDoce;
	}
	
	public Carta vs(Parda parda){
		return parda;
	}
}
