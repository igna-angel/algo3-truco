package com.modelo.acciones.envido;

public class RealEnvido extends EnvidoDecorator {

	public RealEnvido(AccionEnvido accionDecorar) {
		super(accionDecorar);
	}

	public String cantar(){
		return this.accionADecorar.cantar() + "RealEnvido";
	}
}
