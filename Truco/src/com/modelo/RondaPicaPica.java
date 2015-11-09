package com.modelo;

public class RondaPicaPica extends Ronda {

	public RondaPicaPica(Equipo equipoA, Equipo equipoB) {
		super(equipoA, equipoB);
	}

	@Override
	public Ronda getRondaSiguiente(boolean esPicaPica) {
		return new RondaRedonda(this._equipoA, this._equipoB);
	}

}
