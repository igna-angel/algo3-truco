package com.modelo.acciones.envido;

public class NoQuiero extends EnvidoDecorator {

	public NoQuiero(AccionEnvido accionDecorar) {
		super(accionDecorar);
	}
	
	public String cantar(){
		return this.accionADecorar.cantar() + "NoQuiero";
	}

}
