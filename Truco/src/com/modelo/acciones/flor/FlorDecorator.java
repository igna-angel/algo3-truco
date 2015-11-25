package com.modelo.acciones.flor;

public class FlorDecorator  implements AccionFlor {

	protected AccionFlor accionADecorar;
	
	public FlorDecorator(AccionFlor accionDecorar){
		this.accionADecorar = accionDecorar;
	}
	
	@Override
	public int cantar() {
		return accionADecorar.cantar();
	}

}
