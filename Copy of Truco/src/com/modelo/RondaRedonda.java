package com.modelo;

public class RondaRedonda extends Ronda {

	public RondaRedonda(Partido partido, Jugador reparte) {
		super(partido, reparte);
	}

	@Override
	public Ronda getRondaSiguiente(boolean esPicaPica) {		
		if(esPicaPica) return new RondaPicaPica(this.getPartido(), this.getProximoEnRepartir());
		else return new RondaRedonda(this.getPartido(), this.getProximoEnRepartir());
	}

}
