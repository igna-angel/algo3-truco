package com.modelo.acciones.envido;

public class NoQuieroTanto extends EnvidoDecorator {

	public NoQuieroTanto(AccionEnvido accionDecorar) {
		super(accionDecorar);
	}
	
	public int cantar(){
		if (this.accionADecorar.cantar() == 1){
			return this.accionADecorar.cantar();
		} else if (this.accionADecorar.cantar() == 2){
			return this.accionADecorar.cantar() - 1;
		} else {
			return this.accionADecorar.cantar() - 2;
		}
	}
}
