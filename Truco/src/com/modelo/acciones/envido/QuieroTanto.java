package com.modelo.acciones.envido;

import com.modelo.Jugador;

public class QuieroTanto extends TantoDecorator {

	public QuieroTanto(AccionTanto accionDecorar,  Jugador origen, Jugador destino) {
		super(accionDecorar, origen, destino);
	}
	
	public int cantar(){
		return super.cantar();
	}

}
