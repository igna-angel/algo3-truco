package com.modelo.acciones.truco;

public class Truco extends TrucoDecorator{
	
	public Truco(AccionTruco accionDecorar) {
		super(accionDecorar);
	}

	public int cantar(){
		return this.accionADecorar.cantar() + 2;
	}
}
