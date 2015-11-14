package com.modelo.cartas;

import com.modelo.Carta;
import com.modelo.Espada;
import com.modelo.Oro;
import com.modelo.Palo;

public class CartaOnce extends Carta{
	public static final int VALOR_REPRESENTATIVO = 11;
	
	public CartaOnce(Palo palo){
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
		return this;
	}

	@Override
	public Carta vs(CartaCinco cartaCinco) {
		return this;
	}

	@Override
	public Carta vs(CartaSeis cartaSeis) {
		return this;
	}

	@Override
	public Carta vs(CartaSiete cartaSiete) {
		if (cartaSiete.getPalo() instanceof Oro || cartaSiete.getPalo() instanceof Espada){
			return cartaSiete;
		} else {
			return this;
		}
	}
	
	@Override
	public Carta vs(CartaDiez cartaDiez) {
		return this;
	}

	@Override
	public Carta vs(CartaOnce cartaOnce) {
		return new Parda();
	}

	@Override
	public Carta vs(CartaDoce cartaDoce) {
		return cartaDoce;
	}
}
