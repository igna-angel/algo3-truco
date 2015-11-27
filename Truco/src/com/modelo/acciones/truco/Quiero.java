package com.modelo.acciones.truco;

public class Quiero extends TrucoDecorator{

	public Quiero(AccionTruco accionDecorar) {
		super(accionDecorar);
	}
	
	public int cantar(){
		return this.accionADecorar.cantar();
	}

}
