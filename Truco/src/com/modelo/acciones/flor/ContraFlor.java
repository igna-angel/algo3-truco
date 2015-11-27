package com.modelo.acciones.flor;

public class ContraFlor extends FlorDecorator{
	
	public ContraFlor(AccionFlor accionDecorar) {
		super(accionDecorar);
	}

	public int cantar(){
		return this.accionADecorar.cantar() + 3;
	}
}
