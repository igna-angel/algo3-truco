package com.modelo.acciones.envido;

import com.modelo.Jugador;

public class QuieroTanto extends EnvidoDecorator {

	public QuieroTanto(AccionEnvido accionDecorar,  Jugador origen, Jugador destino) {
		super(accionDecorar, origen, destino);
	}
	
	public int cantar(){
		return super.cantar();
	}

}
