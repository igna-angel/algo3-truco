package com.modelo.acciones.envido;

import com.modelo.Jugador;

public class ContraFlor extends TantoDecorator{

	public ContraFlor(AccionTanto accionDecorar, Jugador origen, Jugador destino){
		super(accionDecorar,origen,destino);
	}

	public int cantar(){
		return this.accionADecorar.cantar() + 3;
	}
}
