package com.modelo.acciones.envido;

import com.modelo.Jugador;

public class ContraFlorAlResto extends TantoDecorator{

	public ContraFlorAlResto(AccionTanto accionDecorar, Jugador origen, Jugador destino){
		super(accionDecorar,origen,destino);
	}
	
	public int cantar(){
		return this.accionADecorar.cantar() + 30;
	}
}
