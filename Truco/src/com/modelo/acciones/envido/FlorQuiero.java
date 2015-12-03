package com.modelo.acciones.envido;

import com.modelo.Jugador;

public class FlorQuiero extends TantoDecorator{
	
	public FlorQuiero(AccionTanto accionDecorar, Jugador origen, Jugador destino){
		super(accionDecorar,origen,destino);
	}

	public int cantar(){
		return this.accionADecorar.cantar();
	}
}
