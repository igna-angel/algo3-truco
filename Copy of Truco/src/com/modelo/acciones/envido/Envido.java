package com.modelo.acciones.envido;

public class Envido extends EnvidoDecorator {

	public Envido(AccionEnvido accionDecorar) {
		super(accionDecorar);
	}
	
	public int cantar(){
		return super.cantar() + 1;
	}

}
