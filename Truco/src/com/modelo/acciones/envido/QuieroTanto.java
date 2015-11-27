package com.modelo.acciones.envido;

public class QuieroTanto extends EnvidoDecorator {

	public QuieroTanto(AccionEnvido accionDecorar) {
		super(accionDecorar);
	}
	
	public int cantar(){
		return super.cantar();
	}

}
