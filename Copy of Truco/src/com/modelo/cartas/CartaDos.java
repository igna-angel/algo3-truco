package com.modelo.cartas;

import com.modelo.Basto;
import com.modelo.Carta;
import com.modelo.Espada;
import com.modelo.Oro;
import com.modelo.Palo;

public class CartaDos extends Carta{
	public static final int VALOR_REPRESENTATIVO = 2;
	
	public CartaDos(Palo palo){
		this._palo = palo;
		this._numero = VALOR_REPRESENTATIVO;
	}
	

	public Carta vs(Carta carta) {
		return carta.vs(this);
	}

	public Carta vs(CartaUno cartaUno) {
		if (cartaUno.getPalo() instanceof Basto || cartaUno.getPalo() instanceof Espada){
			return cartaUno;
		} else {
			return this;
		}
	}
	
	public Carta vs(CartaDos cartaDos) {
		return new Parda(cartaDos);
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
		return this;
	}

	@Override
	public Carta vs(CartaDoce cartaDoce) {
		return this;
	}
	
	public Carta vs(Parda parda){
		 if (parda.getCartaParda() instanceof CartaCuatro
		  || parda.getCartaParda() instanceof CartaCinco
		  || parda.getCartaParda() instanceof CartaSeis
		  || parda.getCartaParda() instanceof CartaSiete
		  || parda.getCartaParda() instanceof CartaDiez
		  || parda.getCartaParda() instanceof CartaOnce
		  || parda.getCartaParda() instanceof CartaDoce
		  || parda.getCartaParda() instanceof CartaUno){
			 return this;
		 } else {
			 return parda;
		 }
	}

}
