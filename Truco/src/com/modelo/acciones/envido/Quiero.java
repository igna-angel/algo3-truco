package com.modelo.acciones.envido;

public class Quiero extends EnvidoDecorator {

	public Quiero(AccionEnvido accionDecorar) {
		super(accionDecorar);
	}
	
	public int cantar(){
		return this.accionADecorar.cantar() + 1;
	}

}
