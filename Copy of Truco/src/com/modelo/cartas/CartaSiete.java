package com.modelo.cartas;

import com.modelo.Basto;
import com.modelo.Carta;
import com.modelo.Copa;
import com.modelo.Espada;
import com.modelo.Oro;
import com.modelo.Palo;

public class CartaSiete extends Carta{
	public static final int VALOR_REPRESENTATIVO = 7;
	
	public CartaSiete(Palo palo){
		this._palo = palo;
		this._numero = VALOR_REPRESENTATIVO;
	}
	
	public Carta vs(Carta carta) {
		return carta.vs(this);
	}

	public Carta vs(CartaUno cartaUno) {
		if (cartaUno.getPalo() instanceof Espada || cartaUno.getPalo() instanceof Basto){
			return cartaUno;
		} else if ((this._palo instanceof Espada || this._palo instanceof Oro) 
			   && ((!(cartaUno.getPalo() instanceof Espada)) || (!(cartaUno.getPalo() instanceof Basto)))){
			return this;
		} else {
			return cartaUno;
		}
	}
	
	private Carta cartaContraGenerica(Carta carta){
		if ((this._palo instanceof Basto) ||(this._palo instanceof Copa)){
			return carta;
		} else {
			return this;
		}
	}

	public Carta vs(CartaDos cartaDos) {
		return this.cartaContraGenerica(cartaDos);
	}

	@Override
	public Carta vs(CartaTres cartaTres) {
		return this.cartaContraGenerica(cartaTres);
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
		return this.cartaContraSieteCasoEspecial(cartaSiete);
	}

	private Carta cartaContraSieteCasoEspecial(CartaSiete cartaSiete) {
		if ((this._palo instanceof Basto && cartaSiete.getPalo() instanceof Copa)
		||  (this._palo instanceof Copa && cartaSiete.getPalo() instanceof Basto)){
			return new Parda();
		} else if (this._palo instanceof Espada || cartaSiete.getPalo() instanceof Oro){
			return this;
		} else if (this._palo instanceof Oro || (!(cartaSiete.getPalo() instanceof Espada))){
			return this;
		} else {
			return cartaSiete;
		}
	}

	@Override
	public Carta vs(CartaDiez cartaDiez) {
		return this.cartaContraGenerica(cartaDiez);
	}

	@Override
	public Carta vs(CartaOnce cartaOnce) {
		return this.cartaContraGenerica(cartaOnce);
	}

	@Override
	public Carta vs(CartaDoce cartaDoce) {
		return this.cartaContraGenerica(cartaDoce);
	}
}
