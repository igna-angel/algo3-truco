package com.modelo.acciones.envido;

import com.modelo.Jugador;

public class RealEnvido extends TantoDecorator {

	public RealEnvido(AccionTanto accionDecorar, Jugador origen, Jugador destino) {
		super(accionDecorar, origen, destino);
	}

	public int cantar(){
		return super.cantar() + 3;
	}
}
