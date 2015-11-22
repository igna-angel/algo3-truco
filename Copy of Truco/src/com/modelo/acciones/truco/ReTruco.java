package com.modelo.acciones.truco;

public class ReTruco extends TrucoDecorator {

	public ReTruco(AccionTruco accionDecorar) {
		super(accionDecorar);
	}
	
	public String cantar(){
		return this.accionADecorar.cantar() + " ReTruco";
	}

}
