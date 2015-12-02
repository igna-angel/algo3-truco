package com.modelo.acciones.flor;

public class FlorQuiero extends FlorDecorator{
	
	public FlorQuiero(AccionFlor accionDecorar) {
		super(accionDecorar);
	}

	public int cantar(){
		return this.accionADecorar.cantar();
	}
}
