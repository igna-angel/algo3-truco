package com.modelo.acciones.envido;

public class RealEnvido extends EnvidoDecorator {

	public RealEnvido(AccionEnvido accionDecorar) {
		super(accionDecorar);
	}

	public int cantar(){
		return super.cantar() + 3;
	}
}
