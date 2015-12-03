package com.modelo.acciones.envido;

import com.modelo.Jugador;

public class Envido extends EnvidoDecorator {

	public Envido(AccionEnvido accionDecorar, Jugador origen, Jugador destino) {
		super(accionDecorar, origen, destino);
	}
	
	public int cantar(){
		return super.cantar() + 2 ;
	}

}
