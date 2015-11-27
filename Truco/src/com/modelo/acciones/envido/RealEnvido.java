package com.modelo.acciones.envido;

public class RealEnvido extends EnvidoDecorator {

	public RealEnvido(AccionEnvido accionDecorar) {
		super(accionDecorar);
	}

	public int cantar(){
		if (this.accionADecorar.cantar() == 0){
			return this.accionADecorar.cantar() + 2;
		} else {
			return this.accionADecorar.cantar() + 3;
		}
	}
}
