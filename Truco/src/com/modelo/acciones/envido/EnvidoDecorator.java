package com.modelo.acciones.envido;

public class EnvidoDecorator implements AccionEnvido {

	protected AccionEnvido accionADecorar;
	
	public EnvidoDecorator(AccionEnvido accionDecorar){
		this.accionADecorar = accionDecorar;
	}
	
	@Override
	public int cantar() {
		return accionADecorar.cantar();
	}

}
