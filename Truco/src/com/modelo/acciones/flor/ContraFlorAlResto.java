package com.modelo.acciones.flor;

public class ContraFlorAlResto extends FlorDecorator{

	public ContraFlorAlResto(AccionFlor accionDecorar) {
		super(accionDecorar);
	}

	public int cantar(){
		return this.accionADecorar.cantar() + 2;
	}
}
