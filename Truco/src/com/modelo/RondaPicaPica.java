package com.modelo;

public class RondaPicaPica extends Ronda {

	public RondaPicaPica(Partido partido, Jugador reparte) {
		super(partido, reparte);
	}

	@Override
	public Ronda getRondaSiguiente(boolean esPicaPica) {
		return new RondaRedonda(this.getPartido(), this.getProximoEnRepartir());
	}

}
