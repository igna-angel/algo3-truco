package com.modelo.acciones.envido;

import com.modelo.Jugador;

public class Flor extends TantoDecorator{
	
	private String _nombreAccion;
	
	public Flor(AccionTanto accionDecorar, Jugador origen, Jugador destino) {
		super(accionDecorar, origen, destino);
		this._nombreAccion = "flor";
	}

	@Override
	public int cantar() {
		return super.cantar() + 3;
	}
	
	@Override
	public String getNombreAccion() {
		return this._nombreAccion;
	}

}
