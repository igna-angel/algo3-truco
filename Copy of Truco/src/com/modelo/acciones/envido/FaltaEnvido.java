package com.modelo.acciones.envido;

import com.modelo.Partido;

public class FaltaEnvido extends EnvidoDecorator{

	public FaltaEnvido(AccionEnvido accionDecorar) {
		super(accionDecorar);
	}
	
	public int cantar(Partido partido){
		return this.accionADecorar.cantar() + partido.getcantidadDePuntosFaltantes();
	}

}
