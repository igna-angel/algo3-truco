package com.modelo.acciones.envido;

import com.modelo.Jugador;

public class FlorMeAchico extends TantoDecorator{
	
	public FlorMeAchico(AccionTanto accionDecorar, Jugador origen, Jugador destino){
		super(accionDecorar,origen,destino);
	}

	public int cantar(){
		if (esFlorComun()){
			return super.cantar();
		} else { // ContraFlor al Resto
			return super.cantar() - 30;
		}
	}
	
	private boolean esFlorComun(){
		return super.cantar() == 3;
	}
}
