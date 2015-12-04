package com.modelo.acciones.envido;

import java.util.Arrays;
import java.util.List;

import com.modelo.Jugador;
import com.modelo.Ronda;

public class FaltaEnvido extends TantoDecorator{
	
	private String _nombreAccion;

	public FaltaEnvido(AccionTanto accionDecorar, Jugador origen, Jugador destino, Ronda rondaActual) {
		super(accionDecorar, origen, destino);
		this._nombreAccion = "falta envido";
		List<String> acciones = Arrays.asList("quiero tanto", "no quiero tanto");
		rondaActual.setAccionesPosibles(acciones);
	}
	
	public int cantar(){
		return super.cantar() + 30;
	}
	
	@Override
	public String getNombreAccion() {
		return this._nombreAccion;
	}

}
