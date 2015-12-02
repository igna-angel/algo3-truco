package com.modelo.cartas;

import com.exceptions.PardaInvalidaException;

public class CartaPardaAnchoFalso extends Carta{

	@Override
	public Carta ganador(IPeleable peleable) {
		return peleable.ganador(this);
	}

	@Override
	public Carta ganador(CartaInvalida invalida) {
		return this;
	}

	@Override
	public Carta ganador(CartaNormal normal) {
		return this;
	}

	@Override
	public Carta ganador(CartaAnchoFalso anchoFalso) {
		return this;
	}

	@Override
	public Carta ganador(CartaDos dos) {
		return dos;
	}

	@Override
	public Carta ganador(CartaTres tres) {
		return tres;
	}

	@Override
	public Carta ganador(CartaSieteOro sieteOro) {
		return sieteOro;
	}

	@Override
	public Carta ganador(CartaSieteEspada sieteEspada) {
		return sieteEspada;
	}

	@Override
	public Carta ganador(CartaAnchoBasto anchoBasto) {
		return anchoBasto;
	}

	@Override
	public Carta ganador(CartaAnchoEspada anchoEspada) {
		return anchoEspada;
	}

	@Override
	public Carta ganador(CartaPardaNormal pardaNormal) {
		throw new PardaInvalidaException();
	}

	@Override
	public Carta ganador(CartaPardaAnchoFalso pardaFalso) {
		throw new PardaInvalidaException();
	}

	@Override
	public Carta ganador(CartaPardaDos pardaDos) {
		throw new PardaInvalidaException();
	}

	@Override
	public Carta ganador(CartaPardaTres pardaTres) {
		throw new PardaInvalidaException();
	}
	
	@Override
	public int getPuntosEnvido() {
		return this.getNumero();
	}
}
