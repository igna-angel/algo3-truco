package com.modelo.acciones.envido;

import com.modelo.Jugador;

public class Flor extends TantoDecorator{
	
	public Flor(AccionTanto accionDecorar, Jugador origen, Jugador destino) {
		super(accionDecorar, origen, destino);
	}

	@Override
	public int cantar() {
		return super.cantar() + 3;
	}

}
