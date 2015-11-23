package com.modelo.acciones.truco;

public class TrucoDecorator implements AccionTruco{

	protected AccionTruco accionADecorar;
	
	public TrucoDecorator(AccionTruco accionDecorar){
		this.accionADecorar = accionDecorar;
	}
	
	@Override
	public int cantar() {
		return this.accionADecorar.cantar();
	}

}
