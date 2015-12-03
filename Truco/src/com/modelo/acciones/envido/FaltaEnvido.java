package com.modelo.acciones.envido;

import com.modelo.Jugador;

public class FaltaEnvido extends TantoDecorator{

	public FaltaEnvido(AccionTanto accionDecorar, Jugador origen, Jugador destino) {
		super(accionDecorar, origen, destino);
	}
	
	public int cantar(){
		return super.cantar() + 30;
	}

}
