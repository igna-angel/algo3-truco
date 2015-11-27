package com.modelo.acciones.envido;

public class FaltaEnvido extends EnvidoDecorator{

	public FaltaEnvido(AccionEnvido accionDecorar) {
		super(accionDecorar);
	}
	
	public int cantar(){
		return super.cantar() + 30;
	}

}
