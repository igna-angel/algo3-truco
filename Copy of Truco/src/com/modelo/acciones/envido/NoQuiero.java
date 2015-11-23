package com.modelo.acciones.envido;

public class NoQuiero extends EnvidoDecorator {

	public NoQuiero(AccionEnvido accionDecorar) {
		super(accionDecorar);
	}
	
	public int cantar(){
		return this.accionADecorar.cantar();
	}

}
