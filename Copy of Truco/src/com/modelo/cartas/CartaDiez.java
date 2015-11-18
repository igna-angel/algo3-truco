package com.modelo.cartas;

import com.modelo.Carta;
import com.modelo.Espada;
import com.modelo.Oro;
import com.modelo.Palo;

public class CartaDiez extends Carta{
	public static final int VALOR_REPRESENTATIVO = 10;
	
	public CartaDiez(Palo palo){
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
		return new Parda(cartaDiez);
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
		 if (parda.getCartaParda() instanceof CartaCuatro
		  || parda.getCartaParda() instanceof CartaCinco
		  || parda.getCartaParda() instanceof CartaSeis
		  || parda.getCartaParda() instanceof CartaSiete){
			 return this;
		 } else {
			 return parda;
		 }
	}
}
