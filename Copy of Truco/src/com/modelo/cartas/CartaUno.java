package com.modelo.cartas;

import com.modelo.Basto;
import com.modelo.Carta;
import com.modelo.Copa;
import com.modelo.Espada;
import com.modelo.Oro;
import com.modelo.Palo;

public class CartaUno extends Carta {
	
	public static final int VALOR_REPRESENTATIVO = 1;
	
	public CartaUno(Palo palo){
		this._palo = palo;
		this._numero = VALOR_REPRESENTATIVO;
	}

	public Carta vs(Carta carta) {
		return carta.vs(this);
	}

	public Carta vs(CartaUno cartaUno) {
		return resultadoVsCartaUnoCasoEspecial(cartaUno);
	}
	
	private Carta resultadoVsCartaUnoCasoEspecial(CartaUno cartaUno){
		
		if(this._palo instanceof Espada){
			return this;
		}
		
		if ((this._palo instanceof Basto) && 
			((!(cartaUno.getPalo() instanceof Espada)))){
				return this;
		} else if (((this._palo instanceof Copa) || (this._palo instanceof Oro)) &&
				   ((cartaUno.getPalo() instanceof Basto) || 
				    (cartaUno.getPalo() instanceof Espada))){
			return cartaUno;
		} else if (((this._palo instanceof Copa) && (cartaUno.getPalo() instanceof Oro))
				||((this._palo instanceof Oro) && (cartaUno.getPalo() instanceof Copa))){
			return new Parda(cartaUno);
		} else {
			return cartaUno;
		}
		
	}
	
	private Carta resultadoVsCartaSegunPaloDeUno(Carta carta){
		if ((this._palo instanceof Basto) ||(this._palo instanceof Espada)){
			return this;
		} else {
			return carta;
		}
	}
	
	private Carta cartaContraSiete(CartaSiete cartaSiete){
		if ((this._palo instanceof Basto) ||(this._palo instanceof Espada)){
			return this;
		} else if ((cartaSiete.getPalo() instanceof Basto) || (cartaSiete.getPalo() instanceof Copa)){
			return this;
		} else {
			return cartaSiete;
		}
	}

	public Carta vs(CartaDos cartaDos) {
		return this.resultadoVsCartaSegunPaloDeUno(cartaDos);
	}

	@Override
	public Carta vs(CartaTres cartaTres) {
		return this.resultadoVsCartaSegunPaloDeUno(cartaTres);
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
		return this.cartaContraSiete(cartaSiete);
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
		
		if (this._palo instanceof Basto
		 || this._palo instanceof Espada){
			return this;
		} else 		
		 if (parda.getCartaParda() instanceof CartaCuatro
		  || parda.getCartaParda() instanceof CartaCinco
		  || parda.getCartaParda() instanceof CartaSeis
		  || parda.getCartaParda() instanceof CartaSiete
		  || parda.getCartaParda() instanceof CartaDiez
		  || parda.getCartaParda() instanceof CartaOnce
		  || parda.getCartaParda() instanceof CartaDoce){
			 return this;
		 } else {
			 return parda;
		 }
	}
	
}
