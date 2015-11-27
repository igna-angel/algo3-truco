package com.modelo.acciones.truco;

public class NoQuiero extends TrucoDecorator{

	public NoQuiero(AccionTruco accionDecorar) {
		super(accionDecorar);
	}
	
	public int cantar(){
		return this.accionADecorar.cantar() - 1;
	}
}
