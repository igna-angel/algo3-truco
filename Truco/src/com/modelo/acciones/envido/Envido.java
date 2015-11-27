package com.modelo.acciones.envido;

public class Envido extends EnvidoDecorator {

	public Envido(AccionEnvido accionDecorar) {
		super(accionDecorar);
	}
	
	public int cantar(){
		if (super.cantar() == 0) {
			return super.cantar() + 1;
		} else if (super.cantar() == 1){
			return super.cantar() + 2;
		} else {
			return super.cantar() +3;
		}
	}

}
