package com.modelo.acciones.truco;

public class ValeCuatro extends ReTruco {

	public ValeCuatro(ReTruco accionDecorar) {
		super(accionDecorar);
	}
	
	public String cantar(){
		return this.accionADecorar.cantar() + " ValeCuatro";
	}

}
