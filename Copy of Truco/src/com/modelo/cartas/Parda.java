package com.modelo.cartas;

import com.modelo.Carta;

public class Parda extends Carta{
	
	private Carta cartaConParda = null;
	
	public  Parda(Carta cartaParda){
		this.cartaConParda = cartaParda;
	}
	
	public Carta getCartaParda(){
		return cartaConParda;
	}

	@Override
	public Carta vs(Carta carta) {
		return carta.vs(this);
	}

	@Override
	public Carta vs(CartaUno cartaUno) {
		return cartaUno.vs(this);
	}

	@Override
	public Carta vs(CartaDos cartaDos) {
		return cartaDos.vs(this);
	}

	@Override
	public Carta vs(CartaTres cartaTres) {
		return cartaTres.vs(this);
	}

	@Override
	public Carta vs(CartaCuatro cartaCuatro) {
		return cartaCuatro.vs(this);
	}

	@Override
	public Carta vs(CartaCinco cartaCinco) {
		return cartaCinco.vs(this);
	}

	@Override
	public Carta vs(CartaSeis cartaSeis) {
		return cartaSeis.vs(this);
	}

	@Override
	public Carta vs(CartaSiete cartaSiete) {
		return cartaSiete.vs(this);
	}

	@Override
	public Carta vs(CartaDiez cartaDiez) {
		return cartaDiez.vs(this);
	}

	@Override
	public Carta vs(CartaOnce cartaOnce) {
		return cartaOnce.vs(this);
	}

	@Override
	public Carta vs(CartaDoce cartaDoce) {
		return cartaDoce.vs(this);
	}

	@Override
	public Carta vs(Parda parda) {
		return this;
	}
	
}
