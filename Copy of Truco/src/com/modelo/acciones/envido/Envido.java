package com.modelo.acciones.envido;

public class Envido extends EnvidoDecorator {

	public Envido(AccionEnvido accionDecorar) {
		super(accionDecorar);
	}
	
	public String cantar(){
		return super.cantar() + "Envido";
	}
}
