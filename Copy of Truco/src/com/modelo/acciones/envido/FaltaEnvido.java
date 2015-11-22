package com.modelo.acciones.envido;

public class FaltaEnvido extends EnvidoDecorator{

	public FaltaEnvido(AccionEnvido accionDecorar) {
		super(accionDecorar);
	}
	
	public String cantar(){
		return this.accionADecorar.cantar() + " FaltaEnvido";
	}

}
