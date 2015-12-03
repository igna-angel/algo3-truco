package com.modelo.acciones.envido;

import com.modelo.Jugador;

public class Envido extends TantoDecorator {

	public Envido(AccionTanto accionDecorar, Jugador origen, Jugador destino) {
		super(accionDecorar, origen, destino);
	}
	
	public int cantar(){
		return super.cantar() + 2 ;
	}

}
