package com.modelo.acciones.flor;

public class FlorNoQuiero extends FlorDecorator{
	
	public FlorNoQuiero(AccionFlor accionDecorar) {
		super(accionDecorar);
	}

	public int cantar(){
		if (esFlorComun()){
			return super.cantar() - 2;
		} else if (esContraFlor()){
			return super.cantar() - 2;
		} else { // ContraFlor al Resto
			return super.cantar() - 29;
		}
	}
	
	private boolean esFlorComun(){
		return super.cantar() == 3;
	}
	
	private boolean esContraFlor(){
		return super.cantar() == 6;
	}
}
