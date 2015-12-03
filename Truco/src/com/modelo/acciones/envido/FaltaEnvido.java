package com.modelo.acciones.envido;

import com.modelo.Jugador;

public class FaltaEnvido extends EnvidoDecorator{

	public FaltaEnvido(AccionEnvido accionDecorar, Jugador origen, Jugador destino) {
		super(accionDecorar, origen, destino);
	}
	
	public int cantar(){
		return super.cantar() + 30;
	}

}
