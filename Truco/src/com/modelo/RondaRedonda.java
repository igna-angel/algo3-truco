package com.modelo;

public class RondaRedonda extends Ronda {

	public RondaRedonda(Equipo equipoA, Equipo equipoB) {
		super(equipoA, equipoB);
	}

	@Override
	public Ronda getRondaSiguiente(boolean esPicaPica) {
		if(esPicaPica) return new RondaPicaPica(this._equipoA, this._equipoB);
		else return new RondaRedonda(this._equipoA, this._equipoB);
	}

}
