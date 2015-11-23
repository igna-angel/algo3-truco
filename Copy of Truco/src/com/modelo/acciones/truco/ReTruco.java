package com.modelo.acciones.truco;

public class ReTruco extends TrucoDecorator {

	public ReTruco(AccionTruco accionDecorar) {
		super(accionDecorar);
	}
	
	public int cantar(){
		return this.accionADecorar.cantar() + 1;
	}

}
