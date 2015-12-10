package com.modelo.cartas;

public class CartaInvalida extends Carta {

	@Override
	public Carta ganador(IPeleable peleable) {
		return peleable.ganador(this);
	}

	@Override
	public Carta ganador(CartaInvalida invalida) {
		return invalida;
	}
	
	@Override
	public Carta ganador(CartaNormal normal) {
		return normal;
	}

	@Override
	public Carta ganador(CartaAnchoFalso anchoFalso) {
		return anchoFalso;
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
	public int getPuntosEnvido() {
		return Carta.PUNTOS_CARTA_SIN_PUNTOS;
	}
}
